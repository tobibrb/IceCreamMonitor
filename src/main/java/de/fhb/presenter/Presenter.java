package de.fhb.presenter;

import de.fhb.model.IStationBo;
import de.fhb.model.StationBo;
import de.fhb.model.StationListener;
import de.fhb.model.StationVo;
import de.fhb.system.IceCreamRandomizerTask;
import de.fhb.view.AMonitorView;
import de.fhb.view.MonitorInsertDataView;
import de.fhb.view.MonitorShowDataView;
import de.fhb.view.ViewListener;
import eu.hansolo.enzo.notification.Notification;
import javafx.application.Application;
import javafx.application.Platform;
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
    private static AMonitorView monitorView;
    private Stage primaryStage;
    private int viewNumber = 1;

    public Presenter() {
        this.stationBo = StationBo.getInstance(this);
    }

    public Presenter(String[] args) {
        super();
        //this.stationBo = StationBo.getInstance(this);
        launch(args);
    }

    public static Presenter getInstance() {
        if (sInstance == null) {
            sInstance = new Presenter(null);
        }
        return sInstance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        log.info("Starting IceCreamMonitor application");
        changeView();
        new IceCreamRandomizerTask().run();

    }

    private void changeView() throws Exception {
        if (viewNumber == 0) {

            String fxmlFile = "/fxml/monitorShowDataView.fxml";
            log.debug(String.format("Loading FXML for MonitorShowDataView from: %s", fxmlFile));
            FXMLLoader loader = new FXMLLoader();
            monitorView = new MonitorShowDataView(this);
            loader.setController(monitorView);
            Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

            log.debug("Showing MonitorShowDataView");
            Scene scene = new Scene(rootNode, 800, 600);
            scene.getStylesheets().add("/styles/styles.css");
            this.primaryStage.setTitle("Show Data View");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

            viewNumber = 1;
            monitorView.updateStationList(stationBo.findAll());
        } else if (viewNumber == 1) {

            String fxmlFile = "/fxml/monitorInsertDataView.fxml";
            log.debug(String.format("Loading FXML for MonitorInsertDataView from: %s", fxmlFile));

            FXMLLoader loader = new FXMLLoader();
            monitorView = new MonitorInsertDataView(this);
            loader.setController(monitorView);
            Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

            log.debug("Showing MonitorInsertDataView");
            Scene scene = new Scene(rootNode, 800, 600);
            scene.getStylesheets().add("/styles/styles.css");

            this.primaryStage.setTitle("Insert Data View");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            viewNumber = 0;
            monitorView.updateStationList(stationBo.findAll());
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        IceCreamRandomizerTask.setShouldRun(false);

        Notification.Notifier.INSTANCE.stop();

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
    public void onDataChanged(StationVo station) {
        if (station.getDate() != null) {
            stationBo.updateStationDate(station.getId(), station.getDate());
        }
        if (station.getActualValue() != null) {
            stationBo.updateStationValue(station.getId(), station.getActualValue());
        }
        if (station.getName() != null) {
            stationBo.updateStationName(station.getId(), station.getName());
        }
        monitorView.updateStationList(stationBo.findAll());
    }

    // Methods for StationListener
    @Override
    public void onStationChanged() {
        log.debug("Daten geändert.");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                monitorView.updateStationList(stationBo.findAll());
                Notification.Notifier.INSTANCE.notifyInfo("Info", "New Station added");

            }
        });
    }
}
