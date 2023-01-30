package hello.itemservice.login.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    List<String> findChatId(Long pid);
}
