package ru.alishev.springcourse.FirstRestApp.dto;

import ru.alishev.springcourse.FirstRestApp.models.Sensor;

public class MeasurementDTO {

    private double value;
    private boolean raining;
    private Sensor sensor;

    public MeasurementDTO(Double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
