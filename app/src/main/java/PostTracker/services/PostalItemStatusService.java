package PostTracker.services;

import PostTracker.handlers.AlreadyExistsException;
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
        if (statusRepository.findByName(status.getName()).isPresent())
            throw new AlreadyExistsException(String.format("Status '%s' already exists.", status.getName()));
        statusRepository.save(status);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
