package bg.softuni.webfluxclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties()
@ConfigurationProperties("softuni.webflux.client")
public class ReactiveClientConfig {
    private String schema;
    private String host;
    private String port;

    public String getSchema() {
        return schema;
    }

    public ReactiveClientConfig setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ReactiveClientConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ReactiveClientConfig setPort(String port) {
        this.port = port;
        return this;
    }
}
