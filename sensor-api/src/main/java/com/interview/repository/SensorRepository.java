package com.interview.repository;

import com.interview.entity.Sensor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class SensorRepository extends SimpleJpaRepository<Sensor, Long> {
    private final EntityManager entityManager;

    public SensorRepository(EntityManager entityManager) {
        super(Sensor.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<Sensor> findAllByParameters(@NotNull Map<Sensor.Parameter, Serializable> parameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sensor> criteriaQuery = criteriaBuilder.createQuery(Sensor.class);
        Root<Sensor> entityCriteria = criteriaQuery.from(Sensor.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(entityCriteria.get(Sensor.Parameter.ID.getFieldName())));

        Predicate[] predicates = parameters.entrySet()
                .stream()
                .map(e -> criteriaBuilder.equal(entityCriteria.get(e.getKey().getFieldName()), e.getValue()))
                .toList()
                .toArray(new Predicate[]{});

        if (predicates.length > 0) {
            criteriaQuery.select(entityCriteria).where(criteriaBuilder.and(predicates));
        } else {
            criteriaQuery.select(entityCriteria);
        }
        TypedQuery<Sensor> typedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return typedQuery.getResultList();
        } catch(NoResultException e) {
            return Collections.emptyList();
        }
    }
}
