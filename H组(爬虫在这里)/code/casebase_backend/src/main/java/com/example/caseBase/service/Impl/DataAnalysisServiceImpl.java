package com.example.caseBase.service.Impl;

import com.example.caseBase.DAO.PunishmentRepository;
import com.example.caseBase.PO.punishment;
import com.example.caseBase.VO.DefaultStatistVO;
import com.example.caseBase.VO.RegionFineVO;
import com.example.caseBase.service.DataAnalysisService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.plaf.synth.Region;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    //todo 获取 一定时间范围内（一年）所有的 punishment 条目
//   通过遍历筛选 获得  监管 机构 处罚情况（正则匹配提取） +表驱动
//   通过遍历 得到 个人 处罚情况  hashmap＋排序处理
    @Autowired
    PunishmentRepository punishmentRepository;

    String[] monthDict={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    @Override
    public DefaultStatistVO getOrganStat(Integer year, Integer month) {
        Date startTime;
        Date endTime;
        if (month == 0) {
            ArrayList<Date> timeRange = getYearStartEndTime(year);
            startTime = timeRange.get(0);
            endTime = timeRange.get(1);
        } else {
            ArrayList<Date> timeRange = getCurYearAndMonthStartEndTime(year, month);
            startTime = timeRange.get(0);
            endTime = timeRange.get(1);
        }


        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.equal(root.get("type").as(String.class), "机构"));
                predicates.add(cb.between(root.get("date"), startTime, endTime));


                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults = this.punishmentRepository.findAll(querySpecifi);

        Integer[] stats = {0, 0, 0, 0, 0, 0, 0};
//      银行
//      0 StateOwnedBanks 国有
//      1 isJointStockBank 股份
//      2 PrivateBanks 私人（民营）
//      3 村镇银行
//      4 地方银行  其他的银行
//      5 农村信用社
//      6 保险公司
//
        for (punishment matchResult : matchResults) {
            String litigantName = matchResult.getLitigantName();
            if (litigantName == null) {
                continue;
            }
            if (litigantName.contains("银行")) {
                if (isStateOwnedBanks(litigantName)) {
                    stats[0]++;
                } else if (isJointStockBank(litigantName)) {
                    stats[1]++;
                } else if (isPrivateBanks(litigantName)) {
                    stats[2]++;
                } else if (litigantName.contains("村镇")) {
                    stats[3]++;
                } else {
                    stats[4]++;
                }
            } else if (litigantName.contains("信用合作")) {
                stats[5]++;
            } else if (litigantName.contains("保险")) {
                stats[6]++;
            }
        }
        Map<String, Integer> resMap = new HashMap<>();
        resMap.put("大型国有银行", stats[0]);
        resMap.put("股份制商业银行", stats[1]);
        resMap.put("民营银行", stats[2]);
        resMap.put("村镇银行", stats[3]);
        resMap.put("地方银行", stats[4]);
        resMap.put("农村信用社", stats[5]);
        resMap.put("保险公司机构", stats[6]);
        List<Map.Entry<String, Integer>> resList = new ArrayList<Map.Entry<String, Integer>>(resMap.entrySet());
        // 通过比较器来实现排序
        Collections.sort(resList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        LinkedHashMap<String, Integer> resLinkedMap = new LinkedHashMap();
        int maxNum = resList.get(0).getValue();
        for (Map.Entry<String, Integer> mapping : resList) {
            resLinkedMap.put(mapping.getKey(), mapping.getValue());
        }

        DefaultStatistVO resVO = new DefaultStatistVO();
        resVO.setNum(matchResults.size());
        resVO.setMax(maxNum);
        resVO.setContent(resLinkedMap);
        return resVO;
    }

    @Override
    public DefaultStatistVO getOrganFineNum(Integer year, Integer month) {
        return getOrganFine(year, month, 0);
    }

    @Override
    public DefaultStatistVO getOrganFineMoney(Integer year, Integer month) {
        return getOrganFine(year, month, 1);
    }

    @Override
    public DefaultStatistVO getPersonFineNum(Integer year, Integer month) {
        return getPersonFine(year, month, 0);
    }

    @Override
    public DefaultStatistVO getPersonFineMoney(Integer year, Integer month) {
        return getPersonFine(year, month, 1);
    }


    public DefaultStatistVO getOrganFine(Integer year, Integer month, int type){//type表示罚款数量(0)还是金额(1)
        Date startTime;
        Date endTime;
        if(month==0){
            ArrayList<Date> timeRange=getYearStartEndTime(year);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }else {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,month);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }

        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.equal(root.get("type").as(String.class), "机构"));
                predicates.add(cb.between(root.get("date"),startTime,endTime));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults=this.punishmentRepository.findAll(querySpecifi);
        Map<String,Integer> resMap = new HashMap<String, Integer>();
        for (punishment matchResult : matchResults) {
            String litigantName = matchResult.getLitigantName();
            Integer fine = matchResult.getFine();
            if(litigantName.length() > 1 && type == 0) {
                if (!resMap.containsKey(litigantName)) {
                    resMap.put(litigantName, 1);
                } else {
                    int count = resMap.get(litigantName);
                    resMap.put(litigantName, count + 1);
                }
            }
            else if(litigantName.length() > 1 && type == 1){
                if(!resMap.containsKey(litigantName)){
                    resMap.put(litigantName,fine);
                }else{
                    int fineMoney = resMap.get(litigantName);
                    resMap.put(litigantName,fineMoney + fine);
                }
            }
        }
        List<Map.Entry<String, Integer>> resList = new ArrayList<Map.Entry<String, Integer>>(resMap.entrySet());
        Collections.sort(resList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        resList = resList.subList(0,10);
        LinkedHashMap<String, Integer> resLinkedMap=new LinkedHashMap();
        int maxNum=resList.get(0).getValue();
        int num = 0;
        for (Map.Entry<String, Integer> mapping : resList) {
            if(type == 1){
                resLinkedMap.put(mapping.getKey(),mapping.getValue()/10000);
            }
            else{
                resLinkedMap.put(mapping.getKey(),mapping.getValue());
            }
            num = mapping.getValue() + num;
        }

        DefaultStatistVO resVO=new DefaultStatistVO();


        resVO.setNum(type == 0 ?num:num/10000);
        resVO.setMax(type == 0 ?maxNum:maxNum/10000);
        resVO.setContent(resLinkedMap);
        return resVO;
    }
    public DefaultStatistVO getPersonFine(Integer year, Integer month, int type){//type表示罚款数量(0)还是金额(1)
        Date startTime;
        Date endTime;
        if(month==0){
            ArrayList<Date> timeRange=getYearStartEndTime(year);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }else {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,month);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }

        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.equal(root.get("type").as(String.class), "个人"));
                predicates.add(cb.between(root.get("date"),startTime,endTime));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults=this.punishmentRepository.findAll(querySpecifi);
        Map<String,Integer> resMap = new HashMap<String, Integer>();
        for (punishment matchResult : matchResults) {
            String litigantName = matchResult.getLitigantName();
            if(litigantName.length() == 2 || litigantName.length() == 3){
            }
            else if(litigantName.length() > 5 && litigantName.length() < 20){
                if(litigantName.charAt(2) == '、'){
                    litigantName = litigantName.substring(0,2) + "等";
                }
                else if(litigantName.charAt(3) == '、'){
                    litigantName = litigantName.substring(0,3) + "等";
                }
            }
            else continue;

            Integer fine = matchResult.getFine();
                if (type == 0) {
                    if (!resMap.containsKey(litigantName)) {
                        resMap.put(litigantName, 1);
                    } else {
                        int count = resMap.get(litigantName);
                        resMap.put(litigantName, count + 1);
                    }
                } else if (type == 1) {
                    if (!resMap.containsKey(litigantName)) {
                        resMap.put(litigantName, fine);
                    } else {
                        int fineMoney = resMap.get(litigantName);
                        resMap.put(litigantName, fineMoney + fine);
                    }
            }
        }
        List<Map.Entry<String, Integer>> resList = new ArrayList<Map.Entry<String, Integer>>(resMap.entrySet());
        Collections.sort(resList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        resList = resList.subList(0,10);

        LinkedHashMap<String, Integer> resLinkedMap=new LinkedHashMap();
        int maxNum=resList.get(0).getValue();
        int num = 0;
        for (Map.Entry<String, Integer> mapping : resList) {
            if(type == 1){
                resLinkedMap.put(mapping.getKey(),mapping.getValue()/10000);
            }
            else{
                resLinkedMap.put(mapping.getKey(),mapping.getValue());
            }
            num = mapping.getValue() + num;
        }

        DefaultStatistVO resVO=new DefaultStatistVO();


        resVO.setNum(type == 0 ?num:num/10000);
        resVO.setMax(type == 0 ?maxNum:maxNum/10000);
        resVO.setContent(resLinkedMap);
        return resVO;
    }

    @Override
    public DefaultStatistVO getPunishNumStatByYear(Integer year, Integer month) {

        int  totalNum=0;
        int  maxNum=0;
        LinkedHashMap<String, Integer> resLinkedMap=new LinkedHashMap();
        for (int i = 1; i <= 12; i++) {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,i);
            Date startTime=timeRange.get(0);
            Date endTime=timeRange.get(1);
            List<punishment> matchResults;
            Specification<punishment> querySpecifi = new Specification<punishment>() {
                @Override
                public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();

//                    predicates.add(cb.equal(root.get("type").as(String.class), "机构"));
                    predicates.add(cb.between(root.get("date"),startTime,endTime));
//
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            };
            matchResults=this.punishmentRepository.findAll(querySpecifi);

            totalNum+=matchResults.size();
            maxNum=Math.max(maxNum,matchResults.size());
            resLinkedMap.put(monthDict[i-1],matchResults.size());
        }
        DefaultStatistVO resVO=new DefaultStatistVO();
        resVO.setNum(totalNum);
        resVO.setMax(maxNum);
        resVO.setContent(resLinkedMap);
        return resVO;
    }

    @Override
    public DefaultStatistVO getPunishFineStatByYear(Integer year, Integer month) {
        int  totalNum=0;
        int  maxNum=0;
        LinkedHashMap<String, Double> resLinkedMap=new LinkedHashMap();
        for (int i = 1; i <= 12; i++) {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,i);
            Date startTime=timeRange.get(0);
            Date endTime=timeRange.get(1);
            List<punishment> matchResults;
            Specification<punishment> querySpecifi = new Specification<punishment>() {
                @Override
                public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();

//                    predicates.add(cb.equal(root.get("type").as(String.class), "机构"));
                    predicates.add(cb.between(root.get("date"),startTime,endTime));
//
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            };

            matchResults=this.punishmentRepository.findAll(querySpecifi);

            int curMonthFine=0;
            for (punishment matchResult : matchResults) {
                curMonthFine+=matchResult.getFine();
            }

            totalNum+=curMonthFine;
            maxNum=Math.max(maxNum,curMonthFine);

            double oriFine = curMonthFine/10000;
            BigDecimal bFine =new BigDecimal(oriFine);
            double  realFine =bFine.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            resLinkedMap.put(monthDict[i-1],realFine);
        }

        DefaultStatistVO resVO=new DefaultStatistVO();
        resVO.setNum(totalNum/10000);
        resVO.setMax(maxNum/10000);
        resVO.setContent(resLinkedMap);
        return resVO;
    }
    @Override
    public DefaultStatistVO getPunishAuthorityStatByYear(Integer year, Integer month){
        Date startTime;
        Date endTime;
        if(month==0){
            ArrayList<Date> timeRange=getYearStartEndTime(year);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }else {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,month);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }

        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.between(root.get("date"),startTime,endTime));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults=this.punishmentRepository.findAll(querySpecifi);

