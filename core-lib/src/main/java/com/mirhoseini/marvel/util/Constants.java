package com.mirhoseini.marvel.util;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class Constants {

    public static final String PUBLIC_KEY = "785eccfcbab3a76d09c8f02f12bd5ef3";
    public static final String PRIVATE_KEY = "989690d40838c28ff3c435acbdf30777ce785424";

    public static final String BASE_URL = "http://gateway.marvel.com:80/";

    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day

    public static final int CODE_OK = 200;
    
    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String STANDARD_XLARGE = "standard_xlarge";
}
