package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.dto.SensorDTO;
import ru.alishev.springcourse.FirstRestApp.models.Measurement;
import ru.alishev.springcourse.FirstRestApp.services.MeasurementService;
import ru.alishev.springcourse.FirstRestApp.services.SensorService;
import ru.alishev.springcourse.FirstRestApp.util.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/api")
public class FirstRESTController {

    private SensorService sensorService;
    private MeasurementService measurementService;

    public FirstRESTController(SensorService sensorService, MeasurementService measurementService) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
    }

    @PostMapping("/sensors/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDto) {
        if(sensorService.findByName(sensorDto.getName()) != null){
            throw new SensorException();
        }
        sensorService.save(sensorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO){
        measurementService.save(measurementDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/measurements")
    public List<Measurement> getAllMeasurements(){

        return measurementService.findAll();
    }

    @GetMapping("/measurements/rainyDaysCount")
    public Integer rainyDaysCount(){
        return measurementService.countRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse("This sensor already exists!");
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse("Sensor for measurement not found!");
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ConstraintViolationExceptionResponse> handleException(ConstraintViolationException e){
        ConstraintViolationExceptionResponse exceptionResponse = new ConstraintViolationExceptionResponse(new ArrayList<>(e.getConstraintViolations()).get(0).getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
