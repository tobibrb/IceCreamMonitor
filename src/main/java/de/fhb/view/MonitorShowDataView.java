package de.fhb.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tobi on 30.11.2015.
 */
public class MonitorShowDataView extends AMonitorView implements Initializable {

    @FXML
    private Button changeViewBtn;
    @FXML
    private TableView tabelView;
    @FXML
    private TableColumn tableColumName;
    @FXML
    private TableColumn tableColumDate;
    @FXML
    private TableColumn tableColumTarget;
    @FXML
    private TableColumn tableColumActual;
    @FXML
    private TableColumn tableColumVariance;


    public MonitorShowDataView(Object obj) {
        super(obj);
    }

    @Override
    public void updateViewFromModel() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        EnterEventHandler enterHandler = new EnterEventHandler();

        this.tableColumDate.setOnEditCommit(enterHandler);
        this.tableColumActual.setOnEditCommit(enterHandler);
        this.tableColumName.setOnEditCommit(enterHandler);

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
