package com.econrich.assignment.portfolio.dto;

import lombok.Getter;

public class PortfolioDto {

    @Getter
    public static class MessageDto{
        private String name;
        private String phone;
        private String email;
        private String message;
    }
}
