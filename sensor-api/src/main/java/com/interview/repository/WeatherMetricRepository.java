package com.interview.repository;

import com.interview.common.WeatherStatisticType;
import com.interview.entity.Sensor;
import com.interview.entity.WeatherMetric;
import com.interview.entity.WeatherStatistic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class WeatherMetricRepository extends SimpleJpaRepository<WeatherMetric, Long> {
    private final EntityManager entityManager;

    public WeatherMetricRepository(EntityManager entityManager) {
        super(WeatherMetric.class, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * @param parameters
     * @return
     */
    //@Query("SELECT wm FROM WeatherMetric wm")
    public List<WeatherMetric> findAllByParameters(@NotNull Map<WeatherMetric.Parameter,Serializable> parameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeatherMetric> criteriaQuery = criteriaBuilder.createQuery(WeatherMetric.class);
        Root<WeatherMetric> entityCriteria = criteriaQuery.from(WeatherMetric.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(entityCriteria.get(WeatherMetric.Parameter.ID.getFieldName())));

        Predicate[] predicates = parameters.entrySet()
                .stream()
                .map(e -> {
                    if (e.getKey() == WeatherMetric.Parameter.SENSOR_ID) {
                        String joinParameter = e.getKey().getFieldName();
                        int idx = joinParameter.indexOf('.');
                        String fieldName = joinParameter.substring(0, idx++);
                        String sensorFieldName = joinParameter.substring(idx);
                        Join<Sensor,WeatherMetric> joinCriteria = entityCriteria.join(fieldName);
                        return criteriaBuilder.equal(joinCriteria.get(sensorFieldName), e.getValue());
                    } else if (e.getKey() == WeatherMetric.Parameter.START) {
                        return criteriaBuilder.greaterThanOrEqualTo(entityCriteria.get(e.getKey().getFieldName()), (OffsetDateTime) e.getValue());
                    } else if (e.getKey() == WeatherMetric.Parameter.END) {
                        return criteriaBuilder.lessThanOrEqualTo(entityCriteria.get(e.getKey().getFieldName()), (OffsetDateTime) e.getValue());
                    } else {
                        return criteriaBuilder.equal(entityCriteria.get(e.getKey().getFieldName()), e.getValue());
                    }
                })
                .toList()
                .toArray(new Predicate[]{});

        if (predicates.length > 0) {
            criteriaQuery.select(entityCriteria).where(predicates);
        } else {
            criteriaQuery.select(entityCriteria);
        }
        TypedQuery<WeatherMetric> typedQuery = entityManager.createQuery(criteriaQuery);
        try {
            return typedQuery.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param statisticType
     * @param parameters
     * @return
     */
    public WeatherStatistic queryStatistics(@NotNull WeatherStatisticType statisticType,
                                            @NotNull Map<WeatherStatistic.Parameter, Serializable> parameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeatherStatistic> criteriaQuery = criteriaBuilder.createQuery(WeatherStatistic.class);
        Root<WeatherMetric> entityCriteria = criteriaQuery.from(WeatherMetric.class);

        Predicate[] predicates = parameters.entrySet()
                .stream()
                .map(e -> {
                    if (e.getKey() == WeatherStatistic.Parameter.SENSOR_ID) {
                        String joinParameter = e.getKey().getFieldName();
                        int idx = joinParameter.indexOf('.');
                        String fieldName = joinParameter.substring(0, idx++);
                        String sensorFieldName = joinParameter.substring(idx);
                        Join<Sensor,WeatherStatistic> joinCriteria = entityCriteria.join(fieldName);
                        return criteriaBuilder.equal(joinCriteria.get(sensorFieldName), e.getValue());
                    } else if (e.getKey() == WeatherStatistic.Parameter.START) {
                        return criteriaBuilder.greaterThanOrEqualTo(entityCriteria.get(e.getKey().getFieldName()), (OffsetDateTime) e.getValue());
                    } else if (e.getKey() == WeatherStatistic.Parameter.END) {
                        return criteriaBuilder.lessThanOrEqualTo(entityCriteria.get(e.getKey().getFieldName()), (OffsetDateTime) e.getValue());
                    } else {
                        return criteriaBuilder.equal(entityCriteria.get(e.getKey().getFieldName()), e.getValue());
                    }
                })
                .toList()
                .toArray(new Predicate[]{});

        // add the statistic type to the query
        List<Selection<?>> selections = new ArrayList<>();
        String fieldName = WeatherStatistic.Parameter.TEMPERATURE.getFieldName();
        selections.add(getCriteriaSelection(statisticType, criteriaBuilder, entityCriteria, fieldName)
                .alias(WeatherStatistic.Parameter.TEMPERATURE_STATISTIC.getFieldName()));
        fieldName = WeatherStatistic.Parameter.HUMIDITY.getFieldName();
        selections.add(getCriteriaSelection(statisticType, criteriaBuilder, entityCriteria, fieldName)
                .alias(WeatherStatistic.Parameter.HUMIDITY_STATISTIC.getFieldName()));
        fieldName = WeatherStatistic.Parameter.WIND_SPEED.getFieldName();
        selections.add(getCriteriaSelection(statisticType, criteriaBuilder, entityCriteria, fieldName)
                .alias(WeatherStatistic.Parameter.WIND_SPEED_STATISTIC.getFieldName()));

        CriteriaQuery<WeatherStatistic> where;
        if (predicates.length > 0) {
            where = criteriaQuery.multiselect(selections).where(predicates);
        } else {
            where = criteriaQuery.multiselect(selections);
        }
        TypedQuery<WeatherStatistic> typedQuery = entityManager.createQuery(where);
        return typedQuery.getSingleResult();
    }

    private Selection<Double> getCriteriaSelection(WeatherStatisticType statisticType,
                                                   CriteriaBuilder criteriaBuilder,
                                                   Root<WeatherMetric> rootEntity,
                                                   String fieldName) {
        Selection<Double> selection;
        switch (statisticType) {
            case MAX -> {
                selection = criteriaBuilder.max(rootEntity.get(fieldName)).as(Double.class);
            }
            case MIN -> {
                selection = criteriaBuilder.min(rootEntity.get(fieldName)).as(Double.class);
            }
            case SUM -> {
                selection = criteriaBuilder.sumAsDouble(rootEntity.get(fieldName)).as(Double.class);
            }
            case AVERAGE -> {
                selection = criteriaBuilder.avg(rootEntity.get(fieldName)).as(Double.class);
            }
            default -> {
                throw new IllegalArgumentException("Unknown statistic type: " + statisticType);
            }
        }
        return selection;
    }
}
