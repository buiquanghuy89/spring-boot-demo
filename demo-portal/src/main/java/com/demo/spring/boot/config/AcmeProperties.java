package com.demo.spring.boot.config;

import com.demo.spring.boot.bo.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bqhuy on 3/19/2018.
 */
@Component
@ConfigurationProperties("acme")
public class AcmeProperties {
    private boolean enabled;
    private InetAddress remoteAddress;
    private Security security = new Security();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public InetAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Security getSecurity() {
        return security;
    }

    public static class Security {
        private List<User> users;
        private List<String> roles = new ArrayList<>();

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}
