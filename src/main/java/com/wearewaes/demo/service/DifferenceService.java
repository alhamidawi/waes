package com.wearewaes.demo.service;

import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;

/**
 * Service which contain main login for this assigment.
 *
 * @author Hani Al-Hamidawi
 */
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
     *
     * @return {@link ResponseDto} result
     *
     * @throws {@link com.wearewaes.demo.exception.NotFoundException} if there is no data with given id
     * @throws {@link com.wearewaes.demo.exception.BadRequestException} if there is data but without left or right side
     */
    ResponseDto getDifference(Long id);
}
