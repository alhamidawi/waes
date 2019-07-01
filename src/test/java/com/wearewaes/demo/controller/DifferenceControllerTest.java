package com.wearewaes.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.BadRequestException;
import com.wearewaes.demo.exception.ExceptionHandlerAdvice;
import com.wearewaes.demo.service.DifferenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DifferenceControllerTest {

    @InjectMocks
    private DifferenceController differenceController;
    @Mock
    private DifferenceService differenceService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(differenceController)
                .setControllerAdvice(new ExceptionHandlerAdvice())
                .build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void addLeft() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto("SGVsbG9FdmVyeWJvZHlIaURvY3Rvck5pY2s="));

        mockMvc.perform(post("/v1/diff/{id}/left", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        verify(differenceService, times(1)).add(anyLong(), anyString(), any(Side.class));
    }

    @Test
    public void addLeftBadRequestNullValue() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto());

        mockMvc.perform(post("/v1/diff/{id}/left", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addLeftBadRequestEmptyValue() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto(""));

        mockMvc.perform(post("/v1/diff/{id}/left", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addLeftBadRequestNotValidBase64() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto("D:SomeFile.xls"));

        mockMvc.perform(post("/v1/diff/{id}/left", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRight() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto("SGVsbG9FdmVyeWJvZHlIaURvY3Rvck5pY2s="));

        mockMvc.perform(post("/v1/diff/{id}/right", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());

        verify(differenceService, times(1)).add(anyLong(), anyString(), any(Side.class));
    }

    @Test
    public void addRightBadRequestNullValue() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto());

        mockMvc.perform(post("/v1/diff/{id}/right", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRightBadRequestEmptyValue() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto(""));

        mockMvc.perform(post("/v1/diff/{id}/right", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRightBadRequestNotValidBase64() throws Exception {
        String body = objectMapper.writeValueAsString(new RequestDto("D:SomeFile.xls"));

        mockMvc.perform(post("/v1/diff/{id}/right", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getDifferenceOk() throws Exception {
        ResponseDto result = new ResponseDto();
        result.setIsContentEqual(true);
        when(differenceService.getDifference(anyLong())).thenReturn(result);

        mockMvc.perform(get("/v1/diff/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getDifferenceNotFound() throws Exception {
        ResponseDto result = new ResponseDto();
        result.setIsContentEqual(true);
        when(differenceService.getDifference(anyLong())).thenThrow(new BadRequestException("Ups something went wrong"));

        mockMvc.perform(get("/v1/diff/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}