package com.demo.spring.boot.bo;

/**
 * Created by bqhuy on 3/15/2018.
 */
public class Site {
    private String siteCode;
    private String siteName;
    private String siteType;

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String toString() {
        return new StringBuilder("Site={")
                .append("siteCode=").append(siteCode)
                .append(", siteName=").append(siteName)
                .append(", siteType=").append(siteType)
                .append("}").toString();
    }
}
