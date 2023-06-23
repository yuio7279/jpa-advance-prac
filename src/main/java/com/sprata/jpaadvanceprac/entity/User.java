package com.sprata.jpaadvanceprac.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Food> FoodList = new ArrayList<>();

    public void addFoodList(Food food){
        this.FoodList.add(food);
        food.setUser(this);
    }




}
