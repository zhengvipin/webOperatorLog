package com.springboot.service;

import com.springboot.dao.CarDao;
import com.springboot.domain.Car;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CarService {
    @Resource
    private CarDao carDao;

    public Optional<Car> findById(Integer id) {
        return carDao.findById(id);
    }

    public Car save(Car car) {
        return carDao.save(car);
    }
}
