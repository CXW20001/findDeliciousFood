package com.cxw.finddeliciousfood.service.impl;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.model.FoodInfoModel;
import com.cxw.finddeliciousfood.web.PageResult;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;

@Cacheable(value = "cache1", key = "#p0")
@CacheConfig(cacheNames = "cache1")
public interface FoodInfoService {

    @Cacheable(value = "cache1", key = "#a0")
    PageResult<FoodInfoModel> getFavoriteFood(String foodName, PageRequest pageRequest);

    @CacheEvict(value = "cxwCache", key = "#result.locationid")
    FoodInfoEntity saveCacheable(FoodInfoEntity foodInfoEntity);

    @Cacheable(value = "cxwCache")
    FoodInfoEntity getCacheable(Long id);

    @CachePut(value = "cxwCache", key = "#p0.id")
    FoodInfoEntity updateCacheable(FoodInfoEntity foodInfoEntity);
}
