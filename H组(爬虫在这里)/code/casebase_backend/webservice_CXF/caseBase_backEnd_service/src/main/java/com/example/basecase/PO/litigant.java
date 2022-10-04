package com.example.basecase.PO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class litigant {
    String name;
    String unit;
    String chargeMan;
    public litigant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public litigant(String name, String unit, String chargeMan) {
        this.name = name;
        this.unit = unit;
        this.chargeMan = chargeMan;
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
}
