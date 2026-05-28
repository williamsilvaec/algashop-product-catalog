package com.williamsilva.algashop.product.catalog.infrastructure.persistence.dataload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@ConfigurationProperties("algashop.data-load")
@Validated
public class DataLoadProperties {

    @NotNull
    private Boolean enabled;

    @NotNull
    private Boolean autoDelete;

    @Valid
    private List<DataLoadSource> sources;


    public static class DataLoadSource {
        @NotBlank
        private String location;

        @NotBlank
        private String collection;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(Boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public List<DataLoadSource> getSources() {
        return sources;
    }

    public void setSources(List<DataLoadSource> sources) {
        this.sources = sources;
    }
}
