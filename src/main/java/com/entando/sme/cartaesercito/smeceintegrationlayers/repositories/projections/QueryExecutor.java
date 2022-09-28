package com.entando.sme.cartaesercito.smeceintegrationlayers.repositories.projections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueryExecutor {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
