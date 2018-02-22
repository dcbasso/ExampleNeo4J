/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.neo4j.example.graph.persistence;

import java.util.stream.Stream;
import org.neo4j.ogm.config.AutoIndexMode;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.neo4j.driver.v1.Config;
import org.neo4j.ogm.session.SessionFactory;

/**
 *
 * @author dante.basso
 */
public class Neo4jSessionFactory {
    
    final Logger logger = LoggerFactory.getLogger(Neo4jSessionFactory.class);
    
    private final Neo4jProperties properties;    
    
    private SessionFactory factory;

    public Neo4jSessionFactory() {
        this.properties = new Neo4jProperties();
    }
    
    public Session getSession() {
        if (this.factory == null) {
            this.createInstance();
        }
        return this.factory.openSession();
    }
    
    private SessionFactory createInstance() {
         final Configuration.Builder builder = new Configuration.Builder();
        configureConnection(builder);
        configureIndex(builder);
        configureSSL(builder);
        configureCredentials(builder);
        
        // INIT FACTORY
        final Configuration configuration = builder.build();
        final String packageScan = this.properties.getPackageScan();
        final String uri = configuration.getURI();
        this.logger.debug(String.format("Search the Neo4j entities on package %s", packageScan));
        this.logger.debug(String.format("Access Neo4j on address: %s", uri));
        this.factory = new SessionFactory(configuration, packageScan);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            factory.close();
            this.logger.info(String.format("Closed a instance of %s", SessionFactory.class.getName()));
        }));
        this.logger.info(String.format("Created a new instance of %s", SessionFactory.class.getName()));
        return factory;
    }
    
    /**
     * Configure parameters of connections
     */
    void configureConnection(final Configuration.Builder builder) {
        builder.uri(this.properties.getUri());
        builder.verifyConnection(this.properties.getVerifyConnection());
        builder.connectionPoolSize(this.properties.getConnectionPoolSize());
        builder.connectionLivenessCheckTimeout(this.properties.getConectionLivenessCheckTimeout());
    }
    
    /**
     * Configure all parameters to index
     *
     * @param builder
     */
    void configureIndex(final Configuration.Builder builder) {
        final String autoIndex = this.properties.getAutoIndex();
        if (isFilledParameter(autoIndex) && AutoIndexMode.fromString(autoIndex) == null) {
            throw new RuntimeException(String.format("Auto index %s mode unknown. Please check the parameter: %s", autoIndex, Neo4jProperties.AUTO_INDEX));
        }
        builder.autoIndex(autoIndex);
        final String generatedIndexesOutputDir = this.properties.getGeneratedIndexesOutputDir();
        final String generatedIndexesOutputFilename = this.properties.getGeneratedIndexesOutputFilename();
        if (isFilledParameter(generatedIndexesOutputDir) && isFilledParameter(generatedIndexesOutputFilename)) {
            builder.generatedIndexesOutputDir(generatedIndexesOutputDir);
            builder.generatedIndexesOutputFilename(generatedIndexesOutputFilename);
            this.logger.debug(String.format("Dump the logs to %s directory", generatedIndexesOutputDir));
        } else if (isFilledParameter(autoIndex) && AutoIndexMode.fromString(autoIndex) == AutoIndexMode.DUMP) {
            throw new RuntimeException(String.format("For use %s with DUMP value, necessary is also informing the parameters: %s, %s", Neo4jProperties.AUTO_INDEX, Neo4jProperties.GENERATED_INDEXES_OUTPUT_DIR, Neo4jProperties.GENERATED_INDEXES_OUTPUT_FILENAME));
        }
    }

    /**
     * Configure all parameters to SSL
     *
     * @param builder
     */
    void configureSSL(final Configuration.Builder builder) {
        final String encryptionLevel = this.properties.getEncryptionLevel();
        if (isFilledParameter(encryptionLevel)) {
            builder.encryptionLevel(encryptionLevel);
            final Config.EncryptionLevel[] levels = Config.EncryptionLevel.values();
            final Boolean exists = Stream.of(levels).anyMatch(encryption -> encryption.name().equalsIgnoreCase(encryptionLevel));
            if (!exists) {
                throw new RuntimeException(String.format("Encryption level %s unknown. Please check the parameter: %s", encryptionLevel, Neo4jProperties.ENCRYPTION_LEVEL));
            }
            // TRUSTS
            final String trustStrategy = this.properties.getTrustStrategy();
            if (isFilledParameter(trustStrategy)) {
                this.logger.debug(String.format("TrustStrategy defined to %s", trustStrategy));
                builder.trustStrategy(trustStrategy);
                final String trustCertFile = this.properties.getTrustCertFile();
                if (isFilledParameter(trustCertFile)) {
                    builder.trustCertFile(trustCertFile);
                }
            }
        } else {
            final String trustStrategy = this.properties.getTrustStrategy();
            final String trustCertFile = this.properties.getTrustCertFile();
            if (isFilledParameter(trustStrategy) || isFilledParameter(trustCertFile)) {
                this.logger.debug(String.format("To use trust strategy or trust certification file is necessary to define encryption level for %s", Config.EncryptionLevel.REQUIRED.name()));
            }
        }
    }

    /**
     * Configure all parameters to credentials
     *
     * @param builder
     */
    void configureCredentials(final Configuration.Builder builder) {
        final String username = this.properties.getUsername();
        final String password = this.properties.getPassword();
        if (isFilledParameter(username) && isFilledParameter(password)) {
            this.logger.info("Used credentials to access Neo4j");
            builder.credentials(username, password);
        } else if (isFilledParameter(username) ^ isFilledParameter(password)) {
            throw new RuntimeException(String.format("For use credentials, necessary is inform username and password, check the parameters: %s, %s", Neo4jProperties.USERNAME, Neo4jProperties.PASSWORD));
        }
    }
    
    /**
     * Return true if parameter is not null and is not equals {@link Neo4jProperties#NONE_VALUE}
     *
     * @param parameter
     * @return
     */
    private Boolean isFilledParameter(final String parameter) {
        return null != parameter && !Neo4jProperties.NONE_VALUE.equals(parameter);
    }
    
}