//        总局  分局
        int[] statRes={0,0};
        for (punishment matchResult : matchResults) {
           String organName=  matchResult.getOrgan();

            if (organName == null) {
                continue;
            }

           if(!organName.contains("局")){
               continue;
           } else if(organName.contains("分局")){
               statRes[1]++;
           }else {
               statRes[0]++;
           }
        }
        LinkedHashMap<String, Integer> resLinkedMap=new LinkedHashMap();
        resLinkedMap.put("监管总局",statRes[0]);
        resLinkedMap.put("监管分局",statRes[1]);
        DefaultStatistVO resVO=new DefaultStatistVO();
        resVO.setNum(statRes[0]+statRes[1]);
        resVO.setMax(Math.max(statRes[0],statRes[1]));
        resVO.setContent(resLinkedMap);
       return resVO;
    }

    public DefaultStatistVO getPunishAuthorityFineStatByYear(Integer year, Integer month){
        Date startTime;
        Date endTime;
        if(month==0){
            ArrayList<Date> timeRange=getYearStartEndTime(year);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }else {
            ArrayList<Date> timeRange=getCurYearAndMonthStartEndTime(year,month);
            startTime=timeRange.get(0);
            endTime=timeRange.get(1);
        }

        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.between(root.get("date"),startTime,endTime));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults=this.punishmentRepository.findAll(querySpecifi);

