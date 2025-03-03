package br.com.danilo.parallel.controller;

import br.com.danilo.parallel.model.CommonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ParallelControllerIntegrationTest {
    private static final Logger logger = LoggerFactory.getLogger(ParallelControllerIntegrationTest.class.getSimpleName());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllDataNormal_shouldReturnOkAndCommonData() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/parallel/normal")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        CommonData response = objectMapper.readValue(content, CommonData.class);
        logger.info(response.toString());

        assertNotNull(response.getCar());
        assertNotNull(response.getPen());
        assertNotNull(response.getTable());
        assertNotNull(response.getChair());


    }


}