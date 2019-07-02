package com.wearewaes.demo.controller;


import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class DifferenceControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();


    @Test
    public void addLeft() {
        RequestDto requestDto = new RequestDto("V2VyZXNwYXJ0YQ==");
        HttpEntity<RequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/5/left"), HttpMethod.POST, entity, Object.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void addLeftClientErrorEmpty() {
        RequestDto requestDto = new RequestDto("");
        HttpEntity<RequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/5/left"), HttpMethod.POST, entity, Object.class);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void addLeftClientErrorNull() {
        RequestDto requestDto = new RequestDto();
        HttpEntity<RequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/5/left"), HttpMethod.POST, entity, Object.class);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void addLeftClientErrorValueNotBase64() {
        RequestDto requestDto = new RequestDto("d://hello world");
        HttpEntity<RequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/5/left"), HttpMethod.POST, entity, Object.class);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void addRight() {
        RequestDto requestDto = new RequestDto("b3Igbm90");
        HttpEntity<RequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/5/right"), HttpMethod.POST, entity, Object.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void getDiffNotFound() {
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/6"), HttpMethod.GET, null, Object.class);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void getDiffBadRequest() {
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/4"), HttpMethod.GET, null, Object.class);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void getDiffEqualContent() {
        ResponseEntity<ResponseDto> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/1"), HttpMethod.GET, null, ResponseDto.class);
        ResponseDto result = response.getBody();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(result);
        assertEquals(true, result.getIsContentEqual());
        assertNull(result.getIsSizeEqual());
        assertNull(result.getOffset());
        assertNull(result.getLength());
    }

    @Test
    public void getDiffNotEqualContentNotEqualSize() {
        ResponseEntity<ResponseDto> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/2"), HttpMethod.GET, null, ResponseDto.class);
        ResponseDto result = response.getBody();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(result);
        assertNull(result.getIsContentEqual());
        assertFalse(result.getIsSizeEqual());
        assertNull(result.getOffset());
        assertNull(result.getLength());
    }

    @Test
    public void getDiffNotEqualContentEqualSize() {
        ResponseEntity<ResponseDto> response = restTemplate.exchange(createURLWithPort("/api/v1/diff/3"), HttpMethod.GET, null, ResponseDto.class);
        ResponseDto result = response.getBody();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(result);
        assertNull(result.getIsContentEqual());
        assertNull(result.getIsSizeEqual());
        assertEquals(Integer.valueOf(0), result.getOffset());
        assertEquals(Integer.valueOf(3), result.getLength());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
