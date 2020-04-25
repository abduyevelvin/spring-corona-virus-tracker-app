package com.corona.model;

public class DeathLocationStats {

    private String state;
    private String country;
    private int latestDeathTotalCases;
    private int currentDeathCases;

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

    public int getLatestDeathTotalCases() {
        return latestDeathTotalCases;
    }

    public void setLatestDeathTotalCases(int latestDeathTotalCases) {
        this.latestDeathTotalCases = latestDeathTotalCases;
    }

    public int getCurrentDeathCases() {
        return currentDeathCases;
    }

    public void setCurrentDeathCases(int currentDeathCases) {
        this.currentDeathCases = currentDeathCases;
    }

    @Override
    public String toString() {
        return "ConfirmedLocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCase=" + latestDeathTotalCases +
                '}';
    }
}
