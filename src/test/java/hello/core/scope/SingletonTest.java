package hello.core.scope;

import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void signletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SigneltonBean.class);
        System.out.println("Find 1");
        SigneltonBean bean = ac.getBean(SigneltonBean.class);
        System.out.println("Find 2");
        SigneltonBean bean2 = ac.getBean(SigneltonBean.class);
        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean).isSameAs(bean2);
        ac.close();
    }



    @Scope("singleton")
    static class SigneltonBean {
        @PostConstruct
        public void init() {
            System.out.println("SigneltonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SigneltonBean.destroy");
        }
    }

    @Test
    void myTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigTest.class);
        MemberRepository origin = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository one = ac.getBean("memberRepository1", MemberRepository.class);
        MemberRepository two = ac.getBean("memberRepository2", MemberRepository.class);
        System.out.println("origin = " + origin);
        System.out.println("one = " + one);
        System.out.println("two = " + two);
    }

    @Configuration
    static class ConfigTest {
        @Bean
        MemberRepository memberRepository() {
            System.out.println("ConfigTest.origin");
            return new MemoryMemberRepository();
        }
        @Bean
        MemberRepository memberRepository1() {
            System.out.println("ConfigTest.memberRepository1");
            return memberRepository();
        }
        @Bean
        MemberRepository memberRepository2() {
            System.out.println("ConfigTest.memberRepository2");
            return memberRepository();
        }
    }

}
