package PostTracker.controllers;

import PostTracker.dto.PostalItemStatusDto;
import PostTracker.models.PostalItemStatus;
import PostTracker.services.PostalItemStatusService;
import PostTracker.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/status")
public class PostalItemStatusController {
    private final PostalItemStatusService statusService;

    @GetMapping("/")
    public List<PostalItemStatus> getAll() {
       return statusService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStatus(@Valid @RequestBody PostalItemStatusDto statusDto) {
        statusService.addStatus(DtoMapper.INSTANCE.fromPostalItemStatusDto(statusDto));
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable("id") Long idStatus) {
        statusService.deleteStatus(idStatus);
    }

}
