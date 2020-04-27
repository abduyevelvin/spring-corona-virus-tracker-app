package com.corona.service;

import com.corona.model.CoronaLocationStats;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static final String CORONA_DATA_URL = "https://raw.githubusercontent.com/datasets/covid-19/master/data/time-series-19-covid-combined.csv";

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public List<CoronaLocationStats> fetchAllData() throws IOException, InterruptedException {
        List<CoronaLocationStats> allStats = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(CORONA_DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);

        int confirmed = 0;
        int deaths = 0;
        int recovered = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        now = now.minusDays(1);

        for (CSVRecord record : records) {
            if (dtf.format(now).equals(record.get("Date"))) {
                CoronaLocationStats coronaLocationStat = new CoronaLocationStats();
                coronaLocationStat.setState(record.get("Province/State"));
                coronaLocationStat.setCountry(record.get("Country/Region"));
                try {
                    if (record.get("Confirmed") != null) {
                        confirmed = Integer.parseInt(record.get("Confirmed"));
                    }
                    if (record.get("Deaths") != null) {
                        deaths = Integer.parseInt(record.get("Deaths"));
                    }
                    if (record.get("Recovered") != null) {
                        recovered = Integer.parseInt(record.get("Recovered"));
                    }
                } catch (NumberFormatException e) {
                    confirmed = 0;
                    deaths = 0;
                    recovered = 0;
                }
                coronaLocationStat.setConfirmedCases(confirmed);
                coronaLocationStat.setDeathCases(deaths);
                coronaLocationStat.setRecoveredCases(recovered);
                allStats.add(coronaLocationStat);
            }
        }

        return allStats;
    }

    public int getConfirmedTotalCases() throws IOException, InterruptedException {
        return this.fetchAllData().stream().mapToInt(stat -> stat.getConfirmedCases()).sum();
    }

    public int getDeathTotalCases() throws IOException, InterruptedException {
        return this.fetchAllData().stream().mapToInt(stat -> stat.getDeathCases()).sum();
    }

    public int getRecoveredTotalCases() throws IOException, InterruptedException {
        return this.fetchAllData().stream().mapToInt(stat -> stat.getRecoveredCases()).sum();
    }
}
