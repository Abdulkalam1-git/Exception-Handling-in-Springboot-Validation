package com.telusko.demo6.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dataResponse<T> {
    private T values;
    private String message;
    private String status;
}
