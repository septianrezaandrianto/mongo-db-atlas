package com.demo.mongodb.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralResponse<T> {

    private String responseCode;
    private String responseMessage;
    private T data;
    private List<String> errorList;

}

