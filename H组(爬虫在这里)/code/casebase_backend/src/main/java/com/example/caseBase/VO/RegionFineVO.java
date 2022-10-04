package com.example.caseBase.VO;

public class RegionFineVO {
    public RegionFineVO(String name) {
        this.name = name;
        this.fine=0;
        this.value=0;
    }

    //省份名，写死的。
    private String name;
    //罚单数量
    int value;
    //罚单总额
    long fine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getFine() {
        return fine;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }
}
