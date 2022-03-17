package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoreApplicationTest {

    @Autowired
    DiscountPolicy discountPolicy;

    @Autowired
    FixDiscountPolicy fixDiscountPolicy;

    @Autowired
    RateDiscountPolicy rateDiscountPolicy;

    @Test
    public void test(){
        System.out.println("discountPolicy = " + discountPolicy);
        System.out.println("fixDiscountPolicy = " + fixDiscountPolicy);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
    }
}