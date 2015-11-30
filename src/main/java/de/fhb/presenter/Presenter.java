package de.fhb.presenter;

import de.fhb.model.IStationBo;
import de.fhb.model.StationBo;
import de.fhb.model.StationListener;
import de.fhb.system.IceCreamRandomizer;
import de.fhb.view.AMonitorView;
import de.fhb.view.MonitorInsertDataView;
import de.fhb.view.MonitorShowDataView;
import de.fhb.view.ViewListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tobi on 30.11.2015.
 */
public class Presenter extends Application implements ViewListener, StationListener {

    private static final Logger log = LoggerFactory.getLogger(Presenter.class);

    private static Presenter sInstance;
    private IStationBo stationBo;
    private AMonitorView monitorView;
    private Stage primaryStage;
    private int viewNumber = 1;

    public Presenter() {
    }

    public Presenter(String[] args) {
        super();
        this.stationBo = new StationBo(this);
        launch(args);
    }

    public static Presenter getInstance() {
        if (sInstance == null) {
            return new Presenter(null);
        } else {
            return sInstance;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        log.info("Starting IceCreamMonitor application");
        changeView();
        new Thread(new IceCreamRandomizer()).start();
    }

    private void changeView() throws Exception{
        if (viewNumber == 0) {

            String fxmlFile = "/fxml/monitorShowDataView.fxml";
            log.debug(String.format("Loading FXML for MonitorShowDataView from: %s", fxmlFile));
            FXMLLoader loader = new FXMLLoader();
            this.monitorView = new MonitorShowDataView(this);
            loader.setController(this.monitorView);
            Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

            log.debug("Showing MonitorShowDataView");
            Scene scene = new Scene(rootNode, 800, 600);
            scene.getStylesheets().add("/styles/styles.css");
            this.primaryStage.setTitle("Show Data View");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

            viewNumber = 1;
        } else if (viewNumber == 1) {

            String fxmlFile = "/fxml/monitorInsertDataView.fxml";
            log.debug(String.format("Loading FXML for MonitorInsertDataView from: %s", fxmlFile));;
            FXMLLoader loader = new FXMLLoader();
            this.monitorView = new MonitorInsertDataView(this);
            loader.setController(this.monitorView);
            Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

            log.debug("Showing MonitorInsertDataView");
            Scene scene = new Scene(rootNode, 800, 600);
            scene.getStylesheets().add("/styles/styles.css");

            this.primaryStage.setTitle("Insert Data View");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            viewNumber = 0;
        }
    }

    // Methods for ViewListener
    @Override
    public void onViewChangeClicked() {
        try {
            changeView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDataChanged() {

    }

    // Methods for StationListener
    @Override
    public void onStationChanged() {
        log.debug("Daten geändert.");
        this.monitorView.updateStationList(stationBo.findAll());
    }
}
