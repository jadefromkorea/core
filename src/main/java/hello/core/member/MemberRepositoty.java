package hello.core.member;

public interface MemberRepositoty {
    void save(Member member);

    Member findById(Long memberId);
}
