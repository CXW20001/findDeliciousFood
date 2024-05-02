package com.cxw.finddeliciousfood;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.repo.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.ExampleMatcher.matching;

@DataJpaTest
class foodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    FoodInfoEntity food_1, food_2, food_3;

    @BeforeEach
    public void setUp() {
        foodRepository.deleteAllInBatch();
        food_1 = new FoodInfoEntity();
        food_1.setAddress("5 THE EMBARCADERO");
        food_1.setFoodItems("ice cream:embarcadero:bread");
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
    void matchingAddressIgnoreCase() {
        FoodInfoEntity entity = new FoodInfoEntity();
        entity.setAddress("embarcadero");
        Example example = Example.of(entity, matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase());
//        var example = Example.of(entity, matching(). //
////                withIgnorePaths("age"). //
////                withMatcher("firstname", startsWith()). //
//                withMatcher("address", contains().ignoreCase()));
        assertThat(foodRepository.findAll(example)).containsExactly(food_1);
    }

}
