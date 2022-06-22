package PostTracker.services;

import PostTracker.models.PostalItemType;
import PostTracker.repositories.PostalItemTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostalItemTypeService {
    private final PostalItemTypeRepository typeRepository;

    public List<PostalItemType> getAll() {
        return typeRepository.findAll();
    }

    public void addType(PostalItemType itemType) {
        typeRepository.save(itemType);
    }

    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
