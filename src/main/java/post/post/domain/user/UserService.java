package post.post.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.post.domain.user.dto.req.UserAddRequestDto;
import post.post.global.exception.BusinessException;
import post.post.global.exception.ErrorCode;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void register(UserAddRequestDto userAddRequestDto){
        userRepository.save(UserEntity.saveUserBuilder()
                .name(userAddRequestDto.name())
                .email(userAddRequestDto.email())
                .initialBalance(1000000L)
                .build());
    }

    @Transactional
    public void deductUserBalance(String userEmail, Long amount){
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        user.deductBalance(amount);
    }
}
