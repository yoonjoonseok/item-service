package hello.itemservice.service;

import hello.itemservice.domain.*;
import hello.itemservice.repository.ItemSearchCond;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);

    MemberItem save(MemberItem memberItem);

    ItemHistory save(ItemHistory itemHistory);

    Integer update(Long itemId);

    void delete(Long itemId);

    void delete(MemberItemPK memberItemPK);

    Optional<Item> findById(Long id);

    Optional<MemberItem> findById(MemberItemPK memberItemPK);

    Optional<Item> findByLink(String link);

    List<Item> findItems(ItemSearchCond itemSearch);

    List<ItemDTO> findMembersItem(long uid);

    List<ItemHistory> findItemHistory(long pid);

    void changeAlarm(MemberItemPK memberItemPK);
}