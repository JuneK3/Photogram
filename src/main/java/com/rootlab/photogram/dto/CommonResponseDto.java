package com.rootlab.photogram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseDto<T> {
    private int code; // 1: success, -1: fail
    private String message;
    private T data;
}
