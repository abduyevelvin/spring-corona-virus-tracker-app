package com.corona.service;

import com.corona.model.ConfirmedLocationStats;
import com.corona.model.DeathLocationStats;
import com.corona.model.RecoveredLocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusConfirmedDataService {
    private static final String CONFIRMED_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String DEATH_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static final String RECOVERED_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<ConfirmedLocationStats> allConfirmedStats = new ArrayList<>();
    private List<DeathLocationStats> allDeathStats = new ArrayList<>();
    private List<RecoveredLocationStats> allRecoveredStats = new ArrayList<>();

    public List<ConfirmedLocationStats> getAllConfirmedStats() {
        return allConfirmedStats;
    }

    public List<DeathLocationStats> getAllDeathStats() {
        return allDeathStats;
    }

    public List<RecoveredLocationStats> getAllRecoveredStats() {
        return allRecoveredStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchConfirmedData() throws IOException, InterruptedException {
        List<ConfirmedLocationStats> newConfirmedStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(CONFIRMED_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            ConfirmedLocationStats confirmedLocationStat = new ConfirmedLocationStats();
            confirmedLocationStat.setState(record.get("Province/State"));
            confirmedLocationStat.setCountry(record.get("Country/Region"));
            int allConfirmedTotalCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayConfirmedCases = Integer.parseInt(record.get(record.size() - 2));
            confirmedLocationStat.setLatestConfirmedTotalCases(allConfirmedTotalCases);
            confirmedLocationStat.setCurrentConfirmedCases(allConfirmedTotalCases - prevDayConfirmedCases);
            newConfirmedStats.add(confirmedLocationStat);
        }

        this.allConfirmedStats = newConfirmedStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDeathData() throws IOException, InterruptedException {
        List<DeathLocationStats> newDeathStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DEATH_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            DeathLocationStats deathLocationStat = new DeathLocationStats();
            deathLocationStat.setState(record.get("Province/State"));
            deathLocationStat.setCountry(record.get("Country/Region"));
            int allDeathTotalCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayDeathCases = Integer.parseInt(record.get(record.size() - 2));
            deathLocationStat.setLatestDeathTotalCases(allDeathTotalCases);
            deathLocationStat.setCurrentDeathCases(allDeathTotalCases - prevDayDeathCases);
            newDeathStats.add(deathLocationStat);
        }

        this.allDeathStats = newDeathStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchRecoveredData() throws IOException, InterruptedException {
        List<RecoveredLocationStats> newRecoveredStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(RECOVERED_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            RecoveredLocationStats recoveredLocationStat = new RecoveredLocationStats();
            recoveredLocationStat.setState(record.get("Province/State"));
            recoveredLocationStat.setCountry(record.get("Country/Region"));
            int allRecoveredTotalCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayRecoveredCases = Integer.parseInt(record.get(record.size() - 2));
            recoveredLocationStat.setLatestRecoveredTotalCases(allRecoveredTotalCases);
            recoveredLocationStat.setCurrentRecoveredCases(allRecoveredTotalCases - prevDayRecoveredCases);
            newRecoveredStats.add(recoveredLocationStat);
        }

        this.allRecoveredStats = newRecoveredStats;
    }

    public int getConfirmedTotalCases() {
        return this.allConfirmedStats.stream().mapToInt(stat -> stat.getLatestConfirmedTotalCases()).sum();
    }

    public int getDailyConfirmedCases() {
        return this.allConfirmedStats.stream().mapToInt(stat -> stat.getCurrentConfirmedCases()).sum();
    }

    public int getDeathTotalCases() {
        return this.allDeathStats.stream().mapToInt(stat -> stat.getLatestDeathTotalCases()).sum();
    }

    public int getDailyDeathCases() {
        return this.allDeathStats.stream().mapToInt(stat -> stat.getCurrentDeathCases()).sum();
    }

    public int getRecoveredTotalCases() {
        return this.allRecoveredStats.stream().mapToInt(stat -> stat.getLatestRecoveredTotalCases()).sum();
    }

    public int getDailyRecoveredCases() {
        return this.allRecoveredStats.stream().mapToInt(stat -> stat.getCurrentRecoveredCases()).sum();
    }

}
