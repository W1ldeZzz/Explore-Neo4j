package com.tgtech.explore.connector;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConnector implements InitializingBean {
    @Value("${neo4j.url}")
    private String url;
    @Value("${neo4j.user}")
    private String user;
    @Value("${neo4j.password}")
    private String password;

    private Driver driver;

    @Override
    public void afterPropertiesSet() throws Exception {
        driver = GraphDatabase.driver(url, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public Driver getDriver() {
        return driver;
    }
}
