package PostTracker.services;

import PostTracker.models.PostOffice;
import PostTracker.repositories.PostOfficeRepository;
import PostTracker.services.PostOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostOfficeService {

    private final PostOfficeRepository postOfficeRepository;

    public void addOffice(PostOffice postOffice) {
        postOfficeRepository.save(postOffice);
    }

    public List<PostOffice> getAll(int page, int size) {
        return postOfficeRepository
                .findAll(PageRequest.of(page, size))
                .toList();
    }

    public void deleteById(Long officeId) {
        postOfficeRepository.deleteById(officeId);
    }

    public PostOffice getPostOfficeById(Long postOfficeId) {
        var office = postOfficeRepository.findById(postOfficeId);
        if (!office.isPresent())
            throw new NoSuchElementException(String.format("Id %d not exists.", postOfficeId));
        return office.get();
    }
}
