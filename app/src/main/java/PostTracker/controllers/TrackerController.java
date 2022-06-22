package PostTracker.controllers;

import PostTracker.dto.PostalItemDto;
import PostTracker.dto.TrackingDto;
import PostTracker.models.PostalItem;
import PostTracker.models.Tracking;
import PostTracker.services.PostTrackerService;
import PostTracker.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/track")
public class TrackerController {
    private final PostTrackerService trackerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public PostalItem registerPostalItem(@RequestBody @Valid PostalItemDto postalItemDto) {
        return trackerService.registerPostalItem(postalItemDto);
    }

    @PutMapping("/{itemId}/{statusId}")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(
        @PathVariable("itemId") Long postalItemId,
        @PathVariable("statusId") Long statusId) {
        trackerService.changeStatus(postalItemId, statusId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackingDto tracking(@PathVariable("id") Long postalItemId) {
        return DtoMapper.INSTANCE.fromTrackingList(trackerService.getTracking(postalItemId));
    }

}
