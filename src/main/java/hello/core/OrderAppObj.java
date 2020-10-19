package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImplObj;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImplOld;

public class OrderAppObj {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImplObj();
        OrderService orderService = new OrderServiceImplOld();

        long memberId = 1L;
        Member member = new Member(memberId, "itemA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
