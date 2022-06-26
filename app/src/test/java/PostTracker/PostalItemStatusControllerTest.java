package PostTracker;

import PostTracker.controllers.PostalItemStatusController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PostalItemStatusControllerTest extends BaseControllerTest {

    private final String BASE_URL = "/v1/status";

    @BeforeAll
    public static void setup(@Autowired PostalItemStatusController ctrl) {
        baseSetup(ctrl);
    }

    @Test
    public void addStatus_success() throws Exception {
        var jsonContent = "{ \"name\": \"Принято в почтовом отделении\" }";
        mvc.perform(post(BASE_URL + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
            .andExpect(status().isCreated());
    }

    @Test
    public void addStatus_alreadyExists() throws Exception {
        var jsonContent = "{ \"name\": \"Получено адресатом\" }";
        var request = post(BASE_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent);
        mvc.perform(request).andExpect(status().isCreated());
        mvc.perform(request).andExpect(status().isConflict());
    }

    @Test
    public void deleteStatus_notFound() throws Exception {
        mvc.perform(delete(BASE_URL + "/{id}", Integer.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}
