package org.example.osm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("osm.parse")
@Data
public class OSMFillerConfiguration {
    private int nodes = 100;
}
