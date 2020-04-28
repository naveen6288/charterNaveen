package com.charter.assignment.dao;

import com.charter.assignment.api.SalesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void submitSale(SalesRequest salesRequest) {
        namedParameterJdbcTemplate.update("INSERT INTO SALE (AMOUNT, SALE_DT, CUST_ID) VALUES (:amount, :saleDate, :customerId)",
                new MapSqlParameterSource().addValue("amount", salesRequest.getSale())
                        .addValue("saleDate", salesRequest.getDateTime())
                        .addValue("customerId", salesRequest.getCustomerId()));
    }

    public List<SalesRequest> getAllSales(String customerId) {
        return namedParameterJdbcTemplate.query("SELECT * FROM SALE WHERE CUST_ID = :customerId", new MapSqlParameterSource().addValue("customerId", customerId),
                new RowMapper<SalesRequest>() {
                    @Override
                    public SalesRequest mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new SalesRequest(resultSet.getInt("AMOUNT"), resultSet.getDate("SALE_DT"), resultSet.getString("CUST_ID"));
                    }
                });
    }


    public List<SalesRequest> getSalesInPeriod(LocalDate startDate, LocalDate endDate, String customerId) {
        return namedParameterJdbcTemplate.query("SELECT * FROM SALE WHERE SALE_DT > :startDate AND SALE_DT < :endDate AND CUST_ID = :customerId", new MapSqlParameterSource().addValue("startDate", startDate)
                        .addValue("endDate", endDate)
                        .addValue("customerId", customerId),
                new RowMapper<SalesRequest>() {
                    @Override
                    public SalesRequest mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new SalesRequest(resultSet.getInt("AMOUNT"), resultSet.getDate("SALE_DT"), resultSet.getString("CUST_ID"));
                    }
                });
    }
}
