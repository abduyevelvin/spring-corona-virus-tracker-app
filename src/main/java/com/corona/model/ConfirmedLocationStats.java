package com.corona.model;

public class ConfirmedLocationStats {

    private String state;
    private String country;
    private int latestConfirmedTotalCases;
    private int currentConfirmedCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestConfirmedTotalCases() {
        return latestConfirmedTotalCases;
    }

    public void setLatestConfirmedTotalCases(int latestConfirmedTotalCases) {
        this.latestConfirmedTotalCases = latestConfirmedTotalCases;
    }

    public int getCurrentConfirmedCases() {
        return currentConfirmedCases;
    }

    public void setCurrentConfirmedCases(int currentConfirmedCases) {
        this.currentConfirmedCases = currentConfirmedCases;
    }

    @Override
    public String toString() {
        return "ConfirmedLocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCase=" + latestConfirmedTotalCases +
                '}';
    }
}
