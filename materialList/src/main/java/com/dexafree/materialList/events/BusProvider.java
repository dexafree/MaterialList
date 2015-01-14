package com.dexafree.materialList.events;

import com.dexafree.materialList.model.Card;
import com.squareup.otto.Bus;

/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public final class BusProvider {
    private static final MainThreadBus BUS = new MainThreadBus(new Bus());

    private BusProvider() {
        // No instances.
    }

    public static void dataSetChanged() {
        BUS.post(new DataSetChangedEvent());
    }

    public static void dismiss(Card card) {
        BUS.post(new DismissEvent(card));
    }

    public static void register(Object object) {
        BUS.register(object);
    }

    public static void unregister(Object object) {
        BUS.unregister(object);
    }
}
