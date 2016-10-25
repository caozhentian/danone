package com.threeti.danone.common.bean;

import com.threeti.danone.common.model.Diary;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "notification".
 */
public class Notification extends Diary  {

    /** Not-null value. */
    private String appId;
    private String serverId;
    private String modifyReason;
    private String deleteReason;
    private String infantId;
    private String type;
    private String mode;
    private String interval;
    private Long time;
    private String diaryregId;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Notification() {
    }

    public Notification(String appId) {
        this.appId = appId;
    }

    public Notification(String appId, String serverId, String modifyReason, String deleteReason, String infantId, String type, String mode, String interval, Long time, String diaryregId) {
        this.appId = appId;
        this.serverId = serverId;
        this.modifyReason = modifyReason;
        this.deleteReason = deleteReason;
        this.infantId = infantId;
        this.type = type;
        this.mode = mode;
        this.interval = interval;
        this.time = time;
        this.diaryregId = diaryregId;
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

    public String getInfantId() {
        return infantId;
    }

    public void setInfantId(String infantId) {
        this.infantId = infantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDiaryregId() {
        return diaryregId;
    }

    public void setDiaryregId(String diaryregId) {
        this.diaryregId = diaryregId;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
