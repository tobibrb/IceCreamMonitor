package de.fhb.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Tobi on 30.11.2015.
 */
public interface IStationBo {

    public List<StationVo> findAll();

    public StationVo findStationById(Long id);

    public void updateStationDate(Long id, Date date);

    public void updateStationValue(Long id, Integer actualValue);

    public void updateStationName(Long id, String name);

    public void addStation(String name, Integer targetValue);

}
