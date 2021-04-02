package school.web.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import school.model.service.UserServiceModel;
import school.service.UserService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    final String email = "user@mail.com";

    @Test
    @WithMockUser(username = "user1", password = "pwd", authorities = "ADMIN")
    public void getAllUsers_Ok() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new UserServiceModel().setEmail(email)));
        this.mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(content().string(containsString(email)));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", authorities = "USER")
    public void getAllUser_Forbidden() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new UserServiceModel().setEmail(email)));
        this.mockMvc.perform(get("/api/users")).andExpect(status().isForbidden());
    }

    @Test
    public void getAllUser_Redirect() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new UserServiceModel().setEmail(email)));
        this.mockMvc.perform(get("/api/users")).andExpect(status().is3xxRedirection());
    }

}

