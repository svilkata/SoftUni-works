package bg.softuni.cloudinary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloudinary") //running with annotating processor
public class CloudinaryConfig {
    private String cloudName;
    private String apiKey;
    private String apiSecret;

    /**
     * Sets the Cloud name associated with the cloudinary account.
     * @param cloudName
     * @return this
     */
    public String getCloudName() {
        return cloudName;
    }

    public CloudinaryConfig setCloudName(String cloudName) {
        this.cloudName = cloudName;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public CloudinaryConfig setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public CloudinaryConfig setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }
}
