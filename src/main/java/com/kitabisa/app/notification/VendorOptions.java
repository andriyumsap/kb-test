package com.kitabisa.app.notification;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum VendorOptions implements Feature {
    @Label("Vendor Notification 1")
    VENDOR_ONE,

    @Label("Vendor Notification 2")
    VENDOR_TWO;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
