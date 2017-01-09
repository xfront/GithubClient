package com.ddmeng.githubclient.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Actor {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("display_login")
    @Expose
    private String displayLogin;
    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}