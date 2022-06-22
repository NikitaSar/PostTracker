package PostTracker.repositories;

import PostTracker.models.PostalItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalItemStatusRepository extends JpaRepository<PostalItemStatus, Long> {
}
