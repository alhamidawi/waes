package com.wearewaes.demo.service;

import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;

public interface DifferenceService {

    /**
     * Insert Base64 encode binary data
     *
     * @param id    diff id
     * @param input Base64 encode binary data
     * @param side  left or right {@link Side}
     */
    void add(Long id, String input, Side side);

    /**
     * Get the difference between two side.
     *
     * @param id diff id
     * @return {@link ResponseDto} result
     */
    ResponseDto getDifference(Long id);
}
