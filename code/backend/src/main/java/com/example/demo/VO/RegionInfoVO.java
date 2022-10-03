package com.example.demo.VO;

import com.example.demo.enums.ProvinceType;

import java.util.Date;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/1 17:17
 */
public class RegionInfoVO {

    /**
     * 开始时间
     */
    private Date start_date;

    /**
     * 结束时间
     */
    private Date end_date;

    /**
     * 地区
     */
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
}
