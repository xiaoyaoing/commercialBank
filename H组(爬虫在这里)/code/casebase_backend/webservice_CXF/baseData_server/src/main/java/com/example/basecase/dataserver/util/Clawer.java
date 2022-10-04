package com.example.basecase.dataserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.basecase.dataserver.DAO.DocumentRepository;
import com.example.basecase.dataserver.PO.Doc;
import com.example.basecase.dataserver.PO.litigant;
import com.example.basecase.dataserver.service.DatabaseBasicService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Program: BaseCase
 * @description: Data Clawer of Bank Insurance Regulatory Commission
 * @author: Zhu Wei
 * @create 2021-09-21-19:49
 **/
@Component
public class Clawer {
    //4113: 银保监会机关行政处罚，决定书 or 公开表
    //4114: 银保监局本级行政处罚，决定书 or 公开表
    //4115: 银保监分局本级行政处罚，决定书 or 公开表
    @Autowired
    private DatabaseBasicService databaseBasicService;

    private List<String> nullStringList=new ArrayList<String>(){{
        add("-");
        add("/");
        add("\\");
        add("--");
        add("——");
        add("//");
        add("---");
        add("----");
        add("-----");
        add("------");
        add("-------");
        add("--------");
        add("---------");
        add("----------");
        add("");
        add(" ");
        add("  ");
    }};


    private final String base="http://www.cbirc.gov.cn" ;
    public HashMap<String,JSONObject> sendGet(String urlStr) throws IOException, JSONException {
        URL url=new URL(urlStr);
        //返回结果集
        StringBuilder document = new StringBuilder();
        //创建链接
        URLConnection conn = url.openConnection();
        //读取返回结果集
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Edg/94.0.992.38");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null){
            document.append(line);
        }
        reader.close();
        String jsonStr=document.toString();

