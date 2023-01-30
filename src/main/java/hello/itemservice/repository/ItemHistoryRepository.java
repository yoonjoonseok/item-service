package hello.itemservice.repository;

import hello.itemservice.domain.ItemHistory;
import hello.itemservice.domain.ItemHistoryPK;

import java.util.List;
import java.util.Optional;

public interface ItemHistoryRepository {
    ItemHistory save(ItemHistory itemHistory);
    List<ItemHistory> findItemHistory(long pid);
}
