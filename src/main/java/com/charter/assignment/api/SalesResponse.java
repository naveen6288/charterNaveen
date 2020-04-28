package com.charter.assignment.api;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SalesResponse {
    private List<SalesRequest> sales;
}
