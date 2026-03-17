package post.post.domain.participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import post.post.domain.participation.dto.res.UserMyBettingResponseDto;

import java.util.List;

public interface BettingParticipationRepository extends JpaRepository<BettingParticipation, Long> {
    @Query("SELECT new post.post.domain.participation.dto.res.UserMyBettingResponseDto(" +
            "bp.betting.title, bp.bettingTeam, bp.bettingCost) " +
            "FROM BettingParticipation bp " +
            "WHERE bp.user.email = :email")
    List<UserMyBettingResponseDto> findMyBettingHistoryByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM BettingParticipation bp WHERE bp.betting.id = :bettingId")
    void deleteByBettingId(@Param("bettingId") Long bettingId);
}
