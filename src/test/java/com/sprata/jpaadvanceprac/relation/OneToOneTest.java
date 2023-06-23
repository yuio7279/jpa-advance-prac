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
public class OneToOneTest {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    @Rollback(value = false)
    @DisplayName("1:1 단방향 테스트")
    void test1(){

        User user = new User();
        user.setName("kys");
        userRepository.save(user);

        Food food = new Food();
        food.setName("치킨");
        food.setPrice(15000);
        foodRepository.save(food);
        food.setUser(user);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("1:1 단방향 조회 테스트")
    void test2(){
        //단방향에서는 기준 Entity만 다른 Entity를 조회할 수 있다.
        Food food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        System.out.println("food.getUser().getName() = " + food.getUser().getName());

    }

    @Test
    @Rollback(value = false)
    @DisplayName("1:1 양방향 테스트")
    void test3(){
        User user = new User();
        user.setName("kys");


        Food food = new Food();
        food.setName("고구마피자");
        food.setPrice(15000);


        // = food.setUser(user)
        user.addFood(food);

        foodRepository.save(food);
        userRepository.save(user);

    }

    @Test
    @DisplayName("1:1 양방향 조회 테스트 유저 기준")
    void test4(){
        //양방향에서는 user또한 조회가 가능하다.
        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);
        //고객 정보 조회
        System.out.println("user.getName() = " + user.getName());
        //고객이 주문한 음식 조회
        System.out.println("user.getFood().getName() = " + user.getFood().getName());
        System.out.println("user.getFood().getPrice() = " + user.getFood().getPrice());
    }

    @Test
    @DisplayName("1:1 양방향 조회 테스트 음식 기준")
    void test5(){
        Food food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        //고객 정보 조회
        System.out.println("food.getUser().getName() = " + food.getUser().getName());

        //고객이 주문한 음식 조회
        System.out.println("food.getName() = " + food.getName());
        System.out.println("food.getPrice() = " + food.getPrice());

    }
}
