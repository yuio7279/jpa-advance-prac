package com.sprata.jpaadvanceprac.repository;

import com.sprata.jpaadvanceprac.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
