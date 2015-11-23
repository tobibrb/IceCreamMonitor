package de.fhb.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Notebook on 23.11.2015.
 */
public class MonitorInsertDataView extends AMonitorView implements Initializable{
    @FXML
    private ListView stationListView;
    @FXML
    private TextField stationIDTextField = new TextField();
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField actualTextField;
    @FXML
    private TextField varianceTextField;
    @FXML
    private Button changeViewBtn;

    //************************//
    //Methoden//
    //***********************//

    public void initialize(URL location, ResourceBundle resources) {
        EnterEventHandler enterHandler = new EnterEventHandler();

        this.actualTextField.setOnKeyReleased(enterHandler);
        this.stationIDTextField.setOnKeyReleased(enterHandler);
        this.dateTextField.setOnKeyReleased(enterHandler);
    }


    @FXML
    private void onMOuseClickedChooseStation(){

    }
    @FXML
    private void changeView(){

    }

    public MonitorInsertDataView(Object obj) {
    super(obj);
    }

    @Override
    public void updateViewFromModel() {

    }
}
