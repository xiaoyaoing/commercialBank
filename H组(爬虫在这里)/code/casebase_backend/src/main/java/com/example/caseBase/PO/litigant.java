package com.example.caseBase.PO;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="litigant")
public class litigant implements Serializable {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String name;
    String province;
    String type;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
