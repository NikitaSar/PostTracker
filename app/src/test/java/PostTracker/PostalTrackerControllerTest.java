package PostTracker;

import PostTracker.controllers.TrackerController;
import PostTracker.dto.PostalItemDto;
import PostTracker.dto.TrackingDto;
import PostTracker.models.*;
import PostTracker.repositories.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PostalTrackerControllerTest extends BaseControllerTest {
    final String BASE_URL = "/v1/track";
    @Autowired
    PostalItemTypeRepository typeRepository;
    @Autowired
    PostalItemStatusRepository statusRepository;
    @Autowired
    PostOfficeRepository officeRepository;
    @Autowired
    PostalItemRepository itemRepository;
    @Autowired
    TrackingRepository trackingRepository;

    @BeforeAll
    public static void setup(@Autowired TrackerController ctrl) {
        baseSetup(ctrl);
    }

    @Test
    public void registerPostalItem_success() throws Exception {
        var address = "address";
        var name = "recipient name";
        var itemType = new PostalItemType(null, "newType");
        typeRepository.save(itemType);
        var itemStatus = new PostalItemStatus(null, "newStatus");
        statusRepository.save(itemStatus);
        var office = new PostOffice(null, "789905", "newName", "newAddress");
        officeRepository.save(office);
        var jsonContent = mapper.writeValueAsString(new PostalItemDto(
                itemType.getId(), office.getId(), address, name, itemStatus.getId()));

        mvc.perform(post(BASE_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
        ).andExpect(status().isCreated())
                .andDo(res -> {
                    var actual = mapper.readValue(res.getResponse().getContentAsString(), PostalItem.class);
                    assertEquals(itemType.getName(), actual.getType().getName());
                    assertEquals(office.getPostalCode(), actual.getRecipientPostOffice().getPostalCode());
                    assertEquals(address, actual.getRecipientAddress());
                    assertEquals(name, actual.getRecipientName());
                });
    }

    @Test
    public void registerPostalItem_notExistsStatusId() throws Exception {
        var jsonContent = mapper.writeValueAsString(new PostalItemDto(
                1L,
                1L,
                "address",
                "name",
                Long.MAX_VALUE));
        mvc.perform(post(BASE_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
        ).andExpect(status().isNotFound());
    }

    @Sql("/test_data.sql")
    @Test
    public void changeStatus_success() throws Exception {
        var postalItem = itemRepository.findTop1ByOrderByIdDesc().orElse(null);
        assertNotNull(postalItem, "Item cannot be null.");
        var status = new PostalItemStatus(null, "newStatus");
        statusRepository.save(status);
        mvc.perform(put(BASE_URL + "/{itemId}/{statusId}",
                        postalItem.getId(),
                        status.getId()
                        ))
            .andExpect(status().isOk());
        var actualTracking = trackingRepository
                .findByPostalItemId(postalItem.getId());
        assertNotNull(actualTracking);
        assertEquals(2, actualTracking.size());
        var actual=  actualTracking.stream().
                reduce((l,c) -> c)
                .orElse(null);
        assertNotNull(actual);
        assertEquals("newStatus", actual.getStatus().getName());
    }

    @Sql("/tracking_fake.sql")
    @Test
    public void getTracking_success() throws Exception {
        var postalItem = itemRepository.findTop1ByOrderByIdDesc().orElse(null);
        assertNotNull(postalItem);
        var result = mvc.perform(get(BASE_URL + "/{id}", postalItem.getId()))
                .andExpect(status().isOk())
                .andDo(res -> {
                     var tracking = mapper.readValue(
                             res.getResponse().getContentAsString(),
                             TrackingDto.class);
                     assertEquals(3, tracking.getHistory().size());
                     assertEquals("Status 4", tracking.getHistory().get(0).getStatus());
                     assertEquals("Status 6", tracking.getHistory().get(2).getStatus());
                });
    }
}
