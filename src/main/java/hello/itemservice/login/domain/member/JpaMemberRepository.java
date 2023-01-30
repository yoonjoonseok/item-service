package hello.itemservice.login.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.QMemberItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{
    private final SpringDataJpaMemberRepository repository;

    private final JPAQueryFactory query;

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    public List<Member> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
    @Override
    public List<String> findChatId(Long pid){
        QMember member = QMember.member;
        QMemberItem memberItem = QMemberItem.memberItem;
        List<String> list = query.select(member.chatId)
                .from(member)
                .join(memberItem)
                .on(member.uid.eq(memberItem.uid))
                .where(memberItem.pid.eq(pid),memberItem.alarm.isTrue())
                .fetch();
        return list;
    }
}
