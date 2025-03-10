package br.com.danilo.parallel.service;

import br.com.danilo.parallel.model.Car;
import br.com.danilo.parallel.model.CommonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CarService implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(CarService.class);

    @Override
    public void process(CommonData commonData) {
        log.info("running in thread: {}", Thread.currentThread().getName());
        var car = getCar();
        commonData.setCar(car);
        log.info("Car: {} added", car);
    }

    public Car getCar() {
        return new Car(2, "GOL" + new Random().nextInt(9000) + 1000, 2000);
    }
}
