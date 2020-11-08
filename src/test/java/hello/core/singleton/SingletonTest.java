package hello.core.singleton;

import hello.core.AppConfig2Step;
import hello.core.AppConfigSpring;
import hello.core.member.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void pureContainer() {
        AppConfig2Step appConfig = new AppConfig2Step();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

/*
    - 싱글톤 패턴 문제점
    싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
    의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
    클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
    테스트하기 어렵다.
    내부 속성을 변경하거나 초기화 하기 어렵다.
    private 생성자로 자식 클래스를 만들기 어렵다.
    결론적으로 유연성이 떨어진다.
    안티패턴으로 불리기도 한다.
*/
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        // private으로 생성자를 막아두었다. 컴파일 오류가 발생한다.
//        new SingletonService();

        // 1.조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2.조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonService1 == singletonService2
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }

/*
    - 싱글톤 컨테이너
    스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
    이전에 설명한 컨테이너 생성 과정을 자세히 보자. 컨테이너는 객체를 하나만 생성해서 관리한다.
    스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤
    레지스트리라 한다.
    스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수
    있다.
    싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
    DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.
*/
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        // 1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // singletonService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }

}
