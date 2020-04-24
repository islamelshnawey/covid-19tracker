package com.is.covid_19_tracker;

public class Config {

    /**
     * AppVersion
     * For your app, you need to change according based on your app version
     */
    public static String APP_VERSION = "1.0";

    /**
     * BASE URL
     */
    public static String BASE_URL = "https://corona.lmao.ninja/v2/";

    /**
     * APP Setting
     * Set false, your app is production
     * It will turn off the logging Process
     */
    public static boolean IS_DEVELOPMENT = true;

    /**
     * Loading Limit Count Setting
     */

    public static int NOTI_LIST_COUNT = 30;

    /**
     * Region playstore
     */
    public static String PLAYSTORE_MARKET_URL_FIX = "market://details?id=";
    public static String PLAYSTORE_HTTP_URL_FIX = "http://play.google.com/store/apps/details?id=";

    /**
     * Image Cache and Loading
     */
    public static int IMAGE_CACHE_LIMIT = 250; // Mb
    public static boolean PRE_LOAD_FULL_IMAGE = true;

    /**
     * Policy Url
     */
    public static String POLICY_URL = "";

    /**
     * URI Authority File
     */
    public static String AUTHORITYFILE = ".fileprovider";


}
