package post.post.domain.betting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BettingRepository extends JpaRepository<BettingEntity, Long>, BettingRepositoryCustom {
}
