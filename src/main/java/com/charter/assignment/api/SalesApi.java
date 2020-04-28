package com.charter.assignment.api;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface SalesApi {
    @RequestMapping(
            value = {"/sales"},
            produces = {"application/json"},
            consumes = {"application/json"},
            method = {RequestMethod.POST}
    )
    ResponseEntity<Integer> submitSale(@Valid @RequestBody SalesRequest salesRequest);

    @RequestMapping(
            value = {"/sales"},
            produces = {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<SalesResponse> getAllSales(@Validated @NotNull @Size(min = 10, max = 10) @RequestParam(name = "customerId") String customerId);

    @RequestMapping(
            value = {"sales/getSalesForLastThreeMonths"},
            produces= {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<SalesResponse> getSalesForLastThreeMonths(@Validated @NotNull @Size(min = 10, max = 10) @RequestParam(name = "customerId") String customerId);

    @RequestMapping(
            value = {"sales/getSalesForCurrentMonth"},
            produces= {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<SalesResponse> getSalesForCurrentMonth(@Validated @NotNull @Size(min = 10, max = 10) @RequestParam(name = "customerId") String customerId);

    @RequestMapping(
            value = {"rewards/getRewardsForCurrentMonth"},
            produces= {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<Integer> getRewardsForCurrentMonth(@Validated @NotNull @Size(min = 10, max = 10) @RequestParam(name = "customerId") String customerId);

    @RequestMapping(
            value = {"sales/getRewardsForLastThreeMonths"},
            produces= {"application/json"},
            method = {RequestMethod.GET}
    )
    ResponseEntity<Integer> getRewardsForLastThreeMonths(@Validated @NotNull @Size(min = 10, max = 10) @RequestParam(name = "customerId") String customerId);

}

