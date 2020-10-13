package com.tracker.coronavirustracker.controllers;

import com.tracker.coronavirustracker.models.CovidDataModel;
import com.tracker.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@EnableScheduling
public class Covid19Controller {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @RequestMapping("/")
    public String getCovidData(Model model){
        List<CovidDataModel> covidDataModel = coronaVirusDataService.getCoronaVirusData();
        model.addAttribute("covidDataModel", covidDataModel);
        model.addAttribute("totalCaseCount", coronaVirusDataService.getTotalCase());
        model.addAttribute("totalDeathCount", coronaVirusDataService.getTotalDeath());

        return "coronaTrackerPage";
    }

}
