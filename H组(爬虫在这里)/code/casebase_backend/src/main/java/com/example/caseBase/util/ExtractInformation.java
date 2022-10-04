package com.example.caseBase.util;

import com.example.caseBase.DAO.LitigantRepository;
import com.example.caseBase.DAO.PunishmentRepository;
import com.example.caseBase.PO.Doc;
import com.example.caseBase.PO.litigant;
import com.example.caseBase.PO.punishment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ExtractInformation {
    @Autowired
    LitigantRepository litigantRepository;
    @Autowired
    PunishmentRepository punishmentRepository;

    HashMap<String, String> cityMap = new HashMap<>();
    //e.g.[江苏,苏]
    HashMap<String, String> provinceMap = new HashMap<>();

    public ExtractInformation() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/provinces.csv"), "GBK"));
            String str = null;
            while ((str = br.readLine()) != null) {
                String[] strs = str.split(",");
                provinceMap.put(strs[2], strs[1]);
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/csv/cities.csv"), "GBK"));
            while ((str = br.readLine()) != null) {
                String[] strs = str.split(",");
                for (String city : strs) {
                    if (city.length() == 0) {
                        break;
                    }
                    String[] provAndCity = city.substring(0, city.length() - 1).split("省");
                    cityMap.put(provAndCity[1], provAndCity[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extract(Doc doc) {
        punishment punish = new punishment();
        punish.setCase_id(doc.getDocId());
        String basis=doc.getBasis();
        if(basis!=null&&basis.length()>2000){
            basis=basis.substring(0,2000);
        }
        punish.setBasis(basis);

        String cause=doc.getCause();
        if(cause!=null&&cause.length()>2000){
            cause=cause.substring(0,2000);
        }
        punish.setCause(cause);

        punish.setDate(doc.getPublishDate());

        String name=doc.getName();
        if(name.length()>200){
            name=name.substring(0,200);
        }
        punish.setDocName(name);

        punish.setDocNum(doc.getDocNum());
        punish.setOrgan(doc.getOrgan());
        //提取地区信息
        String title = doc.getName();
        if (title.contains("中国银行保险监督管理委员会")
                || title.contains("中国保险监督管理委员会")
                || title.contains("中国银监会")) {
            //银保监总局分级
            punish.setLevel(1);
        } else {
            for (String city : cityMap.keySet()) {
                if (title.contains(city)) {
                    punish.setLevel(3);
                    punish.setProvince(cityMap.get(city));
                    punish.setCity(city);
                    break;
                }
            }
            if (punish.getLevel() == null) {
                punish.setLevel(2);
                for (String province : provinceMap.keySet()) {
                    if (title.contains(province)) {
                        punish.setProvince(provinceMap.get(province));
                    }
                }
                for (String province : provinceMap.values()) {
                    if (title.contains(province)) {
                        punish.setProvince(province);
                    }
                }
            }
        }

        //提取处罚金额信息
        int fine = 0;
        if (doc.getDecision() != null) {
            fine = extractNum(doc.getDecision());
        } else {
            int start = doc.getContent().indexOf("罚款");
            int end = doc.getContent().lastIndexOf("罚款");
            String text = doc.getContent().substring(Math.max((start - 10), 0), Math.min((end + 10), doc.getContent().length()));
            fine = extractNum(text);
        }
        punish.setFine(fine);

        //提取当事人信息

        for (litigant li : doc.getLitigants()) {
            //对每个当事人查询mysql数据库中是否已有该litigant，有则取出其id，没有则新添加该litigant
            litigant li_found = litigantRepository.getLitigantByName(li.getName());
            li.setProvince(punish.getProvince());
            li.setType(li.getUnit()==null?"机构":"个人");
            punish.setType(li.getType());

            if (li_found != null) {
                li.setId(li_found.getId());
            } else {
                li.setName(li.getName().length()>200?li.getName().substring(0,200):li.getName());
                li=litigantRepository.save(li);
            }
            punish.setLitigant_id(li.getId());
            punish.setLitigantName(li.getName());
            punishmentRepository.save(punish);
        }


    }

    int extractNum(String text) {
        int res = 0;
        //提取文本中的所有金额信息,金额后会跟 “元”；
        List<Character> numCharSet = new ArrayList<Character>() {{
            add('1');
            add('2');
            add('3');
            add('4');
            add('5');
            add('6');
            add('7');
            add('8');
            add('9');
            add('0');
        }};
        List<Character> KanjiCharSet = new ArrayList<Character>() {{
            add('一');
            add('二');
            add('三');
            add('四');
            add('五');
            add('六');
            add('七');
            add('八');
            add('九');
            add('十');
            add('零');
            add('百');
            add('千');
            add('万');
            add('亿');
        }};
        int index = 0;
        while (index < text.length()) {
            index++;
            if(index==text.length()){
                break;
            }
            if (text.charAt(index) == '元') {
                boolean hasKanji = false;
                boolean hasNum = false;
                int start = Math.max((index - 1), 0);
                while (start>=0&&(numCharSet.contains(text.charAt(start)) || KanjiCharSet.contains(text.charAt(start)))) {
                    if (numCharSet.contains(text.charAt(start))) {
                        hasNum = true;
                    }
                    if (KanjiCharSet.contains(text.charAt(start))) {
                        hasKanji = true;
                    }
                    start--;
                }
                start++;
                String numString = text.substring(start, index);
                if (hasKanji && !hasNum) {
                    res += chineseNumber2Int(numString);
                }
                if(hasNum&&hasKanji){
                    res+=mixedNumber2Int(numString);
                }
                if(hasNum&&!hasKanji){
                    res+=Integer.parseInt(numString);
                }
            }
        }
        return res;
    }

    public int chineseNumber2Int(String chineseNumber) {
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if (0 != count) {//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) {//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
    public int mixedNumber2Int(String mixedNumber){
        mixedNumber=mixedNumber.replace("亿","00000000").replace("万","0000").replace("千","000").replace("百","00");
        if(mixedNumber.contains("十")){
            if(mixedNumber.indexOf('十')==0){
                mixedNumber="10"+mixedNumber.substring(1);
            }
        }
        try{
            return Integer.parseInt(mixedNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
