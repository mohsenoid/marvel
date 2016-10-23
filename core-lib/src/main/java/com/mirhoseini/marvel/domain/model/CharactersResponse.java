package com.mirhoseini.marvel.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CharactersResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private String status;
    @SerializedName("attributionText")
    private String attributionText;
    @SerializedName("etag")
    private String eTag;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("attributionHTML")
    private String attributionHTML;
    @SerializedName("data")
    private Data data;

    public CharactersResponse() {
    }

    public CharactersResponse(int code, String status, String attributionText, String eTag, String copyright, String attributionHTML, Data data) {
        this.code = code;
        this.status = status;
        this.attributionText = attributionText;
        this.eTag = eTag;
        this.copyright = copyright;
        this.attributionHTML = attributionHTML;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
