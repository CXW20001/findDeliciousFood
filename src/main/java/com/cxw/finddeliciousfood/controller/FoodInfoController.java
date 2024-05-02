package com.cxw.finddeliciousfood.controller;

import com.cxw.finddeliciousfood.model.FoodInfoModel;
import com.cxw.finddeliciousfood.service.FoodInfoServiceImpl;
import com.cxw.finddeliciousfood.web.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FoodInfoController {

    private final FoodInfoServiceImpl foodInfoService;


    public FoodInfoController(FoodInfoServiceImpl foodInfoService) {
        this.foodInfoService = foodInfoService;
    }

    @GetMapping("/{itemName}")
    public PageResult<FoodInfoModel> findFoodItem(@PathVariable String itemName
            , @RequestParam(defaultValue = "0") Integer pageNumber
            , @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "ExpirationDate"));
        log.info("[findFoodItem],itemName=" + itemName);
        return this.foodInfoService.getFavoriteFood(itemName, pageRequest);
    }

}
