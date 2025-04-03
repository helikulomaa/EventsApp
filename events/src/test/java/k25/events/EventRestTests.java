package k25.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EventRestTests {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testGetEvents() throws Exception {
        mockMvc.perform(get("/api/events")).andExpect(status().isOk());
    }

    @Test
    public void testGetEvent() throws Exception {
        mockMvc.perform(get("/api/events/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetEventBadRequest() throws Exception {
        mockMvc.perform(get("/api/events/abc")).andExpect(status().isBadRequest());
    }

    @Test
    public void responseTypeJson() throws Exception {
        mockMvc.perform(get("/api/events")).andExpect(content().contentType("application/json"));
    }

}
