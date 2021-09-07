package com.javaschool.restapiserver.repository;

import com.javaschool.restapiserver.models.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Integer> {
}
