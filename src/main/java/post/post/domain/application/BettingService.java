package post.post.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.post.domain.domain.repository.BettingRepository;
import post.post.domain.presentation.dto.res.BettingResponseDto;
import post.post.domain.presentation.dto.res.ListBettingResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BettingService {

    private final BettingRepository bettingRepository;

    @Transactional(readOnly = true)
    public ListBettingResponseDto getBettingCard(){
        List<BettingResponseDto> responseDtoList = bettingRepository.findAllBettingCardsWithStats();
        return ListBettingResponseDto.of(responseDtoList);
    }
}