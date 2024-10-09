package com.abhaempire.complifybook.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("providermap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProviderConfig {
    private SmsProviderConfig providerConfig;
}
