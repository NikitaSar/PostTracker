package PostTracker;

import PostTracker.handlers.RestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public abstract class BaseControllerTest {

    protected final ObjectMapper mapper = new ObjectMapper();

    protected static MockMvc mvc;

    protected static void baseSetup(Object controller) {
        mvc = standaloneSetup(controller)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

}
