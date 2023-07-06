package ru.alishev.springcourse.FirstRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.models.Measurement;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;
import ru.alishev.springcourse.FirstRestApp.repositories.MeasurementRepository;
import ru.alishev.springcourse.FirstRestApp.util.MeasurementException;
import ru.alishev.springcourse.FirstRestApp.util.SensorException;

import javax.management.ServiceNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(MeasurementDTO measurementDTO){
        ModelMapper mapper = new ModelMapper();
        Measurement measurement = mapper.map(measurementDTO, Measurement.class);
        measurement.setTime(new Date());
        Sensor sensor = sensorService.findByName(measurement.getSensor().getName());
        if(sensor == null) {
            throw new MeasurementException();
        }
        measurement.getSensor().setId(sensor.getId());
        measurementRepository.save(measurement);
    }

    public Integer countRainyDays() {
        return measurementRepository.countByRaining(true);
    }
}
