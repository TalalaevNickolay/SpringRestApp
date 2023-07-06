package ru.alishev.springcourse.FirstRestApp.models;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Measurements")
public class Measurement implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "value")
    @DecimalMax(value = "100", message = "Value should be less than 100")
    @DecimalMin(value = "-100", message = "Value should be more than -100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Raining can't be empty")
    private Boolean raining;

    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="sensor", nullable=false, updatable=false, referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "time")
    private Date time;

    public Measurement() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
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
