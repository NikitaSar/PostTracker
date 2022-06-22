package PostTracker.repositories;

import PostTracker.models.PostOffice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostOfficeRepository extends PagingAndSortingRepository<PostOffice, Long> {
    Optional<PostOffice> findByPostalCode(String postalCode);
}
