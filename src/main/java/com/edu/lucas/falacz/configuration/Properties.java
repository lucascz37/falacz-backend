package com.edu.lucas.falacz.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("my-properties")
@Data
public class Properties {
    private String secret;

}
