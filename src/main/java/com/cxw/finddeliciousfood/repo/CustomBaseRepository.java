package com.cxw.finddeliciousfood.repo;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class CustomBaseRepository<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    public CustomBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public long customMethod() {
        return 0;
    }
}
