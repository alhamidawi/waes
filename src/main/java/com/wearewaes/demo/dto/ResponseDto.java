package com.wearewaes.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private Boolean isContentEqual;
    private Boolean isSizeEqual;
    private Integer offset;
    private Integer length;
}
