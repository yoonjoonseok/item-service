package hello.itemservice.repository.jpa;

import hello.itemservice.domain.ItemHistory;
import hello.itemservice.domain.ItemHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaItemHistoryRepository extends JpaRepository<ItemHistory, ItemHistoryPK> {
    List<ItemHistory> findAllByPid(long pid);
}
