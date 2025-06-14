package com.eazybytes.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "loans")
public class LoansContactInfoDto{

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}