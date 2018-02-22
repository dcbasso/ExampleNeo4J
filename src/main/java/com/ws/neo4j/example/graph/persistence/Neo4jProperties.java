package com.ws.neo4j.example.graph.persistence;


public class Neo4jProperties {

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    public static final String AUTO_INDEX = "neo4j.indexes.auto";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    public static final String GENERATED_INDEXES_OUTPUT_DIR = "neo4j.generated.indexes.output.dir";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    public static final String GENERATED_INDEXES_OUTPUT_FILENAME = "neo4j.generated.indexes.output.filename";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:connection-test">Configuration liveness</a>
     */
    public static final String CONNECTION_LIVENESS_CHECK_TIMEOUT = "neo4j.connection.liveness.check.timeout";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:bolt">Configuration Pool Size</a>
     */
    public static final String CONNECTION_POOL_SIZE = "neo4j.connection.pool.size";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:credentials">Configuration credentials</a>
     */
    public static final String USERNAME = "neo4j.username";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:credentials">Configuration credentials</a>
     */
    public static final String PASSWORD = "neo4j.password";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    public static final String ENCRYPTION_LEVEL = "neo4j.encryption.level";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    public static final String TRUST_CERT_FILE = "neo4j.trust.cert.file";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    public static final String TRUST_STRATEGY = "neo4j.trust.strategy";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver">Configuration Driver</a>
     */
    public static final String URI = "neo4j.uri";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual//current/reference/#_eager_connection_verification">Eeager connection</a>
     */
    public static final String VERIFY_CONNECTION = "neo4j.verify.connection";

    /**
     * Package to find graph entities
     */
    public static final String PACKAGE_SCAN = "neo4j.package.scan.entities";

    /**
     * Represent value not informed on parameter
     */
    public static final String NONE_VALUE = "NULL_VALUE";

    /**
     * Define the size of package in batch
     */
    public static final String BATCH_SIZE = "neo4j.batch.size";

    /**
     * Define the limit of threads in batch
     */
    public static final String BATCH_THREAD_LIMIT = "neo4j.batch.thread.limit";

    /**
     * Configure log level of Neo4j OGM
     */
    public static final String NEO4J_OGM_LOG_LEVEL = "neo4j.ogm.log.level";

    /**
     * Package name of Neo4j OGM
     */
    public static final String NEO4J_OGM_PACKAGE_NAME = "org.neo4j.ogm";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    private String autoIndex = "NONE";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    private String generatedIndexesOutputDir;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:indexing:creation" />Index Creation</a>
     */
    private String generatedIndexesOutputFilename;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:connection-test">Configuration liveness</a>
     */
    private Integer conectionLivenessCheckTimeout = -1;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:bolt">Configuration Pool Size</a>
     */
    private Integer connectionPoolSize = 150;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:credentials">Configuration credentials</a>
     */
    private String username = "neo4j";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:credentials">Configuration credentials</a>
     */
    private String password = "123mudar";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    private String encryptionLevel = "REQUIRED";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    private String trustCertFile;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver:tsl">Configuration Encryption Level</a>
     */
    private String trustStrategy;

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#reference:configuration:driver">Configuration Driver</a>
     */
    private String uri = "bolt://neo4j:123mudar@localhost";

    /**
     * Neo4j OGM 3.0.0
     *
     * @see <a href="https://neo4j.com/docs/ogm-manual/current/reference/#_eager_connection_verification">Eeager connection</a>
     */
    private Boolean verifyConnection = false;

    /**
     * Package to find graph entities
     */
    private String packageScan = "com.ws.neo4j.example";

    /**
     * Define the size of package in batch
     */
    private Integer batchSize = 500;

    /**
     * Define the limit of threads in batch. In case of increase default value, can cause deadlock
     */
    private Integer batchThreadLimit = 1;

    /**
     * Configure log level of Neo4j OGM
     */
    private String neo4jOgmLogLevel = "ERROR";


    /**
     * Get of {@link #autoIndex} property
     *
     * @return
     */
    public String getAutoIndex() {
        return this.autoIndex;
    }

    /**
     * Get of {@link #conectionLivenessCheckTimeout} property
     *
     * @return
     */
    public Integer getConectionLivenessCheckTimeout() {
        return this.conectionLivenessCheckTimeout;
    }

    /**
     * Get of {@link #connectionPoolSize} property
     *
     * @return
     */
    public Integer getConnectionPoolSize() {
        return this.connectionPoolSize;
    }

    /**
     * Get of {@link #username} property
     *
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get of {@link #password} property
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get of {@link #encryptionLevel} property
     *
     * @return
     */
    public String getEncryptionLevel() {
        return this.encryptionLevel;
    }

    /**
     * Get of {@link #generatedIndexesOutputDir} property
     *
     * @return
     */
    public String getGeneratedIndexesOutputDir() {
        return this.generatedIndexesOutputDir;
    }

    /**
     * Get of {@link #generatedIndexesOutputFilename} property
     *
     * @return
     */
    public String getGeneratedIndexesOutputFilename() {
        return this.generatedIndexesOutputFilename;
    }

    /**
     * Get of {@link #trustStrategy} property
     *
     * @return
     */
    public String getTrustCertFile() {
        return this.trustCertFile;
    }

    /**
     * Get of {@link #trustStrategy} property
     *
     * @return
     */
    public String getTrustStrategy() {
        return this.trustStrategy;
    }

    /**
     * Get of {@link #uri} property
     *
     * @return
     */
    public String getUri() {
        return this.uri;
    }

    /**
     * Get of {@link #verifyConnection} property
     *
     * @return
     */
    public Boolean getVerifyConnection() {
        return this.verifyConnection;
    }

    /**
     * Get of {@link #packageScan} property
     *
     * @return
     */
    public String getPackageScan() {
        return this.packageScan;
    }

    /**
     * Get of {@link #batchSize} property
     *
     * @return
     */
    public Integer getBatchSize() {
        return this.batchSize;
    }

    /**
     * Get of {@link #batchThreadLimit} property
     *
     * @return
     */
    public Integer getBatchThreadLimit() {
        return this.batchThreadLimit;
    }

    /**
     * Get of {@link #neo4jOgmLogLevel} property
     *
     * @return String
     */
    public String getNeo4jOgmLogLevel() {
        return this.neo4jOgmLogLevel;
    }
}