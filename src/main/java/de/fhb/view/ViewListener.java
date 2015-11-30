package de.fhb.view;

import de.fhb.model.StationVo;

/**
 * Created by Notebook on 23.11.2015.
 */
public interface ViewListener {

    void onViewChangeClicked();

    void onDataChanged(StationVo station);
}
