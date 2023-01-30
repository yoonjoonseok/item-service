package hello.itemservice.config;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.login.domain.member.JpaMemberRepository;
import hello.itemservice.login.domain.member.MemberRepository;
import hello.itemservice.login.domain.member.SpringDataJpaMemberRepository;
import hello.itemservice.login.service.MemberService;
import hello.itemservice.login.service.MemberServiceV1;
import hello.itemservice.repository.ItemHistoryRepository;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.MemberItemRepository;
import hello.itemservice.repository.jpa.*;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {
    private final SpringDataJpaItemRepository springDataJpaItemRepository;
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;
    private final SpringDataJpaMemberItemRepository springDataJpaMemberItemRepository;
    private final SpringDataJpaItemHistoryRepository springDataJpaItemHistoryRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository(), memberItemRepository(),itemHistoryRepository());
    }
    @Bean
    public MemberService memberService() { return new MemberServiceV1(memberRepository());}
    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV2(springDataJpaItemRepository,jpaQueryFactory());
    }
    @Bean
    public MemberRepository memberRepository() { return new JpaMemberRepository(springDataJpaMemberRepository,jpaQueryFactory());}

    @Bean
    public MemberItemRepository memberItemRepository() { return new JpaMemberItemRepository(springDataJpaMemberItemRepository);}

    @Bean
    public ItemHistoryRepository itemHistoryRepository() { return new JpaItemHistoryRepository(springDataJpaItemHistoryRepository);}

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}