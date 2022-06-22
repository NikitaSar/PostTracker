package PostTracker.services;

import PostTracker.models.PostalItemStatus;
import PostTracker.repositories.PostalItemStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostalItemStatusService {
    private final PostalItemStatusRepository statusRepository;

    public List<PostalItemStatus> getAll() {
        return statusRepository.findAll();
    }

    public void addStatus(PostalItemStatus status) {
        statusRepository.save(status);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
