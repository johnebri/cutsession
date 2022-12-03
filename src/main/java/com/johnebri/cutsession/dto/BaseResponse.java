package com.johnebri.cutsession.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John on 11/29/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    private String message = "Successful";
    private List<String> errors = new ArrayList<>();
    private T data = null;
}
