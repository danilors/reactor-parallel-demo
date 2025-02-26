package br.com.danilo.parallel.controller;

import br.com.danilo.parallel.common.model.Car;
import br.com.danilo.parallel.common.model.CommonData;
import br.com.danilo.parallel.common.service.ParallelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ParalleController.class)
public class ParalleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParallelService parallelService;

    @Test
    public void getAll_shouldReturnCommonData() throws Exception {
        // Arrange
        CommonData expectedData = new CommonData();
        expectedData.setCar(new Car(1, "Test Car", 2023));
        when(parallelService.processServices()).thenReturn(expectedData);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.car.id").value(1))
                .andExpect(jsonPath("$.car.model").value("Test Car"))
                .andExpect(jsonPath("$.car.year").value(2023));
    }

    @Test
    public void getAll_shouldReturnEmptyCommonData() throws Exception {
        // Arrange
        CommonData expectedData = new CommonData();
        when(parallelService.processServices()).thenReturn(expectedData);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.car").doesNotExist())
                .andExpect(jsonPath("$.table").doesNotExist())
                .andExpect(jsonPath("$.chair").doesNotExist())
                .andExpect(jsonPath("$.pen").doesNotExist());
    }
}