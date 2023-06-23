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

    @Test
    @DisplayName("N:1 단방향 조회 테스트")
    void test2(){
        Food food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        //단방향은 이런게 안좋다. user입장에서 조회를 못하니깐 user가 주문한 음식들을 한번에 볼수가 없다.

        //단순히 음식입장에서 주문한 고객만을 조회할 수 있을 뿐이다.
        System.out.println("food.getUser().getName() = " + food.getUser().getName());
        System.out.println("food.getName() = " + food.getName());
        System.out.println("food.getPrice() = " + food.getPrice());
    }

    @Test
    @Rollback(value= false)
    @DisplayName("N:1 양방향 테스트")
    void test3(){
        User user = new User();
        user.setName("kys");

        Food food = new Food();
        food.setName("치킨");
        food.setPrice(15000);
        food.setUser(user);

        Food food2 = new Food();
        food2.setName("불고기피자");
        food2.setPrice(23000);
        food2.setUser(user);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);
    }
}
