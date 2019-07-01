package com.wearewaes.demo.controller;

import com.wearewaes.demo.dto.RequestDto;
import com.wearewaes.demo.dto.ResponseDto;
import com.wearewaes.demo.enumeration.Side;
import com.wearewaes.demo.exception.InvalidInputExceptionExtractor;
import com.wearewaes.demo.service.DifferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/diff")
public class DifferenceController {

    private final DifferenceService differenceService;

    DifferenceController(DifferenceService differenceService){
        this.differenceService = differenceService;
    }


    @PostMapping("/{id}/left")
    public ResponseEntity addLeft(@PathVariable("id") Long id, @Valid  @RequestBody RequestDto requestBody, BindingResult bindingResult){
        InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
        differenceService.add(id, requestBody.getData(), Side.LEFT);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/right")
    public ResponseEntity addRight(@PathVariable("id") Long id, @Valid  @RequestBody RequestDto requestBody, BindingResult bindingResult){
        InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
        differenceService.add(id, requestBody.getData(), Side.RIGHT);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getDifference(@PathVariable("id") Long id){
        ResponseDto result = differenceService.getDifference(id);
        return ResponseEntity.ok(result);
    }
}
