package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepositoty;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepositoty;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigSpring {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepositoty());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepositoty(), discountPolicy());
    }

    @Bean
    public MemberRepositoty memberRepositoty() {
        return new MemoryMemberRepositoty();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}