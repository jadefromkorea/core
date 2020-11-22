package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImplAuto implements OrderService {

    private final MemberRepositoty memberRepositoty;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImplAuto(MemberRepositoty memberRepositoty, DiscountPolicy discountPolicy) {
        this.memberRepositoty = memberRepositoty;
        this.discountPolicy = discountPolicy;
    }

    // 테스트 용도
    public MemberRepositoty getMemberRepositoty() {
        return memberRepositoty;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepositoty.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
