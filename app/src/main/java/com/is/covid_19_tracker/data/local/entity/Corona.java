package com.is.covid_19_tracker.data.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Islam Elshnawey
 * @date 4/9/20
 * <p>
 * is.elshnawey@gmail.com
 **/
@Entity
public class Corona implements Parcelable  {


   /* {
            "updated": 1586461201611,
            "country": "Egypt",
            "countryInfo": {
                "_id": 818,
                "iso2": "EG",
                "iso3": "EGY",
                "lat": 27,
                "long": 30,
                "flag": "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/eg.png"
           },
            "cases": 1699,
            "todayCases": 139,
            "deaths": 118,
            "todayDeaths": 15,
            "recovered": 348,
            "active": 1233,
            "critical": 0,
            "casesPerOneMillion": 17,
            "deathsPerOneMillion": 1,
            "tests": 25000,
            "testsPerOneMillion": 244
    }*/

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("updated")
    @Expose
    private long updated;

    @SerializedName("country")
    @Expose
    private String country;

    @Expose
    @SerializedName("countryInfo")
    @Embedded(prefix = "countryInfo")
    public CountryInfo CountryInfo;

    @SerializedName("cases")
    @Expose
    private int cases;

    @SerializedName("todayCases")
    @Expose
    private int todayCases;

    @SerializedName("deaths")
    @Expose
    private int deaths;

    @SerializedName("todayDeaths")
    @Expose
    private int todayDeaths;

    @SerializedName("recovered")
    @Expose
    private int recovered;

    @SerializedName("active")
    @Expose
    private int active;

    @SerializedName("critical")
    @Expose
    private int critical;

    @SerializedName("casesPerOneMillion")
    @Expose
    private int casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    @Expose
    private int deathsPerOneMillion;

    @SerializedName("tests")
    @Expose
    private int tests;

    @SerializedName("testsPerOneMillion")
    @Expose
    private int testsPerOneMillion;

    public Corona() {
    }

    protected Corona(Parcel in) {
        id = in.readInt();
        updated = in.readLong();
        country = in.readString();
        cases = in.readInt();
        todayCases = in.readInt();
        deaths = in.readInt();
        todayDeaths = in.readInt();
        recovered = in.readInt();
        active = in.readInt();
        critical = in.readInt();
        casesPerOneMillion = in.readInt();
        deathsPerOneMillion = in.readInt();
        tests = in.readInt();
        testsPerOneMillion = in.readInt();
    }

    public static final Creator<Corona> CREATOR = new Creator<Corona>() {
        @Override
        public Corona createFromParcel(Parcel in) {
            return new Corona(in);
        }

        @Override
        public Corona[] newArray(int size) {
            return new Corona[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeLong(updated);
        parcel.writeString(country);
        parcel.writeInt(cases);
        parcel.writeInt(todayCases);
        parcel.writeInt(deaths);
        parcel.writeInt(todayDeaths);
        parcel.writeInt(recovered);
        parcel.writeInt(active);
        parcel.writeInt(critical);
        parcel.writeInt(casesPerOneMillion);
        parcel.writeInt(deathsPerOneMillion);
        parcel.writeInt(tests);
        parcel.writeInt(testsPerOneMillion);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public com.is.covid_19_tracker.data.local.entity.CountryInfo getCountryInfo() {
        return CountryInfo;
    }

    public void setCountryInfo(com.is.covid_19_tracker.data.local.entity.CountryInfo countryInfo) {
        CountryInfo = countryInfo;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(int casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(int deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public int getTests() {
        return tests;
    }

    public void setTests(int tests) {
        this.tests = tests;
    }

    public int getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(int testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public static Creator<Corona> getCREATOR() {
        return CREATOR;
    }
}
