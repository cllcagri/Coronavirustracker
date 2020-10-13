package com.tracker.coronavirustracker.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CovidDataModel implements Serializable {

    private int cityCode;
    private String cityName;
    private long caseCount;
    private long deathCount;
    private String date;

    public CovidDataModel(){}

    public CovidDataModel(int cityCode, String cityName, long caseCount, long deathCount, String date) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.caseCount = caseCount;
        this.deathCount = deathCount;
        this.date = date;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(long caseCount) {
        this.caseCount = caseCount;
    }

    public long getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(long deathCount) {
        this.deathCount = deathCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CovidDataModel)) return false;
        CovidDataModel that = (CovidDataModel) o;
        return getCityCode() == that.getCityCode() &&
                getCaseCount() == that.getCaseCount() &&
                getDeathCount() == that.getDeathCount() &&
                Objects.equals(getCityName(), that.getCityName()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityCode(), getCityName(), getCaseCount(), getDeathCount(), getDate());
    }

    @Override
    public String toString() {
        return "covidDataModel{" +
                "cityCode=" + cityCode +
                ", cityName='" + cityName + '\'' +
                ", caseCount=" + caseCount +
                ", deathCount=" + deathCount +
                ", date=" + date +
                '}';
    }
}
