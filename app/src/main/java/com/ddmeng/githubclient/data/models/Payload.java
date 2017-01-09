package com.ddmeng.githubclient.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payload {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("push_id")
    @Expose
    private Integer pushId;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("distinct_size")
    @Expose
    private Integer distinctSize;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("head")
    @Expose
    private String head;
    @SerializedName("before")
    @Expose
    private String before;
    @SerializedName("commits")
    @Expose
    private List<Commit> commits = null;
    @SerializedName("forkee")
    @Expose
    private Forkee forkee;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getPushId() {
        return pushId;
    }

    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDistinctSize() {
        return distinctSize;
    }

    public void setDistinctSize(Integer distinctSize) {
        this.distinctSize = distinctSize;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public Forkee getForkee() {
        return forkee;
    }

    public void setForkee(Forkee forkee) {
        this.forkee = forkee;
    }

}