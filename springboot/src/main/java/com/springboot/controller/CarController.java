package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.domain.ChangeLog;
import com.springboot.domain.CustomType;
import com.springboot.service.CarService;
import com.springboot.utils.DomainUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Resource
    private CarService carService;

    @Resource
    private DomainUtils<Car> domainUtils;

    @GetMapping("/getCar")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        Optional<Car> car = carService.findById(id);
        assert car.isPresent();
        return ResponseEntity.ok(car.get());
    }

    @PostMapping("/modifyCar")
    public ResponseEntity<?> modifyCar(Car newCar) throws Exception {
        CustomType customType = new CustomType(200, "修改成功！");
        // 编辑前的对象
        Optional<Car> oldCar = carService.findById(newCar.getId());
        assert oldCar.isPresent();
        // 判断编辑前后对象是否发生变化
        if (domainUtils.isNotChanged(oldCar.get(), newCar)) {
            customType.setCode(400);
            customType.setMessage("数据前后无改动，不保存！");
        } else {
            List<String> paramList = Arrays.asList("ID", "车名", "价格", "生产日期");
            ChangeLog changeLog =
                    domainUtils.getChangeLog(oldCar.get(), newCar, paramList);
            changeLog.getOperateList().forEach(System.out::println);
            carService.save(newCar);
        }
        return ResponseEntity.ok(customType);
    }
}
