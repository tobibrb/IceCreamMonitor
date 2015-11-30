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
    private Integer targetValue;
    private Integer actualValue;
    private Integer variance;
    private Date date;

    public StationVo(String name, Integer targetValue, Integer actualValue, Integer variance, Date date) {
        this(name, targetValue);
        this.actualValue = actualValue;
        this.variance = variance;
        this.date = date;
    }

    public StationVo(String name, Integer targetValue) {
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

    public Integer getTargetValue() {
        return targetValue;
    }

    public Integer getActualValue() {
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

    public void setActualValue(Integer actualValue) {
        this.actualValue = actualValue;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    protected void setVariance(Integer variance) {
        this.variance = variance;
    }

    @Override
    public String toString() {
        return getName();
    }
}
