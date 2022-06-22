package PostTracker.repositories;

import PostTracker.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    List<Tracking> findByPostalItemId(Long postalItemId);
}