        return JSON.parseObject(jsonStr, (Type) Map.class);
    }


    public void getDocURL(int mode) throws IOException, JSONException, ParseException {
        String url=base+"/cbircweb/DocInfo/SelectDocByItemIdAndChild?itemId="+(mode==1?4113:(mode==2?4114:4115))+"&pageSize=18&pageIndex=";
        boolean before2016=false;
        for(int i=0;i<=2000;i++){
            HashMap<String,JSONObject> map=sendGet(url+i);
            JSONObject obj=(JSONObject) map.get("data");
            JSONArray rows=(JSONArray) obj.get("rows");
            if(rows.size()==0){
                break;
            }
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date start=df.parse("2016-01-01 00:00:00");
            Date end=df.parse("2021-08-31 23:59:59");
            for(Object row:rows){
                JSONObject doc=(JSONObject) row;
                Long docId=Long.parseLong(doc.get("docId").toString());

                String docSubtitle=(String)doc.get("docSubtitle");
                String publishDate=(String)doc.get("publishDate");

                Date date=df.parse(publishDate);
                if(date.after(start)&&date.before(end)){
                    if(docSubtitle.contains("信息公开表")||docSubtitle.contains("决定书")){
                    //if(docSubtitle.contains("决定书")){
                        getHtml(docId,docSubtitle,date);
                        System.out.println(docId+" "+date.toString()+" "+docSubtitle);
                    }
                }
                if(date.before(start)){
                    before2016=true;
                }
            }
            if(before2016){
                break;
            }
        }

    }

    public void getHtml(Long docId, String subTitle,Date date) throws JSONException, IOException {
        Doc doc=new Doc();
        doc.setState(1);
        String htmlUrl="http://www.cbirc.gov.cn/cn/static/data/DocInfo/SelectByDocId/data_docId="+docId+".json";
        HashMap<String,JSONObject> html=sendGet(htmlUrl);
        JSONObject data= html.get("data");
        String docClob=(String) data.get("docClob");
        Document document=Jsoup.parse(docClob);
        Element Section0=document.getElementsByTag("body").get(0);
        List<Element> trs=Section0.select("tr");
        doc.setName(subTitle);
        doc.setDocId(docId);
        doc.setHtml(Section0.html());
        //TODO: deal content with <p>xxx</p>
        List<Element> tagPs=Section0.select("p");
        StringBuffer sb=new StringBuffer();
        for(Element tagP:tagPs){
            sb.append(tagP.text()).append("\n");
        }
        doc.setContent(sb.toString());
        doc.setPublishDate(date);
        int skip=0;
        if(trs.size()>5){
            while(!trs.get(skip).select("td").get(0).text().contains("决定")&&skip<trs.size()-1){
                skip++;
            }
            if(trs.get(0).select("table").size()!=0){
                skip=trs.size();
            }
        }
        if(trs.size()>5&&trs.size()-skip>5){
            //表格

            doc.setDocNum(trs.get(skip).select("td").get(1).text());
            int rowspan=1;
            if(!trs.get(skip+1).select("td").get(0).attr("rowspan").equals("")){
                rowspan=Integer.parseInt(trs.get(skip+1).select("td").get(0).attr("rowspan"));
            }
            if(rowspan==2){
                //单位
                doc.setType(1);
                if(trs.get(skip+1).select("td").size()==3){
                    doc.addLitigant(new litigant(trs.get(skip+1).select("td").get(2).text(),"",1));

                }else{
                    doc.addLitigant(new litigant(trs.get(skip+1).select("td").get(3).text(),trs.get(skip+2).select("td").get(1).text(),1));
                }
            }
            else if(rowspan==1){
                //个人
                doc.setType(0);
                doc.addLitigant(new litigant(trs.get(skip+1).select("td").get(1).text(),"",0));
            }
            else if(rowspan==3){
                //TODO: litigant processing
                if(this.nullStringList.contains(trs.get(skip+1).select("td").get(2).text())){
                    //个人为空,只有单位
                    doc.setType(1);
                    litigant li=new litigant(trs.get(skip+2).select("td").get(2).text());
                    li.setUnit(trs.get(skip+3).select("td").get(1).text());
                    doc.addLitigant(li);
                }else{
                    //个人不为空，验证单位两行是否为空
                    if(!this.nullStringList.contains(trs.get(skip+2).select("td").get(2).text())){
                        //单位也不为空，既有个人，也有单位
                        doc.setType(2);
                        doc.addLitigant(new litigant(trs.get(skip+2).select("td").get(2).text(),trs.get(skip+3).select("td").get(1).text(),1));

                    }else{
                        //只有个人
                        doc.setType(0);
                    }

                    doc.addLitigant(new litigant(trs.get(skip + 1).select("td").get(2).text(), "",0));

                }
            }
            else if(rowspan==4){
                if(trs.get(skip+1).select("td").size()==4){
                    boolean personIsNull=this.nullStringList.contains(
                            trs.get(skip+1).select("td").get(3).text());
                    boolean unitIsNull=this.nullStringList.contains(
                            trs.get(skip+3).select("td").get(2).text());

                    if(!personIsNull){
                        //个人不为空
                        doc.addLitigant(new litigant(trs.get(skip+1).select("td").get(3).text(),trs.get(skip+2).select("td").get(1).text(),0));
                    }
                    if(!unitIsNull){
                        //单位不为空
                        doc.addLitigant(new litigant(trs.get(skip+3).select("td").get(2).text(),trs.get(skip+4).select("td").get(1).text(),1));
                    }
                    if(personIsNull&&!unitIsNull){
                        //只有单位
                        doc.setType(1);
                    }else if(!personIsNull&&unitIsNull){
                        //只有个人
                        doc.setType(0);
                    }
                    else if(!personIsNull){
                        //单位、个人都有
                        doc.setType(2);
                    }
                }

            }
            if(trs.size()>skip+rowspan+1){
                doc.setCause(trs.get(skip+rowspan+1).select("td").get(1).text());
            }
            if(trs.size()>skip+rowspan+2){
                doc.setBasis(trs.get(skip+rowspan+2).select("td").get(1).text());
            }
            if(trs.size()>skip+rowspan+3){
                doc.setDecision(trs.get(skip+rowspan+3).select("td").get(1).text());
            }
            if(trs.size()>skip+rowspan+4){
                doc.setOrgan(trs.get(skip+rowspan+4).select("td").get(1).text());
            }
            if(trs.size()>skip+rowspan+5){
                doc.setDate(trs.get(skip+rowspan+5).select("td").get(1).text());
            }
            //所有爬取的文档都是已经发布的。
            doc.setState(1);
            if(doc.getLitigants().size()==0){
                //异常情况，后续单独处理。
                logDocId(doc.getDocId());
            }
        }
        System.out.println(doc.getLitigantText());
        databaseBasicService.saveDoc(doc);

    }
    public void logDocId(Long id) throws IOException {
        File file=new File("logs/clawerLog");
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
        br.write("error occurred when clawing doc id: "+id.toString());
        br.newLine();
        br.flush();
        br.close();
    }



