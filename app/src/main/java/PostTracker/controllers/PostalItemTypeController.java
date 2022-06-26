package PostTracker.controllers;

import PostTracker.dto.PostalItemTypeDto;
import PostTracker.models.PostalItemType;
import PostTracker.services.PostalItemTypeService;
import PostTracker.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/type")
public class PostalItemTypeController {
    private final PostalItemTypeService typeService;

    @GetMapping("/")
    public List<PostalItemType> getAll() {
        return typeService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addType(@Valid @RequestBody PostalItemTypeDto typeDto) {
        typeService.addType(DtoMapper.INSTANCE.fromPostalItemTypeDto(typeDto));
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable("id") Long idType) {
        typeService.deleteType(idType);
    }
}
