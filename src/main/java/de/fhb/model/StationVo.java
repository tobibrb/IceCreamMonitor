package de.fhb.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tobias Bartz on 23.11.2015.
 */
public class StationVo implements Serializable {

    private static Long nextId = 0L;
    private Long id;
    private String name;
    private Float targetValue;
    private Float actualValue;
    private Integer variance;
    private Date date;

    public StationVo(String name, Float targetValue, Float actualValue, Integer variance, Date date) {
        this(name, targetValue);
        this.actualValue = actualValue;
        this.variance = variance;
        this.date = date;
    }

    public StationVo(String name, Float targetValue) {
        this.id = nextId;
        this.name = name;
        this.targetValue = targetValue;
        this.date = new Date();
        nextId++;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getTargetValue() {
        return targetValue;
    }

    public Float getActualValue() {
        return actualValue;
    }

    public Integer getVariance() {
        return variance;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActualValue(Float actualValue) {
        this.actualValue = actualValue;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
