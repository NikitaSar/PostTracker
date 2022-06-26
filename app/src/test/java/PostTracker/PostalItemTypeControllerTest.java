package PostTracker;

import PostTracker.controllers.PostalItemTypeController;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PostalItemTypeControllerTest extends BaseControllerTest {

    final String BASE_URL = "/v1/type";

    @BeforeAll
    public static void setup(@Autowired PostalItemTypeController ctrl) {
        baseSetup(ctrl);
    }

    @Test
    public  void addType_success() throws Exception {
        var jsonContent = "{\"name\": \"Бандероль\"}";
        mvc.perform(post(BASE_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
        ).andExpect(status().isCreated());
    }

    @Test
    public  void addType_alreadyExists() throws Exception {
        var jsonContent = "{\"name\": \"Письмо\"}";
        var request = post(BASE_URL + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent);
        mvc.perform(request).andExpect(status().isCreated());
        mvc.perform(request).andExpect(status().isConflict());
    }

    @Test
    public void getDetailType_notFound() throws Exception {
        mvc.perform(delete(BASE_URL + "/{id}", Integer.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

}
