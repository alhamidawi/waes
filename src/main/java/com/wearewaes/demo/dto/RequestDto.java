package com.wearewaes.demo.dto;

import com.wearewaes.demo.validation.Base64Constraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class RequestDto {

    @Base64Constraint
    @NotEmpty(message = "Base64 encoded binary data cannot be empty or null.")
    private String data;

}
