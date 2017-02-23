package com.mirhoseini.marvel.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class Data {

    @SerializedName("total")
    private int total;
    @SerializedName("limit")
    private int limit;
    @SerializedName("results")
    private Results[] results;
    @SerializedName("count")
    private int count;
    @SerializedName("offset")
    private int offset;

    public Data() {
    }

    public Data(int total, int limit, Results[] results, int count, int offset) {
        this.total = total;
        this.limit = limit;
        this.results = results;
        this.count = count;
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