//    public void downloadFile(String filePath,String subTitle) throws MalformedURLException {
//        String htmlUrl="http://www.cbirc.gov.cn/cn/static/data/DocInfo/SelectByDocId/data_docId=1011731.json";
//        // 下载网络文件
//        int bytesum = 0;
//        int byteread = 0;
//
//        URL url = new URL(base+filePath);
//
//        try {
//            URLConnection conn = url.openConnection();
//            InputStream inStream = conn.getInputStream();
//            FileOutputStream fs = new FileOutputStream("src/docs/"+subTitle+".doc");
//
//            byte[] buffer = new byte[1204];
//            int length;
//            while ((byteread = inStream.read(buffer)) != -1) {
//                bytesum += byteread;
//                fs.write(buffer, 0, byteread);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void processDoc(String FilePath){
//        File docFile=new File(FilePath);
//        FileInputStream docFis;
//        String text = null;
//        try {
//            docFis = new FileInputStream(docFile);
//            HWPFDocument doc = new HWPFDocument(docFis);
//            Range range = doc.getRange();
//            if(FilePath.contains("信息公开表")){
//                readTable(docFile.getName(),range);
//            }else{
//
//            }
//            text = range.text();
//            docFis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(text);
//    }
//    private void readTable(String name, Range range) {
//        //遍历range范围内的table。
//        Doc doc=new Doc();
//        TableIterator tableIter = new TableIterator(range);
//        Table table;
//        TableRow row;
//        TableCell cell;
//        while (tableIter.hasNext()) {
//            doc.setName(name);
//            table = tableIter.next();
//            int cur_row =0;
//            doc.setDocNum(getText(table.getRow(cur_row).getCell(1)));
//            cur_row++;
//            if(getText(table.getRow(cur_row).getCell(0)).equals("被处罚当事人姓名")){
//
//                doc.addChargeMan(getText(table.getRow(cur_row).getCell(1)));
//                cur_row++;
//            }
//            else if(getText(table.getRow(cur_row).getCell(0)).equals("被处罚当事人")){
//
//                doc.addChargeMan(getText(table.getRow(cur_row+1).getCell(3)));
//                doc.addLitigant(getText(table.getRow(cur_row).getCell(3)));
//                cur_row+=2;
//
//            }else if(getText(table.getRow(cur_row).getCell(0)).equals("被处罚当事人姓名或名称")){
//
//                if(getText(table.getRow(cur_row).getCell(3)).equals("")){
//                    doc.addLitigant(getText(table.getRow(cur_row+1).getCell(3)));
//                    doc.addChargeMan(getText(table.getRow(cur_row+2).getCell(3)));
//                }else{
//                    doc.addLitigant(getText(table.getRow(cur_row).getCell(3)));
//                }
//                cur_row+=3;
//            }
//            doc.setCause(getText(table.getRow(cur_row).getCell(1)));
//            cur_row++;
//            doc.setBasis(getText(table.getRow(cur_row).getCell(1)));
//            cur_row++;
//            doc.setDecision(getText(table.getRow(cur_row).getCell(1)));
//            cur_row++;
//            doc.setOrgan(getText(table.getRow(cur_row).getCell(1)));
//            cur_row++;
//            doc.setDate(getText(table.getRow(cur_row).getCell(1)));
//
//            System.out.println(ReflectionToStringBuilder.toString(doc));
//        }
//    }
//    private String getText(TableCell cell){
//        return cell.text().replace("\r","\n")
//                .replace(String.valueOf((char)11)," ").trim();
//    }


//    public void getPage(int docId,String title){
//        String url=base+"/cn/view/pages/ItemDetail.html?docId="+docId;
//        Doc doc=new Doc();
//        try{
//            if(title.contains("信息公开表")){
//                Document document = Jsoup.connect(url).get();
//                System.out.println(document);
//                if(document.select("div[class=Section0]").size()==0){
//                    throw new Exception("页面异常");
//                }
//                Element Section0=document.select("<div class=\"Section0\">").get(0);
//                Element table=Section0.select("table").get(0);
//                List<Element> trs=table.select("tr");
//                for(Element tr:trs){
//                    System.out.println(tr.select("td").get(0).text());
//                }
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
