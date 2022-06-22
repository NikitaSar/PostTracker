package PostTracker.controllers;

import PostTracker.dto.PostOfficeDto;
import PostTracker.models.PostOffice;
import PostTracker.services.PostOfficeService;
import PostTracker.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/post-office")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<PostOffice> postOfficeList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return postOfficeService.getAll(page, size);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostOffice(@RequestBody @Valid PostOfficeDto postOfficeDto) {
        postOfficeService.addOffice(DtoMapper.INSTANCE.fromPostOfficeDto(postOfficeDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deletePostOffice(@PathVariable("id") Long officeId) {
        postOfficeService.deleteById(officeId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostOffice getDetailsPostOffice(@PathVariable("id") Long postOfficeId) {
        return postOfficeService.getPostOfficeById(postOfficeId);
    }

}
