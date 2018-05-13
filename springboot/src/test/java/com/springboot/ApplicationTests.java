package com.springboot;

import com.springboot.domain.Car;
import com.springboot.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Resource
    private CarService carService;

    @Test
    public void modify() {
        Car car = new Car();
        car.setId(1);
        car.setName("奔驰");
        car.setPrice(6000d);
        car.setCreateDate(new Date());
        Car newCar = carService.save(car);
        System.out.println(newCar);
    }
}
