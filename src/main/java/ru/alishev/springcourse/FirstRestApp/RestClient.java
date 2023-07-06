package ru.alishev.springcourse.FirstRestApp;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.alishev.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.alishev.springcourse.FirstRestApp.models.Sensor;

import java.util.List;
import java.util.Random;

public class RestClient {

    public static void main(String[] args) {
        add1000Temperatures();
        get1000Temperatures();
    }

    private static void add1000Temperatures() {
        RestTemplate restTemplate = new RestTemplate();
        String addMeasurementsResource = "http://localhost:8080/api/measurements/add";
        Sensor sensor = new Sensor("1000sensor_" + Math.floor(Math.random()*10));
        restTemplate.postForEntity("http://localhost:8080/api/sensors/registration", sensor, Sensor.class);
        for (int i = 0; i < 1000; i++) {
            double random = Math.random();
            HttpEntity<MeasurementDTO> request = new HttpEntity<>(
                    new MeasurementDTO(200 * random - 100 * random, new Random().nextBoolean(), sensor));
            restTemplate.postForEntity(addMeasurementsResource, request, MeasurementDTO.class);
        }
    }

    private static List<MeasurementDTO>  get1000Temperatures() {
        RestTemplate restTemplate = new RestTemplate();
        String addMeasurementsResource = "http://localhost:8080/api/measurements";
        List<MeasurementDTO> measurements = restTemplate.getForObject(addMeasurementsResource, List.class);
        return measurements;
    }
}
