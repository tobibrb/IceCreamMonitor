package de.fhb.view;

import de.fhb.model.StationVo;
import javafx.fxml.Initializable;

import java.util.List;

public abstract class AMonitorView implements Initializable{

    protected ViewListener listener;

    public AMonitorView(Object obj) {
        onAttach(obj);
    }

    protected void onAttach(Object obj) {
        try {
            listener = (ViewListener) obj;
        } catch (Exception e) {
            throw new ClassCastException(String.format("%s must implement %s", obj.getClass().getSimpleName(),
                    ViewListener.class.getSimpleName()));
        }
    }

    public abstract void updateStationList(List<StationVo> list);


}
