package com.charter.assignment.service;

import com.charter.assignment.api.SalesRequest;
import com.charter.assignment.dao.SalesDao;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesDao salesDao;

    public Integer submitSale(SalesRequest salesRequest) {
        salesDao.submitSale(salesRequest);
        return getRewards((int) Math.ceil(salesRequest.getSale()));
    }

    public List<SalesRequest> getAllSales(String customerId) {
        return salesDao.getAllSales(customerId);
    }

    public List<SalesRequest> getSalesForLastThreeMonths(String customerId) {
        return salesDao.getSalesInPeriod(LocalDate.of(LocalDateTime.now().minusMonths(3).getYear(), LocalDateTime.now().minusMonths(3).getMonth(), 1),
                LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1), customerId);
    }

    public int getTotalRewardsForCurrentMonth(String customerId) {
        List<SalesRequest> sales =  salesDao.getSalesInPeriod(LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1),
                LocalDate.of(LocalDateTime.now().plusMonths(1).getYear(), LocalDateTime.now().plusMonths(1).getMonth(), 1), customerId);
        return cummulateRewards(sales);
    }

    public List<SalesRequest> getSalesForCurrentMonth(String customerId) {
        return salesDao.getSalesInPeriod(LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1),
                LocalDate.of(LocalDateTime.now().plusMonths(1).getYear(), LocalDateTime.now().plusMonths(1).getMonth(), 1), customerId);
    }


    private int cummulateRewards(List<SalesRequest> sales) {
        return sales.stream().mapToInt(s -> getRewards(s.getSale())).sum();
    }

    public int getTotalRewardsForLastThreeMonths(String customerId) {
        return cummulateRewards(getSalesForLastThreeMonths(customerId));
    }


    private int getRewards(int sale) {
        int rewards = 0;
        if (sale > 50 && sale < 100) {
            rewards = sale - 50;
        } else if (sale > 100) {
            rewards = 50 + (sale - 100) * 2;
        }
        return rewards;
    }

}
