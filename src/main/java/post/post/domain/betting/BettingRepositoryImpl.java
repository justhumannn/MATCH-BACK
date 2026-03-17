package post.post.domain.betting;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import post.post.domain.betting.dto.res.BettingResponseDto;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BettingRepositoryImpl implements BettingRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<BettingResponseDto> rowMapper = (rs, rowNum) -> BettingResponseDto.builder()
            .id(rs.getLong("id"))
            .title(rs.getString("title"))
            .blue(rs.getString("blue"))
            .red(rs.getString("red"))
            .result(rs.getString("result"))
            .status(rs.getString("status"))
            .blueBettingCount(rs.getLong("blue_count"))
            .redBettingCount(rs.getLong("red_count"))
            .blueBettingCost(rs.getLong("blue_cost"))
            .redBettingCost(rs.getLong("red_cost"))
            .build();

    @Override
    public List<BettingResponseDto> findAllBettingCardsWithStats() {
        String sql = "SELECT b.id, b.title, b.blue, b.red, b.result, b.status, " +
                "COUNT(CASE WHEN bp.betting_team = 'blue' THEN 1 END) AS blue_count, " +
                "COUNT(CASE WHEN bp.betting_team = 'red' THEN 1 END) AS red_count, " +
                "COALESCE(SUM(CASE WHEN bp.betting_team = 'blue' THEN bp.betting_cost ELSE 0 END), 0) AS blue_cost, " +
                "COALESCE(SUM(CASE WHEN bp.betting_team = 'red' THEN bp.betting_cost ELSE 0 END), 0) AS red_cost " +
                "FROM bettings b " +
                "LEFT JOIN betting_participations bp ON bp.betting_id = b.id " +
                "GROUP BY b.id, b.title, b.blue, b.red, b.result, b.status";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<BettingResponseDto> findBettingCardWithStatsById(Long bettingId) {
        String sql = "SELECT b.id, b.title, b.blue, b.red, b.result, b.status, " +
                "COUNT(CASE WHEN bp.betting_team = 'blue' THEN 1 END) AS blue_count, " +
                "COUNT(CASE WHEN bp.betting_team = 'red' THEN 1 END) AS red_count, " +
                "COALESCE(SUM(CASE WHEN bp.betting_team = 'blue' THEN bp.betting_cost ELSE 0 END), 0) AS blue_cost, " +
                "COALESCE(SUM(CASE WHEN bp.betting_team = 'red' THEN bp.betting_cost ELSE 0 END), 0) AS red_cost " +
                "FROM bettings b " +
                "LEFT JOIN betting_participations bp ON bp.betting_id = b.id " +
                "WHERE b.id = ? " +
                "GROUP BY b.id, b.title, b.blue, b.red, b.result, b.status";

        return jdbcTemplate.query(sql, rowMapper, bettingId).stream().findFirst();
    }
}
