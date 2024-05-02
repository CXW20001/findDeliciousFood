package com.cxw.finddeliciousfood.mapper;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.model.FoodInfoModel;
import com.cxw.finddeliciousfood.repo.FoodRepository;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = FoodItemNamedMapping.class, componentModel = "spring")
//@Mapper(componentModel = "spring")
public abstract class FoodItemMapper {

    //We must remember not to make the injected bean private!
    // This is because MapStruct has to access the object in the generated implementation class.
    @Autowired
    protected FoodRepository foodRepository;

    @Mapping(target = "foodItems", qualifiedByName = {"FoodItemNamedMapping", "FoodItemStringToList"})
    @Mapping(source = "locationid", target = "locationId")
    @Mapping(target = "testMapStructExpression", expression = "java(com.cxw.finddeliciousfood.utils.DateUtil.formatUsDate(\"03/15/2016 12:00:00 AM\").toString())")
    @Mapping(target = "testMapStructConstant", constant = "testMapStructConstant")
    @Mapping(target = "testMapStructDefaultValue", defaultValue = "testMapStructDefaultValue")
    public abstract FoodInfoModel entityToModel(FoodInfoEntity entity);

    @InheritInverseConfiguration
//    @InheritConfiguration
    @Mapping(target = "foodItems", qualifiedByName = {"FoodItemNamedMapping", "FoodItemListToString"})
    public abstract FoodInfoEntity modelToEntity(FoodInfoModel model);
}
