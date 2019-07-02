package com.wearewaes.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@ToString
class BaseErrorDto {
    private final Timestamp timestamp;
    private final int status;
    private final String error;
    private final String uri;
}
