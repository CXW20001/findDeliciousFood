package com.cxw.finddeliciousfood.service;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.mapper.FoodItemMapper;
import com.cxw.finddeliciousfood.model.FoodInfoModel;
import com.cxw.finddeliciousfood.repo.FoodRepository;
import com.cxw.finddeliciousfood.service.impl.FoodInfoService;
import com.cxw.finddeliciousfood.web.PageResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {

    private final FoodRepository foodRepository;
    private final FoodItemMapper foodItemMapper;

    public FoodInfoServiceImpl(FoodRepository foodRepository, FoodItemMapper foodItemMapper) {
        this.foodRepository = foodRepository;
        this.foodItemMapper = foodItemMapper;
    }

    public PageResult<FoodInfoModel> getFavoriteFood(String foodName, PageRequest pageRequest) {
        List<FoodInfoModel> foodInfoModelList = Collections.emptyList();
        PageResult<FoodInfoModel> foodInfoModelPage = new PageResult<>();
        FoodInfoEntity foodInfoEntity = new FoodInfoEntity();
        foodInfoEntity.setFoodItems(foodName);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("foodItems", contains().ignoreCase());
        Example example = Example.of(foodInfoEntity, exampleMatcher);
        Page<FoodInfoEntity> foodInfoEntityPage = foodRepository.findAll(example, pageRequest);
        if (foodInfoEntityPage.hasContent()) {
            foodInfoModelList = new ArrayList<>(foodInfoEntityPage.getContent().size());
            for (FoodInfoEntity entity : foodInfoEntityPage.getContent()) {
                foodInfoModelList.add(foodItemMapper.entityToModel(entity));
            }
        }
        foodInfoModelPage.setPageNum(foodInfoEntityPage.getNumber());
        foodInfoModelPage.setPageSize(foodInfoEntityPage.getSize());
        foodInfoModelPage.setTotal(foodInfoEntityPage.getTotalElements());
        foodInfoModelPage.setTotalPages(foodInfoEntityPage.getTotalPages());
        foodInfoModelPage.setContent(foodInfoModelList);
        return foodInfoModelPage;
    }

    @Override
    public FoodInfoEntity saveCacheable(FoodInfoEntity foodInfoEntity) {
        return foodRepository.save(foodInfoEntity);
    }

    @Override
    public FoodInfoEntity getCacheable(Long id) {
        return foodRepository.findById(id).get();
    }

    @Override
    public FoodInfoEntity updateCacheable(FoodInfoEntity foodInfoEntity) {
        return foodRepository.save(foodInfoEntity);
    }
}
