package com.corona.controller;

import com.corona.service.CoronaVirusConfirmedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusConfirmedDataService coronaVirusConfirmedDataService;

    @GetMapping("/")
    public String home(Model model) {
        int totalConfirmedCases = coronaVirusConfirmedDataService.getConfirmedTotalCases();
        int dailyConfirmedCases = coronaVirusConfirmedDataService.getDailyConfirmedCases();
        model.addAttribute("confirmedLocationStats", coronaVirusConfirmedDataService.getAllConfirmedStats());
        model.addAttribute("totalConfirmedCases", totalConfirmedCases);
        model.addAttribute("dailyConfirmedCases", dailyConfirmedCases);
        return "home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "redirect:/";
    }

    @GetMapping("/death")
    public String death(Model model) {
        int totalDeathCases = coronaVirusConfirmedDataService.getDeathTotalCases();
        int dailyDeathCases = coronaVirusConfirmedDataService.getDailyDeathCases();
        model.addAttribute("deathLocationStats", coronaVirusConfirmedDataService.getAllDeathStats());
        model.addAttribute("totalDeathCases", totalDeathCases);
        model.addAttribute("dailyDeathCases", dailyDeathCases);
        return "death";
    }

    @GetMapping("/recovered")
    public String recovered(Model model) {
        int totalRecoveredCases = coronaVirusConfirmedDataService.getRecoveredTotalCases();
        int dailyRecoveredCases = coronaVirusConfirmedDataService.getDailyRecoveredCases();
        model.addAttribute("recoveredLocationStats", coronaVirusConfirmedDataService.getAllRecoveredStats());
        model.addAttribute("totalRecoveredCases", totalRecoveredCases);
        model.addAttribute("dailyRecoveredCases", dailyRecoveredCases);
        return "recovered";
    }
}
