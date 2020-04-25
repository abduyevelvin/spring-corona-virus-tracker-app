package com.corona.model;

public class RecoveredLocationStats {

    private String state;
    private String country;
    private int latestRecoveredTotalCases;
    private int currentRecoveredCases;

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

    public int getLatestRecoveredTotalCases() {
        return latestRecoveredTotalCases;
    }

    public void setLatestRecoveredTotalCases(int latestRecoveredTotalCases) {
        this.latestRecoveredTotalCases = latestRecoveredTotalCases;
    }

    public int getCurrentRecoveredCases() {
        return currentRecoveredCases;
    }

    public void setCurrentRecoveredCases(int currentRecoveredCases) {
        this.currentRecoveredCases = currentRecoveredCases;
    }

    @Override
    public String toString() {
        return "ConfirmedLocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCase=" + latestRecoveredTotalCases +
                '}';
    }
}
