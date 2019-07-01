package com.wearewaes.demo.service.impl;

import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.BadRequestException;
import com.wearewaes.demo.exception.NotFoundException;
import com.wearewaes.demo.model.Data;
import com.wearewaes.demo.service.DifferenceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DifferenceServiceImpl implements DifferenceService {

    private final Map<Long, Data> storage;

    public DifferenceServiceImpl(Map<Long, Data> storage) {
        this.storage = storage;
    }

    @Override
    public void add(Long id, String input, Side side) {
        Data data = getData(id);
        byte[] decodedBytes = decodeString(input);

        if (Side.LEFT.equals(side)) {
            data.setLeft(decodedBytes);
        } else {
            data.setRight(decodedBytes);
        }

        if (!storage.containsKey(id)) {
            storage.put(id, data);
        }
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
        Diff diff = Diff.instance(left, right);
        return createResponse(diff);
    }

    private ResponseDto createResponse(Diff diff) {
        ResponseDto result = new ResponseDto();
        if (diff.isEqual()) {
            result.setIsContentEqual(true);
            return result;
        }

        if (!diff.isSizeEqual()) {
            result.setIsSizeEqual(false);
            return result;
        }

        result.setOffset(diff.getOffset());
        result.setLength(diff.getDiffSize());
        return result;
    }

    private boolean isDataValid(Data data) {
        return data != null && data.getLeft() != null && data.getLeft().length != 0
                && data.getRight() != null && data.getRight().length != 0;

    }



}
