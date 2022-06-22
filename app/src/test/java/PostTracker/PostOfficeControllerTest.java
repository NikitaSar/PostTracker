package PostTracker;

import PostTracker.controllers.PostOfficeController;
import PostTracker.dto.PostOfficeDto;
import PostTracker.handlers.RestExceptionHandler;
import PostTracker.models.PostOffice;
import PostTracker.repositories.PostOfficeRepository;
import PostTracker.services.PostOfficeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PostOfficeControllerTest extends BaseControllerTest {
    final String BASE_URL = "/v1/post-office/";

    @Autowired
    protected PostOfficeRepository repo;

    @BeforeAll
    public static void setup(@Autowired PostOfficeController postOfficeController) {
        baseSetup(postOfficeController);
    }

    @BeforeEach
    public void beforeEach() {
        repo.deleteAll();
    }

    @Test
    public void addPostOffice_Success() throws Exception {
        var postOffice = new PostOfficeDto("123456", "name", "address");
        var jsonBody = mapper.writeValueAsString(postOffice);
        mvc.perform(
            post(BASE_URL + "add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody)
        ).andExpect(status().isCreated());

        assertTrue(
                repo.
                 findByPostalCode(postOffice.getPostalCode())
                 .isPresent());
    }

    @Test
    public void addPostOffice_emptyBody() throws Exception {
        mvc.perform(
                post(BASE_URL + "add").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void addPostOffice_invalidPostalCode() throws Exception {
        var postOffice = new PostOfficeDto(null, "name", "address");
        var jsonBody = mapper.writeValueAsString(postOffice);
        mvc.perform(
                post(BASE_URL + "add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void getAllOffices_success() throws Exception {
        var fakeOffices = IntStream
                .range(1, 100)
                .mapToObj(num -> new PostOffice(
                            null,
                            String.valueOf(num),
                            String.format("Name %d", num),
                            String.format("Address %d", num))
                )
                .peek(repo::save)
                .collect(Collectors.toList());

        var expectedContent = mapper.writeValueAsString(fakeOffices.subList(10, 15));
        mvc.perform(get(BASE_URL)
            .param("page", "2")
            .param("size", "5")
        )
        .andExpectAll(
            status().isOk(),
            content().string(expectedContent)
        );
    }

    @Test
    public void getAllOffices_emptyContent() throws Exception {
        mvc.perform(get(BASE_URL))
            .andExpectAll(
                status().isOk(),
                content().string("[]")
            );
    }

    @Test
    public void getPostOfficeById_success() throws Exception {
        var postOffice = new PostOffice(null, "123456", "name", "address");
        repo.save(postOffice);
        assertNotNull(postOffice.getId());
        var expectedContent = mapper.writeValueAsString(postOffice);
        mvc.perform(get(BASE_URL + "/{id}", postOffice.getId()))
            .andExpectAll(
                    status().isOk(),
                    content().string(expectedContent)
            );
    }

    @Test
    public void getPostOfficeById_notExists() throws Exception {
        Long NOT_EXISTS_ID = Long.MAX_VALUE;
        mvc.perform(get(BASE_URL + "/{id}", NOT_EXISTS_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePostOffice_success() throws Exception {
        var office = new PostOffice(null, "123456", "name", "address");
        repo.save(office);
        mvc.perform(delete(BASE_URL + "/{id}", office.getId()))
                .andExpect(status().isOk());
        assertEquals(0L, repo.count());
    }

    @Test
    public void deletePostOffice_idNotExists() throws Exception {
        mvc.perform(delete(BASE_URL + "/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
