package com.wearewaes.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Difference insight response")
public class ResponseDto {
    @ApiModelProperty(notes = "Flag if the content of two arrays are equal")
    private Boolean isContentEqual;
    @ApiModelProperty(notes = "Flag if the size of two arrays are equal")
    private Boolean isSizeEqual;
    @ApiModelProperty(notes = "Offset")
    private Integer offset;
    @ApiModelProperty(notes = "Diff length")
    private Integer length;
}
