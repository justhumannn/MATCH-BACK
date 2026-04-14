package post.post.domain.betting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface BettingRepository extends JpaRepository<BettingEntity, Long>, BettingRepositoryCustom {
    @Modifying
    @Query("UPDATE BettingEntity b SET b.status = :newStatus WHERE b.time <= :now AND b.status != :newStatus")
    void updateStatusForExpiredBettings(@Param("newStatus") String newStatus, @Param("now") LocalDateTime now);
}
