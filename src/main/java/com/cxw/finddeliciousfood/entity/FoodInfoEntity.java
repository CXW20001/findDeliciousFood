package com.cxw.finddeliciousfood.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FoodInfoEntity extends AbstractPersistable<Long> {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
    @Column(unique = true)
    private String locationid;
    private String applicant;
    private String facilityType;
    private String locationDescription;
    private String address;
    private String status;
    private Date expirationDate;
    private String location;
    @Column(columnDefinition = "VARCHAR(500)")
    private String foodItems;

    @Transient
    private String testMapStructConstant;
    @Transient
    private String testMapStructExpression;
    @Transient
    private String testMapStructDefaultValue;
}
