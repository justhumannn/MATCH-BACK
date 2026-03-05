package post.post.domain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import post.post.domain.domain.entity.BettingEntity;
import post.post.domain.presentation.dto.res.BettingResponseDto;

import java.util.List;

public interface BettingRepository extends JpaRepository<BettingEntity, Long> {

    // 배팅 카드 정보와 트랜잭션 통계를 한 번에 JOIN해서 DTO로 바로 가져옵니다.
    @Query("SELECT new post.post.domain.presentation.dto.res.BettingResponseDto(" +
            "b.title, b.blue, b.red, b.result, b.status, " +
            "SUM(CASE WHEN t.bettingTeam = 'blue' THEN 1L ELSE 0L END), " +
            "SUM(CASE WHEN t.bettingTeam = 'red' THEN 1L ELSE 0L END), " +
            "COALESCE(SUM(CASE WHEN t.bettingTeam = 'blue' THEN t.bettingCost ELSE 0L END), 0L), " +
            "COALESCE(SUM(CASE WHEN t.bettingTeam = 'red' THEN t.bettingCost ELSE 0L END), 0L)) " +
            "FROM BettingEntity b " +
            "LEFT JOIN TransectionEntity t ON t.bettingEntity = b " + // 배팅이 하나도 없는 카드도 조회해야 하므로 LEFT JOIN 사용
            "GROUP BY b.id, b.title, b.blue, b.red, b.result, b.status")
    List<BettingResponseDto> findAllBettingCardsWithStats();
}