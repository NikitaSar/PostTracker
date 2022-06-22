package PostTracker.repositories;

import PostTracker.models.PostalItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostalItemRepository extends PagingAndSortingRepository<PostalItem, Long> {
    @Query("SELECT MAX(id) FROM PostalItem")
    Optional<Long> findMaxId();
    Optional<PostalItem> findTop1ByOrderByIdDesc();
}
