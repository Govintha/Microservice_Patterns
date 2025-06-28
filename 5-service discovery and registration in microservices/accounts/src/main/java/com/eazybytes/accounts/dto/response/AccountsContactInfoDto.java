package com.eazybytes.accounts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "accounts")
public class AccountsContactInfoDto{

        String message;
        Map<String, String> contactDetails;
        List<String> onCallSupport;
}