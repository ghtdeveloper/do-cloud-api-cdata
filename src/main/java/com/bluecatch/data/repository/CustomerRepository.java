package com.bluecatch.data.repository;

import com.bluecatch.domain.entities.CustomerEntity;
import com.bluecatch.domain.projection.CustomerMetricsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query(value = """
        SELECT
            ROUND(AVG(age), 2) as averageAge,
            ROUND(STD(age), 2) as standardDeviation,
            MIN(age) as minAge,
            MAX(age) as maxAge
        FROM tb_customer
        WHERE age IS NOT NULL
        """, nativeQuery = true)
    CustomerMetricsProjection getCustomerAgeMetrics();
}
