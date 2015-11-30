package de.fhb.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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

        this.changeViewBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    listener.onViewChangeClicked();
                }
            }
        });
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
