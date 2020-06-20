package com.developerhvg24.drcovid;

import java.util.List;

public class Whole_India_Data {

    private String activeCases,recovered,deaths,totalCases;
    private String sourceUrl,lastUpdatedAtApify,readMe;
    private List<State_Wise_Data> regionData;

    public String getActiveCases() {
        return activeCases;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getLastUpdatedAtApify() {
        return lastUpdatedAtApify;
    }

    public String getReadMe() {
        return readMe;
    }

    public List<State_Wise_Data> getRegionData() {
        return regionData;
    }
}
