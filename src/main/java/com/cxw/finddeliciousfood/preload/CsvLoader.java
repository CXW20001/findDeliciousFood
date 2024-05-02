package com.cxw.finddeliciousfood.preload;

import com.cxw.finddeliciousfood.entity.FoodInfoEntity;
import com.cxw.finddeliciousfood.repo.FoodRepository;
import com.cxw.finddeliciousfood.utils.DateUtil;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CsvLoader implements ApplicationRunner {

    private final FoodRepository foodRepository;

    public CsvLoader(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<FoodInfoEntity> foodList = loadFoodList(new ClassPathResource("static/Mobile_Food_Facility_Permit.csv"));
        foodRepository.deleteAllInBatch();
        foodRepository.saveAllAndFlush(foodList);
    }

    private static List<FoodInfoEntity> loadFoodList(ClassPathResource classPathResource) throws Exception {
        Scanner scanner = new Scanner(classPathResource.getInputStream());

        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<FoodInfoEntity> reader = new FlatFileItemReader<>();
        reader.setResource(classPathResource);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<FoodInfoEntity> lineMapper = new DefaultLineMapper();
        lineMapper.setFieldSetMapper(fieldSet -> {

            FoodInfoEntity foodInfo = new FoodInfoEntity();
            foodInfo.setAddress(fieldSet.readString("Address"));
            foodInfo.setApplicant(fieldSet.readString("Applicant"));
            foodInfo.setLocation(fieldSet.readString("Location"));
            foodInfo.setLocationid(fieldSet.readString("locationid"));
            foodInfo.setLocationDescription(fieldSet.readString("LocationDescription"));
            foodInfo.setStatus(fieldSet.readString("Status"));
            String expirationDateString = fieldSet.readString("ExpirationDate");
            if (StringUtils.hasText(expirationDateString)) {
                foodInfo.setExpirationDate(Date.from(DateUtil.formatUsDate(expirationDateString).toInstant(ZoneOffset.of("+8"))));
            }
            foodInfo.setFacilityType(fieldSet.readString("FacilityType"));
            foodInfo.setFoodItems(StringUtils.truncate(fieldSet.readString("FoodItems"), 500));

            return foodInfo;
        });


        lineMapper.setLineTokenizer(tokenizer);

        reader.setLineMapper(lineMapper);
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        reader.setLinesToSkip(1);
        reader.open(new ExecutionContext());

        List<FoodInfoEntity> foodInfoEntities = new ArrayList<>();
        FoodInfoEntity foodInfoEntity = null;

        do {

            foodInfoEntity = reader.read();

            if (foodInfoEntity != null) {
                foodInfoEntities.add(foodInfoEntity);
            }

        } while (foodInfoEntity != null);

        return foodInfoEntities;
    }
}
