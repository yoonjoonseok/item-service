package hello.itemservice.repository.jpa;

import hello.itemservice.domain.ItemHistory;
import hello.itemservice.domain.ItemHistoryPK;
import hello.itemservice.repository.ItemHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemHistoryRepository implements ItemHistoryRepository{
    private final SpringDataJpaItemHistoryRepository repository;

    @Override
    public ItemHistory save(ItemHistory itemHistory){
        return repository.save(itemHistory);
    }

    @Override
    public List<ItemHistory> findItemHistory(long pid){return repository.findAllByPid(pid);}
}
