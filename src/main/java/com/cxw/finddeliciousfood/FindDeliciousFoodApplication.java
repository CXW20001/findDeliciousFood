package com.cxw.finddeliciousfood;

import com.cxw.finddeliciousfood.repo.CustomBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories(repositoryBaseClass = CustomBaseRepository.class)
@SpringBootApplication
public class FindDeliciousFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindDeliciousFoodApplication.class, args);
    }

}
