package post.post.domain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import post.post.domain.domain.entity.TransectionEntity;
import post.post.domain.presentation.dto.res.UserMyBettingResponseDto;

import java.util.List;

public interface TransectionRepository extends JpaRepository<TransectionEntity,Long> {
    @Query("SELECT new post.post.domain.presentation.dto.res.UserMyBettingResponseDto(" +
            "t.bettingEntity.title, t.bettingTeam, t.bettingCost) " +
            "FROM TransectionEntity t " +
            "WHERE t.userEntity.email = :email")
    List<UserMyBettingResponseDto> findMyBettingHistoryByEmail(@Param("email") String email);
}
