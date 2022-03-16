package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSinglton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService ss1 = ac.getBean(StatefulService.class);
        StatefulService ss2 = ac.getBean(StatefulService.class);

        ss1.order("test1", 100);
        ss2.order("test2", 200);

        System.out.println("ss1 = " + ss1.getPrice());
        System.out.println("ss2 = " + ss2.getPrice());
        Assertions.assertThat(ss1.getPrice()).isEqualTo(ss2.getPrice());
    }

    static class TestConfig {
        @Bean
        public  StatefulService statefulService() {
            return new StatefulService();
        }
    }
}