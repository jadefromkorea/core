package hello.core.order;

import hello.core.AppConfig1Step;
import hello.core.AppConfig2Step;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTestAppConfig2Step {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig2Step appConfig2Step = new AppConfig2Step();
        memberService = appConfig2Step.memberService();
        orderService = appConfig2Step.orderService();
    }

    @Test
    @DisplayName("Order Test")
    void createOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order.getItemPrice(): " + order.getItemPrice() + " | order.getDiscountPrice(): " + order.getDiscountPrice());

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }
}
