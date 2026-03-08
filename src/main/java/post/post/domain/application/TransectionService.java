package post.post.domain.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.post.domain.domain.entity.BettingEntity;
import post.post.domain.domain.entity.TransectionEntity;
import post.post.domain.domain.entity.UserEntity;
import post.post.domain.domain.repository.BettingRepository;
import post.post.domain.domain.repository.TransectionRepository;
import post.post.domain.domain.repository.UserRepository;
import post.post.domain.presentation.dto.req.UserBettingRequestDto;

@RequiredArgsConstructor
@Service
public class TransectionService {
    private final TransectionRepository transectionRepository;
    private final UserRepository userRepository;
    private final BettingRepository bettingRepository;

    @Transactional
    public void postUserBetting(UserBettingRequestDto userBettingRequestDto, String email){
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        BettingEntity betting = bettingRepository.findById(userBettingRequestDto.bettingId())
                .orElseThrow(() -> new IllegalArgumentException("해당 배팅을 찾을 수 없습니다."));
        if (user.getCost() < userBettingRequestDto.bettingCost()){
            throw new IllegalArgumentException("보유금보다 높은 금액은 배팅할 수 없습니다.");
        }
        transectionRepository.save(userBettingRequestDto.toEntity(user,betting));
    }
}
