package com.wearewaes.demo.dto;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Map;

@Getter
@ToString(callSuper = true)
public class InputErrorDto extends BaseErrorDto {

    private final Map<String,String> messages;

    public InputErrorDto(Timestamp timestamp, int status, String error, String uri, Map<String,String> messages ){
        super(timestamp, status, error, uri);
        this.messages = messages;
    }
}
