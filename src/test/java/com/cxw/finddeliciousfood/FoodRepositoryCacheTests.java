package com.cxw.finddeliciousfood;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.model.FoodInfoModel;
import com.cxw.finddeliciousfood.repo.FoodRepository;
import com.cxw.finddeliciousfood.service.impl.FoodInfoService;
import com.cxw.finddeliciousfood.web.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class FoodRepositoryCacheTests {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodInfoService foodInfoService;

    @Autowired
    CacheManager cacheManager;

    FoodInfoEntity food_1, food_2, food_3;

    @BeforeEach
    public void setUp() {
        foodRepository.deleteAllInBatch();
        food_1 = new FoodInfoEntity();
        food_1.setAddress("5 THE EMBARCADERO");
        food_1.setFoodItems("ice cream:embarcadero:bread:chicken over rice");
        food_1.setStatus("REQUESTED");

        food_2 = new FoodInfoEntity();
        food_2.setAddress("50 01ST ST");
        food_2.setFacilityType("Truck");
        food_2.setStatus("EXPIRED");

        food_3 = new FoodInfoEntity();
        food_3.setAddress("1700 EVANS AVE");
        food_3.setFacilityType("Truck");
        food_3.setStatus("SUSPEND");
        foodRepository.save(food_1);
        foodRepository.save(food_2);
        foodRepository.save(food_3);
    }

    @Test
    void checkCachedValue() {
        var foodEntity = new FoodInfoEntity();
//        foodEntity.setId(UUID.randomUUID().toString());
        foodEntity.setLocationid("123435hger");
        FoodInfoEntity foodInfoEntity = foodInfoService.saveCacheable(foodEntity);
        assertThat(foodInfoService.getCacheable(foodInfoEntity.getId())).isEqualTo(foodInfoEntity);
    }

    @Test
    void checkEviction() {
        var foodEntity = new FoodInfoEntity();
//        foodEntity.setId(UUID.randomUUID().toString());
        foodEntity.setLocationid("123435hger");
        FoodInfoEntity foodInfoEntity = foodInfoService.saveCacheable(foodEntity);
        assertThat(foodInfoService.getCacheable(foodInfoEntity.getId())).isEqualTo(foodEntity);
        Cache cxwCache = cacheManager.getCache("cxwCache");
        assertThat(cxwCache.get(foodInfoEntity.getLocationid())).isNull();
    }

    @Test
    void checkCacheable() {
        PageResult<FoodInfoModel> foodModelPage = foodInfoService.getFavoriteFood("embarcadero", PageRequest.of(0, 20));
        Cache cache1 = cacheManager.getCache("cache1");
        assertThat(cache1.get("embarcadero")).isNotNull();
    }

}
