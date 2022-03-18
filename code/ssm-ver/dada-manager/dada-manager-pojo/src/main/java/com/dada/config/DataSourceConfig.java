package com.dada.config;

public class DataSourceConfig {

    private String configType;

    private String username;

    private String password;

    private String driver;

    private String url;

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DataSourceConfig [configType=" + configType + ", username=" + username + ", password=" + password
                + ", driver=" + driver + ", url=" + url + "]";
    }
    
}
