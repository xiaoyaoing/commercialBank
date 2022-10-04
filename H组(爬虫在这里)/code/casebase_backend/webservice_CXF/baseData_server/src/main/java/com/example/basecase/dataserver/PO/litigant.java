package com.example.basecase.dataserver.PO;


import lombok.Data;

@Data
public class litigant {
    String name;
    String unit;
    String chargeMan;
    public litigant(String name) {
        this.name = name;
    }

    public litigant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }
    public litigant(String name,String str2, int type){
        this.name=name;
        if(type==0){
            //create a litigant as a person.
            this.unit=str2;
        }else{
            //create a litigant as an organization.
            this.chargeMan=str2;
        }
    }
}
