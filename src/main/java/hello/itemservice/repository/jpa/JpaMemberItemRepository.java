package hello.itemservice.repository.jpa;
import hello.itemservice.domain.MemberItem;
import hello.itemservice.domain.MemberItemPK;
import hello.itemservice.repository.MemberItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberItemRepository implements MemberItemRepository {
    private final SpringDataJpaMemberItemRepository repository;
    @Override
    public MemberItem save(MemberItem memberItem) {
        return repository.save(memberItem);
    }
    @Override
    public Optional<MemberItem> findById(MemberItemPK memberItemPK) {
        return repository.findById(memberItemPK);
    }
    @Override
    public void delete(MemberItemPK memberItemPK) {repository.deleteById(memberItemPK);};

    @Override
    public void changeAlarm(MemberItemPK memberItemPK) {
        MemberItem findMemberItem = repository.findById(memberItemPK).orElseThrow();
        if(findMemberItem.isAlarm())
            findMemberItem.setAlarm(false);
        else
            findMemberItem.setAlarm(true);
    }
}
