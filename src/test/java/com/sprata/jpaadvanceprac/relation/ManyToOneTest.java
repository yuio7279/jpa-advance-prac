package com.sprata.jpaadvanceprac.relation;

import com.sprata.jpaadvanceprac.entity.Food;
import com.sprata.jpaadvanceprac.entity.User;
import com.sprata.jpaadvanceprac.repository.FoodRepository;
import com.sprata.jpaadvanceprac.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ManyToOneTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @Test
    @Rollback(value=false)
    @DisplayName("N:1 단방향 테스트")
    void test1(){
        User user = new User();
        user.setName("kys");

        Food food = new Food();
        food.setPrice(15000);
        food.setName("치킨");
        food.setUser(user);

        Food food2 = new Food();
        food2.setPrice(15000);
        food2.setName("불고기피자");
        food2.setUser(user);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);

    }

}
