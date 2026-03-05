package post.post.domain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.post.domain.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
