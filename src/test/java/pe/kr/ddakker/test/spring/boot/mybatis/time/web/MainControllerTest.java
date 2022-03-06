package pe.kr.ddakker.test.spring.boot.mybatis.time.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pe.kr.ddakker.test.spring.boot.mybatis.time.vo.User;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
//@Rollback
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Test
    void S1_조회() throws Exception {
        final ResultActions actions = mvc.perform(get("/user")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0]name").value("dk"))
        ;
    }

    @Test
    void S2_등록() throws Exception {
        User user = new User("dk1", 2);
        String content = objectMapper.writeValueAsString(user);
        final ResultActions actions = mvc.perform(post("/user")
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(user.getName()))
                .andExpect(jsonPath("age").value(user.getAge()))
        ;
    }

    @Test
    void S3_업데이트() throws Exception {
        final ResultActions actions = mvc.perform(put("/user/1/birthday")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("birthday").value("2000-01-01"))
        ;
    }

    @Test
    void S4_삭제_업데이트() throws Exception {
        final ResultActions actions = mvc.perform(delete("/user/2")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());

        actions
                .andExpect(status().isOk())
//                .andExpect(jsonPath("birthday").value("2000-01-01"))
        ;
    }
}
