package com.vn.fa.bus;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by leobui on 2018-08-16.
 */
public class FaBus {
    volatile static FaBus instance;

    public static FaBus getDefault() {
        FaBus localRef = instance;
        if (localRef == null) {
            synchronized (FaBus.class) {
                localRef = instance;
                if (localRef == null) {
                    localRef = instance = new FaBus();
                }
            }
        }
        return localRef;
    }

    private WeakHashMap<Object, OnEventReceived> observables = new WeakHashMap<>();

    public void registerEvent(OnEventReceived listener, Object tag) {
        if (listener != null) {
            observables.put(tag, listener);
        }
    }

    public void unRegisterEvent(Object tag) {
        if (tag != null) {
            observables.remove(tag);
        }
    }

    public void release() {
        observables.clear();
    }

    public void postEvent(Object event) {
        if (observables.size() == 0) return;
        for (Map.Entry<Object, OnEventReceived> entry : observables.entrySet()) {
            Object key = entry.getKey();
            OnEventReceived listener = entry.getValue();
            listener.onEventReceived(event);
        }
    }
}
