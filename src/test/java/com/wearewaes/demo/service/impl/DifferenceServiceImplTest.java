package com.wearewaes.demo.service.impl;

import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.BadRequestException;
import com.wearewaes.demo.exception.NotFoundException;
import com.wearewaes.demo.model.Data;
import com.wearewaes.demo.service.DifferenceService;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DifferenceServiceImplTest {

    private DifferenceService differenceService;
    private Map<Long, Data> storage;
    private Data data1;
    private Data data2;
    private Data data3;
    private Data data4;


    @Before
    public void before() {

        byte[] input = Base64.decodeBase64("SGVsbG9FdmVyeWJvZHlIaURvY3Rvck5pY2s=");
        byte[] right = Base64.decodeBase64("R29vZGJheVdvcmxk");
        byte[] left = Base64.decodeBase64("SGVsbG9Xb3JsZA==");
        byte[] leftSameSize = Base64.decodeBase64("SGVsbG8=");
        byte[] rightSameSize = Base64.decodeBase64("T2xsZWg=");

        this.data1 = new Data();
        this.data1.setLeft(Base64.decodeBase64(input));
        this.data1.setRight(Base64.decodeBase64(input));

        this.data2 = new Data();
        this.data2.setRight(Base64.decodeBase64(right));
        this.data2.setLeft(Base64.decodeBase64(left));

        this.data3 = new Data();
        this.data3.setLeft(Base64.decodeBase64(leftSameSize));
        this.data3.setRight(Base64.decodeBase64(rightSameSize));

        this.data4 = new Data();
        this.data4.setLeft(input);


        this.storage = new HashMap<>();
        this.storage.put(1L, data1);
        this.storage.put(2L, data2);
        this.storage.put(3L, data3);
        this.storage.put(4L, data4);

        this.differenceService = new DifferenceServiceImpl(storage);
    }

    @Test
    public void addLeft() {
        String input = "T2xsZWgKSGVsbG8=";
        differenceService.add(6L, input, Side.LEFT);
    }


    @Test
    public void addRight() {
        String input = "V2VBcmVDaGFtcGlvbnM=";
        differenceService.add(6L, input, Side.RIGHT);
    }

    @Test
    public void getDifferenceEqual() {
        ResponseDto result = differenceService.getDifference(1L);
        assertNotNull(result);
        assertTrue(result.getIsContentEqual());
        assertNull(result.getIsSizeEqual());
        assertNull(result.getLength());
        assertNull(result.getOffset());
    }

    @Test
    public void getDifferenceContentNotEqualAndSizeNotEqual() {
        ResponseDto result = differenceService.getDifference(2L);
        assertNotNull(result);
        assertNotNull(result.getIsSizeEqual());
        assertFalse(result.getIsSizeEqual());
        assertNull(result.getIsContentEqual());
        assertNull(result.getLength());
        assertNull(result.getOffset());
    }

    @Test
    public void getDifferenceDifferentContentAndSameSize() {
        ResponseDto result = differenceService.getDifference(3L);
        assertNotNull(result);
        assertNull(result.getIsSizeEqual());
        assertNull(result.getIsContentEqual());
        assertNotNull(result.getLength());
        assertEquals(Integer.valueOf(0), result.getOffset());
        assertNotNull(result.getOffset());
        assertEquals(Integer.valueOf(3), result.getLength());
    }

    @Test(expected = NotFoundException.class)
    public void getDifferenceNotFoundException() {
        differenceService.getDifference(5L);
    }


    @Test(expected = BadRequestException.class)
    public void getDifferenceBadRequestException() {
        differenceService.getDifference(4L);
    }
}