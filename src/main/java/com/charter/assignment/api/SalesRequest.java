package com.charter.assignment.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
public class SalesRequest {
    @NotNull
    private Integer sale;
    @NotNull
    private Date dateTime;
    @NotNull
    @Size(min = 10, max = 10)
    private String customerId;
}
