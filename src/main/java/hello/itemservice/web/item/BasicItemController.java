package hello.itemservice.web.item;
import hello.itemservice.domain.*;
import hello.itemservice.login.domain.member.Member;
import hello.itemservice.login.web.SessionConst;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemService itemService;

    public String getMemberName(HttpServletRequest request){
        return ((Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER)).getName();
    }
    public Long getMemberUid(HttpServletRequest request){
        return ((Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER)).getUid();
    }
    @GetMapping
    public String items(@ModelAttribute("itemSearch") ItemSearchCond itemSearch, Model model, HttpServletRequest request) {
        //List<Item> items =  itemService.findItems(itemSearch);
        List<ItemDTO> items = itemService.findMembersItem(getMemberUid(request));
        model.addAttribute("items", items);
        model.addAttribute("memberName",getMemberName(request));
        return "itemservice/items";
    }
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item =  itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "itemservice/item";
    }
    @GetMapping("/add")
    public String addForm() {
        return "itemservice/addForm";
    }
    @PostMapping("/add")
    public String addItemV6(@Valid @ModelAttribute Item item, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "itemservice/addForm";
        }
        Optional<Item> savedItem = itemService.findByLink(item.getLink());
        if(savedItem.isEmpty()){
            item.setOthers();
            savedItem = Optional.ofNullable(itemService.save(item));
        }
        itemService.save(new MemberItem(getMemberUid(request),savedItem.get().getId(),false));
        //redirectAttributes.addAttribute("itemId", savedItem.getId());
        //redirectAttributes.addAttribute("status", true);
        return "redirect:/items";
    }
    @PostMapping("/delete")
    public String checkedDelete(@RequestParam("id") long[] ids,HttpServletRequest request) {
        long uid = getMemberUid(request);
        for(long id:ids){
            MemberItemPK pk = new MemberItemPK(uid,id);
            itemService.delete(pk);
        }
        return "redirect:/items";
    }
    /*@GetMapping("/delete/{itemId}")
    public String delete(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return "redirect:/items";
    }*/
    @PostMapping("/update")
    public String checkedUpdate(@RequestParam("id") long[] ids) {
        ItemUpdateDto iud = new ItemUpdateDto();
        for(long id:ids){
            iud.setQuantity(itemService.findById(id).get().updateStock());
            itemService.update(id);
        }
        return "redirect:/items";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "itemservice/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemService.findById(itemId).get();
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/changeAlarm")
    public String changeAlarm(@PathVariable Long itemId, HttpServletRequest request){
        itemService.changeAlarm(new MemberItemPK(getMemberUid(request),itemId));
        return "redirect:/items";
    }

    @ResponseBody
    @GetMapping("/{itemId}/getGraph/getItemHistory")
    public List<ItemHistory> getItemHistory(@PathVariable Long itemId, HttpServletRequest request,Model model){
        List<ItemHistory> itemHistory = itemService.findItemHistory(itemId);
        return itemHistory;
    }

    @GetMapping("/{itemId}/getGraph")
    public String getGraph(@PathVariable Long itemId, HttpServletRequest request,Model model){
        return "itemservice/graph";
    }

    public void init() {
    }
}