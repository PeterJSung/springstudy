package hello.core.autowired;

import hello.core.member.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    @Test
    void setNoBean() {

    }

    static class TestBean {
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("nobean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("nobean2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("nobean3 = " + member);
        }

    }
}
