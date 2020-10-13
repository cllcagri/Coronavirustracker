package com.tracker.coronavirustracker.service;
import com.tracker.coronavirustracker.models.CovidDataModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronaVirusDataService {
    private static final String CORONA_DATA_CSV = "https://raw.githubusercontent.com/denizalti/covid-19-data-turkey/master/city-data.csv";

    @Scheduled(cron = "* * 1 * * *")
    public List<CovidDataModel> getCoronaVirusData() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_DATA_CSV)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader reader = new StringReader(response.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            List<CovidDataModel> covidDataList = new ArrayList<>();
            CovidDataModel covidDataModel;
            for(CSVRecord rec: records){
                covidDataModel = new CovidDataModel();
                covidDataModel.setCityCode(Integer.parseInt(rec.get("citycode")));
                covidDataModel.setCityName(rec.get("cityname"));
                covidDataModel.setCaseCount(Long.parseLong(rec.get("cases")));
                covidDataModel.setDeathCount(Long.parseLong(rec.get("deaths")));
                covidDataModel.setDate(rec.get("date"));
                covidDataList.add(covidDataModel);
            }
            return covidDataList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public long getTotalCase(){
        List<CovidDataModel> covidModelList  = getCoronaVirusData();
        long totalCase = 0;
        for(CovidDataModel covidDataModel : covidModelList){
            totalCase += covidDataModel.getCaseCount();
        }

        return totalCase;
    }

    public long getTotalDeath(){
        List<CovidDataModel> covidModelList  = getCoronaVirusData();
        long totalDeath = 0;
        for(CovidDataModel covidDataModel : covidModelList){
            totalDeath += covidDataModel.getDeathCount();
        }

        return totalDeath;
    }
}
