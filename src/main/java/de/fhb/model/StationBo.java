package de.fhb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tobi on 30.11.2015.
 */
public class StationBo implements IStationBo {

    private static List<StationVo> stationList = new ArrayList<>();
    private static List<StationListener> listeners = new ArrayList<>();
    private static StationBo sInstance;

    public static StationBo getInstance(Object obj) {
        if (sInstance == null) {
            sInstance = new StationBo(obj);
        } else {
            sInstance.onAttach(obj);
        }
        return sInstance;
    }

    public StationBo(Object obj) {
        onAttach(obj);
    }

    private void onAttach(Object obj) {
        try {
            StationListener listener = (StationListener) obj;
            listeners.add(listener);
        } catch (Exception e) {
            throw new ClassCastException(String.format("%s must implement %s", obj.getClass().getSimpleName(),
                    StationListener.class.getSimpleName()));
        }
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
        notifyListeners();
    }

    @Override
    public void updateStationValue(Long id, Integer actualValue) {
        StationVo updateStation = findStationById(id);
        updateStation.setActualValue(actualValue);
        updateStation.setVariance(updateStation.getActualValue() - updateStation.getTargetValue());
        notifyListeners();
    }

    @Override
    public void updateStationName(Long id, String name) {
        StationVo updateStation = findStationById(id);
        updateStation.setName(name);
        notifyListeners();
    }

    @Override
    public void addStation(String name, Integer targetValue) {
        stationList.add(new StationVo(name, targetValue));
        notifyListeners();
    }

    private void notifyListeners() {
        for (StationListener listener : listeners) {
            listener.onStationChanged();
        }
    }
}
