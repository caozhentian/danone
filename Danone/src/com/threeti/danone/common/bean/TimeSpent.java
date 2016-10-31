package com.threeti.danone.common.bean;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "timespent".
 */
public class TimeSpent extends Diary  {

    /** Not-null value. */
    private String appId;
    private String serverId;
    private String modifyReason;
    private String deleteReason;
    /** Not-null value. */
    private String infantId;
    private Integer status;
    /** Not-null value. */
    private java.util.Date ddat;
    private String type;
    private Integer time;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public TimeSpent() {
    }

    public TimeSpent(String appId) {
        this.appId = appId;
    }

    public TimeSpent(String appId, String serverId, String modifyReason, String deleteReason, String infantId, Integer status, java.util.Date ddat, String type, Integer time) {
        this.appId = appId;
        this.serverId = serverId;
        this.modifyReason = modifyReason;
        this.deleteReason = deleteReason;
        this.infantId = infantId;
        this.status = status;
        this.ddat = ddat;
        this.type = type;
        this.time = time;
    }

    /** Not-null value. */
    public String getAppId() {
        return appId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    /** Not-null value. */
    public String getInfantId() {
        return infantId;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setInfantId(String infantId) {
        this.infantId = infantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /** Not-null value. */
    public java.util.Date getDdat() {
        return ddat;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDdat(java.util.Date ddat) {
        this.ddat = ddat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
