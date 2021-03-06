package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient nc = ac.getBean(NetworkClient.class);
        ac.close();
    }


    @Configuration
    static class LifeCycleConfig {
        @Bean//(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkCLient() {
            NetworkClient nc = new NetworkClient();
            nc.setUrl("https://www.naver.com");
            return nc;
        }
    }

}
