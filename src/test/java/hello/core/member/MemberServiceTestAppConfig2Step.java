package hello.core.member;

import hello.core.AppConfig1Step;
import hello.core.AppConfig2Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTestAppConfig2Step {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig2Step appConfig2Step = new AppConfig2Step();
        memberService = appConfig2Step.memberService();
    }

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
