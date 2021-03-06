package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImplObj;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTestObj {
    MemberService memberService = new MemberServiceImplObj();
    OrderService orderService = new OrderServiceImplOld();

    @Test
    @DisplayName("Order Test")
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order.getItemPrice(): " + order.getItemPrice() + " | order.getDiscountPrice(): " + order.getDiscountPrice());

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
