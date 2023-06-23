package com.sprata.jpaadvanceprac.relation;


import com.sprata.jpaadvanceprac.entity.Food;
import com.sprata.jpaadvanceprac.entity.User;
import com.sprata.jpaadvanceprac.repository.FoodRepository;
import com.sprata.jpaadvanceprac.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
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


}
