package com.example.basecase.dataserver.dto;


public class queryBody {
    public static class queryFilter {
        public queryFilter(){
            this.date=new date();
        }
        public static class date{
            public long startDate;
            public long endDate;
        }
        public date date;
        public String tag;
        public String content;

    }
    public static class querySorter {
        public String date;
    }
    public queryBody(){
        this.filter=new queryFilter();
        this.sorter=new querySorter();
    }
    //获取文件的数量
    public int pageNum;
    //当前页码的
    public int currentPage;
    //分页尺寸
    public int currentPageSize;

    public queryFilter filter;
    public querySorter sorter;
    public String ToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("tag: ").append(this.filter.tag).append("\n");
        sb.append("content: ").append(this.filter.content).append("\n");
        sb.append("date: ").append(this.filter.date.endDate).append(" ").append(this.filter.date.startDate);
        return sb.toString();
    }
}