//        总局  分局
        int[] statRes={0,0};
        for (punishment matchResult : matchResults) {
            String organName=  matchResult.getOrgan();
            if (organName == null) {
                continue;
            }

            if(!organName.contains("局")){
                continue;
            } else if(organName.contains("分局")){
                statRes[1]+=matchResult.getFine();
            }else {
                statRes[0]+=matchResult.getFine();
            }
        }
        LinkedHashMap<String, Integer> resLinkedMap=new LinkedHashMap();
        resLinkedMap.put("监管总局",statRes[0]/10000);
        resLinkedMap.put("监管分局",statRes[1]/10000);
        DefaultStatistVO resVO=new DefaultStatistVO();
        resVO.setNum((statRes[0]+statRes[1])/10000);
        resVO.setMax(Math.max(statRes[0],statRes[1])/10000);
        resVO.setContent(resLinkedMap);
        return resVO;
    }
    boolean isStateOwnedBanks(String name) {
        if (name == null) {
            return false;
        }
        String[] dict = {"工商银行", "农业银行", "中国银行", "建设银行", "交通银行", "邮政储蓄银行"};
        for (String s : dict) {
            if (name.contains(s)) {
                return true;
            }
        }
        return false;
    }

    boolean isJointStockBank(String name) {
        if (name == null) {
            return false;
        }
        String[] dict = {"招商银行", "浦发银行", "中信银行", "光大银行", "华夏银行", "民生银行", "广发银行", "兴业银行", "平安银行", "浙商银行", "恒丰银行", "渤海银行"};
        for (String s : dict) {
            if (name.contains(s)) {
                return true;
            }
        }
        return false;
    }

    boolean isPrivateBanks(String name) {
        if (name == null) {
            return false;
        }
        String[] dict = {"金城银行", "华瑞银行", "浙江网商银行", "民商银行", "前海微众银行", "三湘银行", "富民银行", "新网银行", "中关村银行", "亿联银行", "众邦银行", "华通银行", "蓝海银行", "苏宁银行", "客商银行", "新安银行", "振兴银行", "裕民银行", "锡商银行"};
        for (String s : dict) {
            if (name.contains(s)) {
                return true;
            }
        }
        return false;
    }


    ArrayList<Date> getCurYearAndMonthStartEndTime(Integer year, Integer month) {
        ArrayList<Date> res = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = String.valueOf(year);
        String endTime = String.valueOf(year);
        if (month < 10) {
            startTime = startTime + "-0" + String.valueOf(month) + "-01 01:00:00";
            if (month != 2) {
                endTime = endTime + "-0" + String.valueOf(month) + "-30 23:59:59";
            } else {
                endTime = endTime + "-0" + String.valueOf(month) + "-28 23:59:59";
            }
        } else {
            startTime = startTime + "-" + String.valueOf(month) + "-01 01:00:00";
            endTime = endTime + "-" + String.valueOf(month) + "-30 23:59:59";
        }


        Date start = new Date();
        Date end = new Date();
        try {
            start = ft.parse(startTime);
            end = ft.parse(endTime);
        } catch (ParseException e) {
            System.out.println("Unparseable using " + startTime + " " + endTime);
        }
        res.add(start);
        res.add(end);
        return res;
    }

    ArrayList<Date> getYearStartEndTime(Integer year) {
        ArrayList<Date> res = new ArrayList<>();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = String.valueOf(year) + "-01-01 01:00:00";
        String endTime = String.valueOf(year) + "-12-31 23:59:59";
        Date start = new Date();
        Date end = new Date();
        try {
            start = ft.parse(startTime);
            end = ft.parse(endTime);
        } catch (ParseException e) {
            System.out.println("Unparseable using " + startTime + " " + endTime);
        }
        res.add(start);
        res.add(end);
        return res;
    }


    // todo 利用同样的方式 得到 监管机构（分和总）处罚情况（对应着表一）
