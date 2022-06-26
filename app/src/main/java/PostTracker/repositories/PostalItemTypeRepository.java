package PostTracker.repositories;

import PostTracker.models.PostalItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostalItemTypeRepository extends JpaRepository<PostalItemType, Long> {
    Optional<PostalItemType> findByName(String name);
}
