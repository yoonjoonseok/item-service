package hello.itemservice.repository;

import hello.itemservice.domain.MemberItem;
import hello.itemservice.domain.MemberItemPK;

import java.util.Optional;

public interface MemberItemRepository {
    MemberItem save(MemberItem memberItem);
    Optional<MemberItem> findById(MemberItemPK memberItemPK);
    void delete(MemberItemPK memberItemPK);
    void changeAlarm(MemberItemPK memberItemPK);
}
