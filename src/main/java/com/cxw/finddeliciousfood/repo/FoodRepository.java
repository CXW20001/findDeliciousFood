package com.cxw.finddeliciousfood.repo;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends BaseRepository<FoodInfoEntity, Long> {

}
