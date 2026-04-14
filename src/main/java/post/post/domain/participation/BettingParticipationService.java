package post.post.domain.participation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.post.domain.betting.BettingEntity;
import post.post.domain.betting.BettingRepository;
import post.post.domain.participation.dto.req.UserBettingRequestDto;
import post.post.domain.participation.dto.res.ListUserMyBettingResponseDto;
import post.post.domain.participation.dto.res.UserMyBettingResponseDto;
import post.post.domain.user.UserEntity;
import post.post.domain.user.UserRepository;
import post.post.global.exception.BusinessException;
import post.post.global.exception.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BettingParticipationService {
    private final BettingParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final BettingRepository bettingRepository;

    @Transactional
    public void postUserBetting(UserBettingRequestDto userBettingRequestDto, String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        BettingEntity betting = bettingRepository.findById(userBettingRequestDto.bettingId())
                .orElseThrow(() -> new BusinessException(ErrorCode.BETTING_NOT_FOUND));

        if ("종료".equals(betting.getStatus())) {
            throw new BusinessException(ErrorCode.BETTING_ALREADY_ENDED);
        }

        // 유저 잔액 차감 (내부적으로 잔액 확인 및 예외 발생 처리)
        user.deductBalance(userBettingRequestDto.bettingCost());

        participationRepository.save(userBettingRequestDto.toEntity(user, betting));
    }

    @Transactional(readOnly = true)
    public ListUserMyBettingResponseDto getMyBettingHistory(String email){
        List<UserMyBettingResponseDto> history = participationRepository.findMyBettingHistoryByEmail(email);
        return ListUserMyBettingResponseDto.of(history);
    }
}
