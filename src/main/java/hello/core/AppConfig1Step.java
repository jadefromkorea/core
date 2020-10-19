package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig1Step {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepositoty());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepositoty(), new FixDiscountPolicy());
    }
}