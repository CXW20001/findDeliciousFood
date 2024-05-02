package com.cxw.finddeliciousfood.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Named("FoodItemNamedMapping")
@Component
public class FoodItemNamedMapping {

    @Named("FoodItemStringToList")
    public static List<String> FoodItemStringToList(String FoodItemString) {
        return Arrays.stream(StringUtils.tokenizeToStringArray(FoodItemString, ":")).toList();
//        return null;
    }

    @Named("FoodItemListToString")
    public String FoodItemStringToList(List<String> FoodItems) {
        return StringUtils.collectionToDelimitedString(FoodItems, ":");
//        return null;
    }

}
