package com.dexafree.materialList.events;

import com.squareup.otto.Bus;

/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public final class BusProvider {
    private static final MainThreadBus BUS = new MainThreadBus(new Bus());

    public static MainThreadBus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
