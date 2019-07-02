package com.wearewaes.demo.dto;

import com.wearewaes.demo.validation.Base64Constraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Request input for adding base64 binary data")
public class RequestDto {

    @Base64Constraint
    @NotEmpty(message = "Base64 encoded binary data cannot be empty or null.")
    @ApiModelProperty("Base64 encoded binary data")
    private String data;


}
