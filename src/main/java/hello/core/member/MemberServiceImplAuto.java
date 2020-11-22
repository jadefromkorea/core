package hello.core.member;

import org.springframework.stereotype.Component;

@Component
public class MemberServiceImplAuto implements MemberService {
//    private final MemberRepositoty memberRepositoty = new MemoryMemberRepositoty();
    private final MemberRepositoty memberRepositoty;

    public MemberServiceImplAuto(MemberRepositoty memberRepositoty) {
        this.memberRepositoty = memberRepositoty;
    }

    // 테스트 용도
    public MemberRepositoty getMemberRepositoty() {
        return memberRepositoty;
    }

    @Override
    public void join(Member member) {
        memberRepositoty.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepositoty.findById(memberId);
    }
}
