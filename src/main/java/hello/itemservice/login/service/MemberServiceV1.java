package hello.itemservice.login.service;


import hello.itemservice.login.domain.member.Member;
import hello.itemservice.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
