package com.kitabisa.app.notification;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.spring.security.SpringSecurityUserProvider;

public class TogglzConfiguration implements TogglzConfig {
    public Class<? extends Feature> getFeatureClass() {
        return VendorOptions.class;
    }

    public StateRepository getStateRepository() {
        return new InMemoryStateRepository ();
    }

    public UserProvider getUserProvider() {
        return new SpringSecurityUserProvider ("admin");
    }
}