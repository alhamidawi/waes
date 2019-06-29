package com.wearewaes.demo.service;

import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;

public interface DifferenceService {

    void add(Long id, RequestDto requestDto, Side side);

    ResponseDto getDifference(Long id);
}
