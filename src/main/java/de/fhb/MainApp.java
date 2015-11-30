package de.fhb;

import de.fhb.presenter.Presenter;
import de.fhb.system.IceCreamRandomizer;

public class MainApp {

    public static void main(String[] args) throws Exception {
        new Thread(new IceCreamRandomizer()).start();
        Presenter.getInstance();
    }
}
