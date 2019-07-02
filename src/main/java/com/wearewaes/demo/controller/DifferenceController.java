package com.wearewaes.demo.controller;

import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.InvalidInputExceptionExtractor;
import com.wearewaes.demo.service.DifferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/diff")
@Api(tags = "Diff")
public class DifferenceController {

    private final DifferenceService differenceService;

    DifferenceController(DifferenceService differenceService){
        this.differenceService = differenceService;
    }


    @ApiOperation(value = "Endpoint that accepts JSON base64 encoded binary data for left side")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Value is null, empty or not Base64 encoded")
    })
    @PostMapping("/{id}/left")
    public ResponseEntity<Void> addLeft(@PathVariable("id") Long id, @Valid @RequestBody RequestDto requestBody, BindingResult bindingResult) {
        InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
        differenceService.add(id, requestBody.getData(), Side.LEFT);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint that accepts JSON base64 encoded binary data for right side")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Value is null, empty or not Base64 encoded")
    })
    @PostMapping("/{id}/right")
    public ResponseEntity<Void> addRight(@PathVariable("id") Long id, @Valid @RequestBody RequestDto requestBody, BindingResult bindingResult) {
        InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
        differenceService.add(id, requestBody.getData(), Side.RIGHT);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Endpoint to get insight regarding difference")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Left or right side doesn't contain any data"),
            @ApiResponse(code = 404, message = "There is no data with given id"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getDifference(@PathVariable("id") Long id) {
        ResponseDto result = differenceService.getDifference(id);
        return ResponseEntity.ok(result);
    }
}
