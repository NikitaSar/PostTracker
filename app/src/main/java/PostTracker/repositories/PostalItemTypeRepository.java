package PostTracker.repositories;

import PostTracker.models.PostalItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalItemTypeRepository extends JpaRepository<PostalItemType, Long> {
}
