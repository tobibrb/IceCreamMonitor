package de.fhb.view;

import de.fhb.model.StationVo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Tobi on 30.11.2015.
 */
public class MonitorShowDataView extends AMonitorView implements Initializable {

    @FXML
    private Button changeViewBtn;
    @FXML
    private TableView<StationVo> tableView;
    @FXML
    private TableColumn<StationVo,String> tableColumName;
    @FXML
    private TableColumn<StationVo,String> tableColumDate;
    @FXML
    private TableColumn<StationVo,String> tableColumTarget;
    @FXML
    private TableColumn<StationVo,String> tableColumActual;
    @FXML
    private TableColumn<StationVo,String> tableColumVariance;


    public MonitorShowDataView(Object obj) {
        super(obj);
    }

    @Override
    public void updateViewFromModel() {

    }

    @Override
    public void updateStationList(List<StationVo> list) {
        ObservableList<StationVo> observableList = FXCollections.observableArrayList(list);
        tableView.setItems(observableList);

        tableColumName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StationVo, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StationVo, String> param) {
                return param.getValue().nameSSPProperty();
            }
        });
        tableColumDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StationVo, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StationVo, String> param) {
                return param.getValue().dateSSPProperty();
            }
        });
        tableColumTarget.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StationVo, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StationVo, String> param) {
                return param.getValue().targetSSPProperty();
            }
        });
        tableColumActual.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StationVo, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StationVo, String> param) {
                return param.getValue().actualSSPProperty();
            }
        });
        tableColumVariance.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StationVo, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StationVo, String> param) {
                return param.getValue().varianceSSPProperty();
            }
        });

        /*tableColumName.setCellValueFactory(
                new PropertyValueFactory<StationVo, String>("Name"));
        tableColumDate.setCellValueFactory(
                new PropertyValueFactory<StationVo, Date>("Date"));
        tableColumTarget.setCellValueFactory(
                new PropertyValueFactory<StationVo, Integer>("Target"));
        tableColumActual.setCellValueFactory(
                new PropertyValueFactory<StationVo, Integer>("Actual"));
        tableColumVariance.setCellValueFactory(
                new PropertyValueFactory<StationVo, Integer>("Variance"));
*/




    }

    public void initialize(URL location, ResourceBundle resources) {
        EnterEventHandler enterHandler = new EnterEventHandler();
    /*
        this.tableColumDate.setOnEditCommit(enterHandler);
        this.tableColumActual.setOnEditCommit(enterHandler);
        this.tableColumName.setOnEditCommit(enterHandler);
    */
        this.changeViewBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    listener.onViewChangeClicked();
                    changeViewBtn.setText("Clicked");
                }
            }
        });
    }

}
