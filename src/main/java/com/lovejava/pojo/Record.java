package com.lovejava.pojo;

import java.util.Date;

public class Record {
    private Integer rid;

    private Integer uid;

    private String singleList;

    private String multiList;

    private String judgeList;

    private String saqList;

    private Date startTime;

    private Date endTime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSingleList() {
        return singleList;
    }

    public void setSingleList(String singleList) {
        this.singleList = singleList == null ? null : singleList.trim();
    }

    public String getMultiList() {
        return multiList;
    }

    public void setMultiList(String multiList) {
        this.multiList = multiList == null ? null : multiList.trim();
    }

    public String getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(String judgeList) {
        this.judgeList = judgeList == null ? null : judgeList.trim();
    }

    public String getSaqList() {
        return saqList;
    }

    public void setSaqList(String saqList) {
        this.saqList = saqList == null ? null : saqList.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", singleList='" + singleList + '\'' +
                ", multiList='" + multiList + '\'' +
                ", judgeList='" + judgeList + '\'' +
                ", saqList='" + saqList + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}