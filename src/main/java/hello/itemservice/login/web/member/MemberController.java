package hello.itemservice.login.web.member;
import hello.itemservice.login.domain.member.Member;
import hello.itemservice.login.service.MemberServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceV1 memberService;
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "members/addMemberForm";
        }
        memberService.save(member);
        return "redirect:/";
    }
}