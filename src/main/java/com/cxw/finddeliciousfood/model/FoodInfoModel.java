package com.cxw.finddeliciousfood.model;

import lombok.Data;

import java.util.List;

@Data
public class FoodInfoModel {
    private Long id;
    private String locationId;
    private String applicant;
    private String facilityType;
    private String locationDescription;
    private String address;
    private String status;
    private String expirationDate;
    private String location;
    private List<String> foodItems;

    private String testMapStructConstant;
    private String testMapStructExpression;
    private String testMapStructDefaultValue;
}
