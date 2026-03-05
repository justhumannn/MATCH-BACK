package post.post.domain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import post.post.domain.domain.entity.TransectionEntity;

import java.util.List;

public interface TransectionRepository extends JpaRepository<TransectionEntity,Long> {
}
