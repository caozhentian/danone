package com.threeti.danone.common.bean;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "dm".
 */
public class Dm {

    /** Not-null value. */
    private String appId;
    private java.util.Date brthdat;
    private String diaryregId;
    private String sex;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Dm() {
    }

    public Dm(String appId) {
        this.appId = appId;
    }

    public Dm(String appId, java.util.Date brthdat, String diaryregId, String sex) {
        this.appId = appId;
        this.brthdat = brthdat;
        this.diaryregId = diaryregId;
        this.sex = sex;
    }

    /** Not-null value. */
    public String getAppId() {
        return appId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public java.util.Date getBrthdat() {
        return brthdat;
    }

    public void setBrthdat(java.util.Date brthdat) {
        this.brthdat = brthdat;
    }

    public String getDiaryregId() {
        return diaryregId;
    }

    public void setDiaryregId(String diaryregId) {
        this.diaryregId = diaryregId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
