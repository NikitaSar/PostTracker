package PostTracker.repositories;

import PostTracker.models.PostalItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostalItemStatusRepository extends JpaRepository<PostalItemStatus, Long> {
    Optional<PostalItemStatus> findByName(String name);
}
