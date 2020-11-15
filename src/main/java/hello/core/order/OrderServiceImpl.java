package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepositoty;

public class OrderServiceImpl implements OrderService {
//    private final MemberRepositoty memberRepositoty = new MemoryMemberRepositoty();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepositoty memberRepositoty;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepositoty memberRepositoty, DiscountPolicy discountPolicy) {
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
