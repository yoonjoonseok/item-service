package hello.itemservice.service;

import hello.itemservice.domain.*;
import hello.itemservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {
    private final ItemRepository itemRepository;
    private final MemberItemRepository memberItemRepository;
    private final ItemHistoryRepository itemHistoryRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public MemberItem save(MemberItem memberItem) {
        return memberItemRepository.save(memberItem);
    }

    @Override
    public ItemHistory save(ItemHistory itemHistory){return itemHistoryRepository.save(itemHistory);}

    @Override
    public Integer update(Long itemId) {
        Optional<Item> item = findById(itemId);
        Integer preQuantity = item.get().getQuantity();
        Integer postQuantity = item.get().updateStock();

        if (preQuantity != postQuantity) {
            itemRepository.update(itemId, new ItemUpdateDto(null, null, null, postQuantity, null));
            itemHistoryRepository.save(new ItemHistory(itemId,null,postQuantity));
            return postQuantity;
        }
        return -1;
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.delete(itemId);
    }

    @Override
    public void delete(MemberItemPK memberItemPK) {
        memberItemRepository.delete(memberItemPK);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<MemberItem> findById(MemberItemPK memberItemPK) {
        return memberItemRepository.findById(memberItemPK);
    }

    @Override
    public Optional<Item> findByLink(String link) {
        return itemRepository.findByLink(link);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }

    @Override
    public List<ItemDTO> findMembersItem(long uid) {
        return itemRepository.findMembersItem(uid);
    }

    @Override
    public List<ItemHistory> findItemHistory(long pid){return itemHistoryRepository.findItemHistory(pid);};

    @Override
    public void changeAlarm(MemberItemPK memberItemPK) {
        memberItemRepository.changeAlarm(memberItemPK);
    }
}
