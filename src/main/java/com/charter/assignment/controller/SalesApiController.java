package com.charter.assignment.controller;

import com.charter.assignment.api.SalesRequest;
import com.charter.assignment.api.SalesApi;
import com.charter.assignment.api.SalesResponse;
import com.charter.assignment.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Controller
public class SalesApiController implements SalesApi {

    private final SalesService salesService;

    @Override
    public ResponseEntity<Integer> submitSale(@Valid SalesRequest salesRequest) {
        return ResponseEntity.ok().body(salesService.submitSale(salesRequest));
    }

    @Override
    public ResponseEntity<SalesResponse> getAllSales(String customerId) {
        return mapResponse(salesService.getAllSales(customerId));
    }

    @Override
    public ResponseEntity<SalesResponse> getSalesForLastThreeMonths(@NotNull @Size(min = 10, max = 10) String customerId) {
        return mapResponse(salesService.getSalesForLastThreeMonths(customerId));
    }

    @Override
    public ResponseEntity<SalesResponse> getSalesForCurrentMonth(@NotNull @Size(min = 10, max = 10) String customerId) {
        return mapResponse(salesService.getSalesForCurrentMonth(customerId));
    }

    @Override
    public ResponseEntity<Integer> getRewardsForCurrentMonth(@NotNull @Size(min = 10, max = 10) String customerId) {
        return ResponseEntity.ok().body(salesService.getTotalRewardsForCurrentMonth(customerId));
    }

    @Override
    public ResponseEntity<Integer> getRewardsForLastThreeMonths(@NotNull @Size(min = 10, max = 10) String customerId) {
        return ResponseEntity.ok().body(salesService.getTotalRewardsForLastThreeMonths(customerId));
    }

    private ResponseEntity<SalesResponse> mapResponse(List<SalesRequest> sales)  {
        if (CollectionUtils.isEmpty(sales)) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find sales for the customer");
        }
        return ResponseEntity.ok(new SalesResponse(sales));
    }
}
