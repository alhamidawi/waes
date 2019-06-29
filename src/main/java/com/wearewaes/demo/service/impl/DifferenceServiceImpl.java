package com.wearewaes.demo.service.impl;

import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.BadRequestException;
import com.wearewaes.demo.exception.NotFoundException;
import com.wearewaes.demo.model.Data;
import com.wearewaes.demo.service.DifferenceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DifferenceServiceImpl implements DifferenceService {

    private final Map<Long, Data> storage = new HashMap<>();

    @Override
    public void add(Long id, RequestDto requestDto, Side side) {
        String binary64Encoded = requestDto.getData();
        Data data = getData(id);
        byte[] decodedBytes = decodeString(binary64Encoded);
        insertInto(data, decodedBytes, side);
        if (!storage.containsKey(id)) {
            storage.put(id, data);
        }
    }

    private void insertInto(Data data, byte[] input, Side side) {
        if (side.equals(Side.LEFT)) {
            data.setLeft(input);
        }

        data.setRight(input);
    }

    private byte[] decodeString(String input) {
        return Base64.decodeBase64(input);
    }

    private Data getData(Long id) {
        Data result = storage.get(id);
        if (result == null) {
            result = new Data();
        }
        return result;
    }


    @Override
    public ResponseDto getDifference(Long id) {
        if (!storage.containsKey(id)) {
            throw new NotFoundException("No binary data found for id " + id);
        }

        Data data = storage.get(id);

        if (!isDataValid(data)) {
            throw new BadRequestException("Data is missing left and/or right side");
        }

        byte[] left = data.getLeft();
        byte[] right = data.getRight();
        return createResponse(left, right);
    }

    private ResponseDto createResponse(byte[] left, byte[] right) {
        ResponseDto result = new ResponseDto();
        boolean isContentEqual = Arrays.equals(left, right);
        if (isContentEqual) {
            result.setIsContentEqual(true);
            return result;
        }

        boolean isSizeEqual = left.length == right.length;
        if (isSizeEqual) {
            result.setIsSizeEqual(true);
            return result;
        }


        Helper tmp = getOffsetAndSize(left, right);
        result.setOffset(tmp.getOffset());
        result.setLength(tmp.getDiffSize());
        return result;
    }

    private boolean isDataValid(Data data) {
        return data != null && data.getLeft() != null && data.getLeft().length != 0
                && data.getRight() != null && data.getRight().length != 0;

    }

    private Helper getOffsetAndSize(byte[] left, byte[] right) {
        // Check to make sure arrays have same length
        if (left.length != right.length) {
            throw new IllegalArgumentException("Left and right array don't have the same size");
        }
        int offset = 0;
        int diffSize = 0;
        boolean offsetFound = false;

        //iterate over both arrays to find first byte which is different
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]) {
                diffSize++;
                if (!offsetFound) {
                    offset = i;
                    offsetFound = true;
                }
            }
        }

        return new Helper(offset, diffSize);
    }

    @AllArgsConstructor
    @Getter
    private class Helper {
        private int offset;
        private int diffSize;
    }
}
