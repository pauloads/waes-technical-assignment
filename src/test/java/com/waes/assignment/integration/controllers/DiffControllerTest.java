package com.waes.assignment.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.assignment.diff.application.dto.DiffRequestDTO;
import com.waes.assignment.diff.domain.model.Diff;
import com.waes.assignment.diff.domain.model.LeftSide;
import com.waes.assignment.diff.domain.model.RightSide;
import com.waes.assignment.diff.domain.repository.DiffRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DiffRepository diffRepository;

    private ObjectMapper objectMapper;

    private static final String RIGHT_SIDE_ENDPOINT = "/v1/diff/1/right";

    private static final String LEFT_SIDE_ENDPOINT = "/v1/diff/1/left";

    private static final String BASE_64_ENCODED_JSON = "eyJiYW5kIjp7Im5hbWUiOiJQaW5rIEZsb3lkIiwibWVtYmVycyI6W3sibmFtZSI6IkRhdmlkIEdpbG1vdXIifSx7Im5hbWUiOiJTeWQgQmFycmV0In0seyJuYW1lIjoiUm9nZXIgV2F0ZXJzIn0seyJuYW1lIjoiUmljaGFyZCBXcmlnaHQifSx7Im5hbWUiOiJOaWNrIE1hc29uIn1dfX0=";

    private static final String DIFFERENCE_ENDPOINT = "/v1/diff/1";

    @Before
    public void setUp() throws Exception {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void should_save_left_side() throws Exception {
        DiffRequestDTO requestDTO = new DiffRequestDTO();
        requestDTO.setBase64EncodedJson(BASE_64_ENCODED_JSON);

        String requestBody = this.objectMapper.writeValueAsString(requestDTO);

        this.mockMvc.perform(post(LEFT_SIDE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_save_right_side() throws Exception {
        DiffRequestDTO requestDTO = new DiffRequestDTO();
        requestDTO.setBase64EncodedJson(BASE_64_ENCODED_JSON);

        String requestBody = this.objectMapper.writeValueAsString(requestDTO);

        this.mockMvc.perform(post(RIGHT_SIDE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_404_when_diff_not_found() throws Exception {
        this.mockMvc.perform(get(DIFFERENCE_ENDPOINT))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_422_when_one_side_is_null() throws Exception {
        Diff diff = new Diff(1l);
        LeftSide leftSide = new LeftSide(BASE_64_ENCODED_JSON);
        diff.setLeft(leftSide);

        diffRepository.save(diff);

        this.mockMvc.perform(get(DIFFERENCE_ENDPOINT))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_diff() throws Exception {
        Diff diff = new Diff(1l);
        LeftSide leftSide = new LeftSide(BASE_64_ENCODED_JSON);
        RightSide rightSide = new RightSide(BASE_64_ENCODED_JSON);
        diff.setLeft(leftSide);
        diff.setRight(rightSide);

        diffRepository.save(diff);

        this.mockMvc.perform(get(DIFFERENCE_ENDPOINT))
                .andExpect(status().isOk());
    }
}
