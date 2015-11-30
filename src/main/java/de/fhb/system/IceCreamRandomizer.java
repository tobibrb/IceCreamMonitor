package de.fhb.system;

import de.fhb.model.IStationBo;
import de.fhb.model.StationBo;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Tobi on 30.11.2015.
 */
public class IceCreamRandomizer implements Runnable {

    private IStationBo stationBo;
    private static final Logger log = LoggerFactory.getLogger(IceCreamRandomizer.class);

    public boolean shouldRun;

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public IceCreamRandomizer() {
        this.stationBo = new StationBo();
        shouldRun = true;
    }

    @Override
    public void run() {

        while (shouldRun) {
            String name = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
            Random rand = new Random();
            int randomNum = rand.nextInt((75 - 25) + 1) + 25;
            stationBo.addStation(name, randomNum);
            log.debug("Created Station: " + name);
            try {
                sleep(Math.abs(rand.nextLong()) % 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}