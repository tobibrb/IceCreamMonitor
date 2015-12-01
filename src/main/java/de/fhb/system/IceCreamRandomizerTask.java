package de.fhb.system;

import de.fhb.model.IStationBo;
import de.fhb.model.StationBo;
import de.fhb.model.StationListener;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tobi on 01.12.2015.
 */
public class IceCreamRandomizerTask extends TimerTask implements StationListener{

    private static Timer timer = new Timer();
    private static boolean shouldRun = true;
    private static IStationBo stationBo;
    private static IceCreamRandomizerTask sInstance;

    private static final Logger log = LoggerFactory.getLogger(IceCreamRandomizerTask.class);

    public IceCreamRandomizerTask() {
        sInstance = this;
        stationBo = StationBo.getInstance(this);
    }

    public static void stop() {
        shouldRun = false;
        timer.cancel();
        sInstance.cancel();
    }

    @Override
    public void run() {
            Random rand = new Random();
            String name = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
            int randomNum = rand.nextInt((75 - 25) + 1) + 25;
            stationBo.addStation(name, randomNum);
            log.debug("Created Station: " + name);
        if (shouldRun) {
            int delay = (5 + new Random().nextInt(5)) * 1000;
            timer.schedule(new IceCreamRandomizerTask(), delay);
        }

    }

    @Override
    public void onStationChanged() {

    }
}
