package de.fhb.view;

import de.fhb.model.StationVo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Notebook on 23.11.2015.
 */
public class MonitorInsertDataView extends AMonitorView implements Initializable {
    @FXML
    private ListView<StationVo> stationListView;
    @FXML
    private TextField stationIDTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField targetTextField;
    @FXML
    private TextField actualTextField;
    @FXML
    private TextField varianceTextField;
    @FXML
    private Button changeViewBtn;
    @FXML
    private Button saveBtn;

    //************************//
    //Methoden//
    //***********************//

    public void initialize(URL location, ResourceBundle resources) {
        EnterEventHandler enterHandler = new EnterEventHandler();

        this.actualTextField.setOnKeyReleased(enterHandler);
        this.stationIDTextField.setOnKeyReleased(enterHandler);
        this.dateTextField.setOnKeyReleased(enterHandler);

        this.stationListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StationVo>() {
            @Override
            public void changed(ObservableValue<? extends StationVo> observable, StationVo oldValue, StationVo newValue) {
                if (newValue != null) {
                    MonitorInsertDataView.this.stationIDTextField.setText(newValue.getName());
                    MonitorInsertDataView.this.targetTextField.setText(String.valueOf(newValue.getTargetValue()));
                    if (newValue.getDate() != null) {
                        MonitorInsertDataView.this.dateTextField.setText(new SimpleDateFormat("dd.MM.yyyy").format(newValue.getDate()));
                    } else {
                        MonitorInsertDataView.this.dateTextField.setText("");
                    }
                    if (newValue.getActualValue() != null) {
                        MonitorInsertDataView.this.actualTextField.setText(String.valueOf(newValue.getActualValue()));
                    } else {
                        MonitorInsertDataView.this.actualTextField.setText("");
                    }

                    if (newValue.getVariance() != null) {
                        MonitorInsertDataView.this.varianceTextField.setText(String.valueOf(newValue.getVariance()));
                    } else {
                        MonitorInsertDataView.this.varianceTextField.setText("");
                    }

                    MonitorInsertDataView.this.dateTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode().equals(KeyCode.ENTER)) {
                                try {
                                    newValue.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(dateTextField.getText()));
                                    listener.onDataChanged(newValue);
                                } catch (ParseException e) {
                                    // TODO: Fehlermeldung!
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    MonitorInsertDataView.this.actualTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER) {
                                newValue.setActualValue(Integer.parseInt(actualTextField.getText()));
                                listener.onDataChanged(newValue);
                            }
                        }
                    });
                    MonitorInsertDataView.this.saveBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                if (!stationIDTextField.getText().isEmpty()) {
                                    newValue.setName(stationIDTextField.getText());
                                }
                                if (!actualTextField.getText().isEmpty()) {
                                    newValue.setActualValue(Integer.parseInt(actualTextField.getText()));
                                }
                                if (!dateTextField.getText().isEmpty()) {
                                    try {
                                        newValue.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(dateTextField.getText()));
                                    } catch (ParseException e) {
                                        // TODO: Fehlermeldung!
                                        e.printStackTrace();
                                    }
                                }
                                listener.onDataChanged(newValue);
                            }
                        }
                    });
                }
            }
        });

        this.changeViewBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    listener.onViewChangeClicked();
                }
            }
        });
    }

    public void updateStationList(List<StationVo> list) {
        ObservableList<StationVo> observableList = FXCollections.observableArrayList(list);
        this.stationListView.setItems(observableList);
    }

    @FXML
    private void onMOuseClickedChooseStation() {

    }

    @FXML
    private void changeView() {

    }

    public MonitorInsertDataView(Object obj) {
        super(obj);
    }

    @Override
    public void updateViewFromModel() {

    }
}
