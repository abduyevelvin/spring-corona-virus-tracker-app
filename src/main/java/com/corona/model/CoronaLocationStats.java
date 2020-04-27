package com.corona.model;

public class CoronaLocationStats {

    private String state;
    private String country;
    private int confirmedCases;
    private int deathCases;
    private int recoveredCases;

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

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public int getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(int deathCases) {
        this.deathCases = deathCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(int recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    @Override
    public String toString() {
        return "CoronaLocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", confirmedCases=" + confirmedCases +
                ", deathCases=" + deathCases +
                ", recoveredCases=" + recoveredCases +
                '}';
    }
}
