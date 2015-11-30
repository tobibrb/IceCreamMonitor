package de.fhb.presenter;

import de.fhb.model.IStationBo;
import de.fhb.view.AMonitorView;
import de.fhb.view.MonitorInsertDataView;
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
public class Presenter extends Application implements ViewListener {

    private static final Logger log = LoggerFactory.getLogger(Presenter.class);

    private static Presenter sInstance;
    private IStationBo stationBo;
    private AMonitorView view;

    public Presenter() {}

    public Presenter(String[] args) {
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
        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/monitorInsertDataView.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        MonitorInsertDataView monitor = new MonitorInsertDataView(this);
        loader.setController(monitor);
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 800, 600);
        scene.getStylesheets().add("/styles/styles.css");

        primaryStage.setTitle("Hello JavaFX and Maven");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Methods for ViewListener
    @Override
    public void onViewChangeClicked() {

    }

    @Override
    public void onDataChanged() {

    }
}
