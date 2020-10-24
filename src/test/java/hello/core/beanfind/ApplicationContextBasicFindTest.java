package hello.core.beanfind;

import hello.core.AppConfigSpring;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X 1 -> NoSuchBeanDefinitionException 발생")
    void findBeanByNameX1() {
        MemberService memberService =  ac.getBean("XXXXX", MemberService.class);

        assertThat(NoSuchBeanDefinitionException.class).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("빈 이름으로 조회X 2")
    void findBeanByNameX2() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXXXX", MemberService.class));
    }
}
