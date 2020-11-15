package hello.core.singleton;

import hello.core.AppConfigSpring;
import hello.core.member.MemberRepositoty;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    public void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepositoty memberRepositoty = ac.getBean("memberRepositoty", MemberRepositoty.class);

        // 모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepositoty = " + memberService.getMemberRepositoty());
        System.out.println("orderService -> memberRepositoty = " + orderService.getMemberRepositoty());
        System.out.println("memberRepositoty = " + memberRepositoty);

        // 모두 같은 인스턴스를 참고하고 있다.
        Assertions.assertThat(memberService.getMemberRepositoty()).isSameAs(memberRepositoty);
        Assertions.assertThat(orderService.getMemberRepositoty()).isSameAs(memberRepositoty);
    }

    /*
    @Configuration과 바이트코드 조작의 마법
    스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 그런데
    스프링이 자바 코드까지 어떻게 하기는 어렵다. 저 자바 코드를 보면 분명 3번 호출되어야 하는 것이 맞다.
    그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
    모든 비밀은 @Configuration을 적용한 AppConfig 에 있다.
    */
    @Test
    public void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        // AppConfig도 스프링 빈으로 등록한다.
        AppConfigSpring bean = ac.getBean(AppConfigSpring.class);

        System.out.println("bean = " + bean.getClass());
        // 출력: bean = class hello.core.AppConfigSpring$$EnhancerBySpringCGLIB$$79c718d4

        /*
        순수한 클래스라면 다음과 같이 출력되어야 한다.
        class hello.core.AppConfig
        그런데 예상과는 다르게 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해진 것을 볼 수 있다. 이것은 내가
        만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스
        를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!

        그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다. 아마도 다음과 같이 바이트 코드를 조작해서 작
        성되어 있을 것이다.(실제로는 CGLIB의 내부 기술을 사용하는데 매우 복잡하다.)

        - AppConfig@CGLIB 예상 코드
        @Bean
        public MemberRepository memberRepository() {
            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
                return 스프링 컨테이너에서 찾아서 반환;
            } else { //스프링 컨테이너에 없으면
                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
                return 반환
            }
        }

        @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
        스프링 빈이 없으면 생성 해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
        덕분에 싱글톤이 보장되는 것이다.

        @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
        memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
        크게 고민할 것이 없다. 스프링 설정 정보는 항상 @Configuration 을 사용하자.
        */
    }
}
