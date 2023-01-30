package hello.itemservice.repository.jpa;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.*;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {
    private final SpringDataJpaItemRepository repository;
    private final JPAQueryFactory query;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }
    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = repository.findById(itemId).orElseThrow();
        //findItem.setLink(updateParam.getLink());
        //findItem.setItemName(updateParam.getItemName());
        //findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //findItem.setImgLink(updateParam.getImgLink());
    }
    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Optional<Item> findByLink(String link) {
        return repository.findByLink(link);
    }
    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        if (StringUtils.hasText(itemName) && maxPrice != null) {
            //return repository.findByItemNameLikeAndPriceLessThanEqual("%" + itemName +"%", maxPrice);
            return repository.findItems("%" + itemName + "%", maxPrice);
        } else if (StringUtils.hasText(itemName)) {
            return repository.findByItemNameLike("%" + itemName + "%");
        } else if (maxPrice != null) {
            return repository.findByPriceLessThanEqual(maxPrice);
        } else {
            return repository.findAll();
        }
    }
    @Override
    public List<ItemDTO> findMembersItem(long uid){
        QItem item = QItem.item;
        QMemberItem memberItem = QMemberItem.memberItem;
        List<ItemDTO> list = query.select(new QItemDTO(item.id,item.link,item.itemName,item.price,item.quantity,item.imgLink,memberItem.alarm))
                .from(item)
                .join(memberItem)
                .on(item.id.eq(memberItem.pid))
                .where(memberItem.uid.eq(uid))
                .fetch();
        return list;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}