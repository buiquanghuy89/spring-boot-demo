package com.demo.spring.boot.bo;

/**
 * Created by bqhuy on 3/14/2018.
 */
public class Meter {
    private String meterCode;
    private String meterName;
    private String meterType;
    private Integer numberOfWheel;
    private Site site;

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public Integer getNumberOfWheel() {
        return numberOfWheel;
    }

    public void setNumberOfWheel(Integer numberOfWheel) {
        this.numberOfWheel = numberOfWheel;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void init() {
        System.out.println("init meter: meterCode=" + meterCode);
    }

    public void destroy() {
        System.out.println("destroy meter: meterCode=" + meterCode);
    }

    public String toString() {
        return new StringBuilder("Meter={")
                .append("meterCode=").append(meterCode)
                .append(", meterName=").append(meterName)
                .append(", meterType=").append(meterType)
                .append(", numberOfWheel=").append(numberOfWheel)
                .append(", site=").append(site)
                .append("}").toString();
    }
}