// todo 利用同样的方式，得到  具体受罚机构罚单数量排行 和 金额总计排行 （大hashmap）
// todo 利用同样的方式，，得到 月度统计的资料。。。罚单和金额
//  以及 全国地理位置
    @Override
    public List<RegionFineVO> getRegionFineData(Integer year, Integer month) {
        Date startTime;
        Date endTime;
        if (month == 0) {
            ArrayList<Date> timeRange = getYearStartEndTime(year);
            startTime = timeRange.get(0);
            endTime = timeRange.get(1);
        } else {
            ArrayList<Date> timeRange = getCurYearAndMonthStartEndTime(year, month);
            startTime = timeRange.get(0);
            endTime = timeRange.get(1);
        }

        List<punishment> matchResults;
        Specification<punishment> querySpecifi = new Specification<punishment>() {
            @Override
            public Predicate toPredicate(Root<punishment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.between(root.get("date"), startTime, endTime));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        matchResults = this.punishmentRepository.findAll(querySpecifi);
        List<RegionFineVO> res = new ArrayList<>();
        List<String> provinces = new ArrayList<String>() {{
            add("北京");
            add("天津");
            add("河北");
            add("山西");
            add("内蒙");
            add("辽宁");
            add("吉林");
            add("黑龙江");
            add("上海");
            add("江苏");
            add("浙江");
            add("安徽");
            add("福建");
            add("江西");
            add("山东");
            add("河南");
            add("湖北");
            add("湖南");
            add("广东");
            add("广西");
            add("海南");
            add("重庆");
            add("四川");
            add("贵州");
            add("云南");
            add("西藏");
            add("陕西");
            add("甘肃");
            add("青海");
            add("宁夏");
            add("新疆");
            add("台湾");
            add("香港特别行政区");
            add("澳门");
            add("南海诸岛");
        }};
        for (String province : provinces) {
            res.add(new RegionFineVO(province));
        }
        for (punishment punish : matchResults) {
            RegionFineVO regionFineVO;
            if(punish.getDocName()==null){
                continue;
            }
            if(punish.getDocName().contains("天津")||punish.getDocName().contains("滨海")){
                regionFineVO = res.get(provinces.indexOf("天津"));
                regionFineVO.setFine(regionFineVO.getFine()+punish.getFine());
                regionFineVO.setValue(regionFineVO.getValue()+1);
            }
            if (punish.getProvince() == null) {
                continue;
            }
            regionFineVO = res.get(provinces.indexOf(punish.getProvince()));
            regionFineVO.setFine(regionFineVO.getFine()+punish.getFine());
            regionFineVO.setValue(regionFineVO.getValue()+1);


        }
        res.get(provinces.indexOf("香港特别行政区")).setName("香港");
        res.get(provinces.indexOf("内蒙")).setName("内蒙古");
        for(RegionFineVO regionFineVO:res){
            regionFineVO.setFine(regionFineVO.getFine()/10000);
        }
        return res;
    }


//  以及 全国地理位置punishment
}
