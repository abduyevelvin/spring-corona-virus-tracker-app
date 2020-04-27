package com.corona.controller;

import com.corona.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/all")
public class AllInOneController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/home")
    public String homePage() {
        return "redirect:/all/";
    }

    @GetMapping("/")
    public String allHome(Model model) throws IOException, InterruptedException {
        int totalConfirmedCases = coronaVirusDataService.getConfirmedTotalCases();
        int totalDeathCases = coronaVirusDataService.getDeathTotalCases();
        int totalRecoveredCases = coronaVirusDataService.getRecoveredTotalCases();
        model.addAttribute("allLocationStats", coronaVirusDataService.fetchAllData());
        model.addAttribute("totalConfirmedCases", totalConfirmedCases);
        model.addAttribute("totalDeathCases", totalDeathCases);
        model.addAttribute("totalRecoveredCases", totalRecoveredCases);

        return "allInOneHome";
    }
}
