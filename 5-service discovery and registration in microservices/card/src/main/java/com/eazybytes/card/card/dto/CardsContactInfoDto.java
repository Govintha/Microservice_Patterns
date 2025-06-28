package com.eazybytes.card.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "cards")
public class CardsContactInfoDto{
    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;
}