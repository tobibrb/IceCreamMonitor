package de.fhb.view;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by Notebook on 23.11.2015.
 */
public class EnterEventHandler implements EventHandler<KeyEvent> {
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
            //TODO handle Event
            System.out.println("Hallo dies ist ein Test");

    }
}
