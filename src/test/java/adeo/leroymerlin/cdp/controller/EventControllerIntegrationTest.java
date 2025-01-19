package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.dto.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EventControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findEventsTest() throws Exception {
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void findEventsFilteredTest() throws Exception {
        String query = "graves";
        mockMvc.perform(get("/api/events/search/{query}",  query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deleteEventTest() throws Exception {
        Long eventId = 1000L;

        // First we fetch all events, we find 5 and id 1000 is there
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[?(@.id == " + eventId + ")]").exists());

        // We then delete an element
        mockMvc.perform(delete("/api/events/{id}", eventId))
                .andExpect(status().isOk());

        // We ensure that element is deleted since now size is 4 and id isn't there
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[?(@.id == " + eventId + ")]").doesNotExist());
    }

    @Test
    public void deleteEventTest_EventDoesntExist() throws Exception {
        Long eventId = 9999L;
        // We then delete an element
        mockMvc.perform(delete("/api/events/{id}", eventId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateEventTest() throws Exception {
        Long eventId = 1000L;
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        eventDTO.setNbStars(4);

        // ensures the event exists
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == " + eventId + ")]").exists());

        // Update the event with id 1000
        mockMvc.perform(put("/api/events/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk());

        // Check that nbStars for event 1000 is uodated
        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == " + eventId + ")]").exists())
                .andExpect(jsonPath("$[?(@.id == " + eventId + ")].nbStars").value(4));
    }

    @Test
    public void updateEventTest_EventIsNotFound() throws Exception {
        Long eventId = 9999L;
        EventDTO eventDTO = new EventDTO();

        mockMvc.perform(put("/api/events/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isNotFound());
    }
}