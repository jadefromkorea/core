package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("테스트")
    void join() {
        //given
        Member member1 = new Member(1L, "memberA", Grade.VIP);
        Member member2 = new Member(2L, "memberB", Grade.BASIC);

        //when
        memberService.join(member1);
        Member findMember1 = memberService.findMember(1L);

        memberService.join(member2);
        Member findMember2 = memberService.findMember(2L);

        System.out.println("member1.getName(): " + member1.getName());
        System.out.println("findMember1.getName(): " + findMember1.getName());

        System.out.println("member2.getName(): " + member2.getName());
        System.out.println("findMember2.getName(): " + findMember2.getName());

        //then
        Assertions.assertThat(member1).isEqualTo(findMember1);
        Assertions.assertThat(member2).isEqualTo(findMember2);
    }
}
