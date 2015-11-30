package de.fhb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tobi on 30.11.2015.
 */
public class StationBo implements IStationBo {

    private static List<StationVo> stationList;

    public StationBo() {
        stationList = new ArrayList<>();
    }

    @Override
    public List<StationVo> findAll() {
        return stationList;
    }

    @Override
    public StationVo findStationById(Long id) {
        StationVo returnStation = null;
        boolean stationFound = false;
        int i = 0;
        while (!stationFound && stationList.size() > i) {
            StationVo station = stationList.get(i);
            if (id == station.getId()) {
                returnStation = station;
                stationFound = true;
            }
            i++;
        }
        return returnStation;
    }

    @Override
    public void updateStationDate(Long id, Date date) {
        StationVo updateStation = findStationById(id);
        updateStation.setDate(date);
    }

    @Override
    public void updateStationValue(Long id, Integer actualValue) {
        StationVo updateStation = findStationById(id);
        updateStation.setActualValue(actualValue);
        updateStation.setVariance(updateStation.getTargetValue() - updateStation.getActualValue());
    }

    @Override
    public void updateStationName(Long id, String name) {
        StationVo updateStation = findStationById(id);
        updateStation.setName(name);
    }

    @Override
    public void addStation(String name, Integer targetValue) {
        stationList.add(new StationVo(name, targetValue));
    }
}