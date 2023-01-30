package hello.itemservice.repository.jpa;

import hello.itemservice.domain.MemberItem;
import hello.itemservice.domain.MemberItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberItemRepository extends JpaRepository<MemberItem, MemberItemPK>{
}
