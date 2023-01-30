package hello.itemservice.login.domain.member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {
}
