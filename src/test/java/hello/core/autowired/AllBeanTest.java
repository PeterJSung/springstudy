package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService dc = ac.getBean(DiscountService.class);
        Member myMember = new Member(1L, "Test", Grade.VIP);
        int fixDiscountValue = dc.discount(myMember, 20000, "fixDiscountPolicy");
        int rateDiscountValue = dc.discount(myMember, 20000, "rateDiscountPolicy");

        Assertions.assertThat(fixDiscountValue).isEqualTo(1000);
        Assertions.assertThat(rateDiscountValue).isEqualTo(2000);
    }



    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final Map<String, OrderService> orderServiceMap;
        private final List<DiscountPolicy> policies;
        private final List<OrderService> services;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               Map<String, OrderService> orderServiceMap, List<DiscountPolicy> policies, List<OrderService> services) {
            this.policyMap = policyMap;
            this.orderServiceMap = orderServiceMap;
            this.policies = policies;
            this.services = services;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
            System.out.println("orderServiceMap = " + orderServiceMap);
            System.out.println("services = " + services);
        }

        int discount(Member member, int price, String policyName) {
            DiscountPolicy dp = policyMap.get(policyName);
            return dp.discount(member, price);
        }

    }

}
