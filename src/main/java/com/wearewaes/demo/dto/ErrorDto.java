package com.wearewaes.demo.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString(callSuper = true)
public class ErrorDto extends BaseErrorDto {

    private String message;

    public ErrorDto(Timestamp timestamp, int status, String error, String uri, String message ){
        super(timestamp, status, error, uri);
        this.message = message;
    }
}
