package com.is.covid_19_tracker.data.local.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Islam Elshnawey on 4/9/20.
 * <p>
 * is.elshnawey@gmail.com
 **/
public class CountryInfo {

    /* "countryInfo": {
                "_id": 818,
                "iso2": "EG",
                "iso3": "EGY",
                "lat": 27,
                "long": 30,
                "flag": "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/eg.png"
    }*/

    @SerializedName("_id")
    @Expose
    public int _id;

    @SerializedName("iso2")
    @Expose
    public String iso2;

    @SerializedName("iso3")
    @Expose
    public String iso3;

    @SerializedName("lat")
    @Expose
    public double lat;

    @SerializedName("long")
    @Expose
    public double lng;

    @SerializedName("flag")
    @Expose
    public String flag;


}
