package post.post.domain.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import post.post.domain.domain.entity.UserEntity;
import post.post.domain.domain.repository.UserRepository;
import post.post.domain.presentation.dto.req.UserAddRequestDto;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void register(UserAddRequestDto userAddRequestDto){
        userRepository.save(UserEntity.saveUserBuilder()
                .name(userAddRequestDto.name())
                .email(userAddRequestDto.email())
                .cost(1000000L)
                .build());
    }

}
