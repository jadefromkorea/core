package hello.core.member;

public class MemberServiceImplObj implements MemberService {
    private final MemberRepositoty memberRepositoty = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepositoty.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepositoty.findById(memberId);
    }
}
