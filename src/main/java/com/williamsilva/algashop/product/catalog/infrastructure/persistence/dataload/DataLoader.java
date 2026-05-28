package com.williamsilva.algashop.product.catalog.infrastructure.persistence.dataload;

import com.williamsilva.algashop.product.catalog.infrastructure.utility.AlgaShopResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final MongoOperations mongoOperations;
    private final DataLoadProperties properties;

    public DataLoader(MongoOperations mongoOperations, DataLoadProperties properties) {
        this.mongoOperations = mongoOperations;
        this.properties = properties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!properties.getEnabled()) {
            return;
        }

        log.info("Data load started");
        if (properties.getSources() == null) {
            log.info("No sources configured");
            return;
        }

        properties.getSources().forEach(this::importJsonFileToCollection);
    }

    private void importJsonFileToCollection(DataLoadProperties.DataLoadSource source) {
        String rawJson = AlgaShopResourceUtils.readContent(source.getLocation());
        if (StringUtils.isBlank(rawJson)) {
            log.warn("Resource {} is empty or not found", source.getLocation());
            return;
        }

        List<Document> docs = parseJsonToDocuments(rawJson);
        int inserted = insertInto(docs, source.getCollection());
        log.info("{} - Imports: {}/{}", source.getLocation(), inserted, docs.size());
    }

    private List<Document> parseJsonToDocuments(String rawJson) {
        try {
            BsonArray array = BsonArray.parse(rawJson);
            return array.stream().map(Object::toString).map(Document::parse).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to parse JSON resource {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private int insertInto(List<Document> mongoDocs, String collectionName) {
        if (mongoDocs == null || mongoDocs.isEmpty()) {
            return 0;
        }

        try {
            if (Boolean.TRUE.equals(properties.getAutoDelete())) {
                mongoOperations.getCollection(collectionName).deleteMany(new BsonDocument());
            }
            return mongoOperations.insert(mongoDocs, collectionName).size();
        } catch (Exception e) {
            log.error("Error inserting documents into {}: {}", collectionName, e.getMessage(), e);
        }

        return 0;
    }
}
