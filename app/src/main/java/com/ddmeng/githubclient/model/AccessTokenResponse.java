package com.ddmeng.githubclient.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AccessTokenResponse {
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    @SerializedName("error_uri")
    @Expose
    private String errorUri;

    /**
     * @return The accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken The access_token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return The tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * @param tokenType The token_type
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * @return The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return The error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return The errorDescription
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription The error_description
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * @return The errorUri
     */
    public String getErrorUri() {
        return errorUri;
    }

    /**
     * @param errorUri The error_uri
     */
    public void setErrorUri(String errorUri) {
        this.errorUri = errorUri;
    }

}

