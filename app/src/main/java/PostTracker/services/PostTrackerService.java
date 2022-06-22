package PostTracker.services;

import PostTracker.dto.PostalItemDto;
import PostTracker.models.PostalItem;
import PostTracker.models.Tracking;
import PostTracker.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostTrackerService {
    private final PostalItemRepository itemRepository;
    private final PostalItemStatusRepository statusRepository;
    private final PostOfficeRepository officeRepository;
    private final PostalItemTypeRepository typeRepository;
    private final TrackingRepository trackingRepository;

    @Transactional
    public PostalItem registerPostalItem(PostalItemDto postalItemDto) {
        var itemType = typeRepository.findById(postalItemDto.getPostalItemTypeId()).orElseThrow();
        var status = statusRepository.getReferenceById(postalItemDto.getPostalStatusId());
        if (null == status.getName())
            throw new NoSuchElementException(String.format("itemStatusId %d not found.",
                    postalItemDto.getPostalStatusId()));
        var office = officeRepository.findById(postalItemDto.getRecipientPostOfficeId())
                .orElseThrow();
        var postalItem = new PostalItem(
                null,
                itemType,
                office,
                postalItemDto.getRecipientAddress(),
                postalItemDto.getRecipientName());

        itemRepository.save(postalItem);
        trackingRepository.save(new Tracking(
                null,
                new Timestamp(System.currentTimeMillis()),
                status,
                postalItem));

        return postalItem;
    }

    @Transactional
    public void changeStatus(Long postalItemId, Long statusId) {
        var postalItem = itemRepository.findById(postalItemId)
                .orElseThrow();
        var status = statusRepository.getReferenceById(statusId);
        itemRepository.save(postalItem);
        trackingRepository.save(new Tracking(
                null,
                new Timestamp(System.currentTimeMillis()),
                status,
                postalItem
        ));
    }

    public List<Tracking> getTracking(Long postalItemId) {
        return trackingRepository.findByPostalItemId(postalItemId);
    }
}
