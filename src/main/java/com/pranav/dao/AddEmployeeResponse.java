package com.pranav.dao;

import java.util.Map;

public record AddEmployeeResponse(
        String message,
        Map<Integer, String> results
) {}
