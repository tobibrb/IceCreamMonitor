package de.fhb.view;

import de.fhb.model.StationVo;

/**
 * Interface zum erkenne von Änderung der View, wie Wechsel der View oder ändern der Daten durch den Benutzer über die View.
 */
public interface ViewListener {

    void onViewChangeClicked();

    void onDataChanged(StationVo station);
}
