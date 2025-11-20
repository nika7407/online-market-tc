package com.solvd.onlinemarkettc.domain.payment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHolder {

    private static final Map<EventType, List<Object>> listeners = new HashMap<>();

    public EventHolder() {
        Arrays.stream(EventType.values())
                .forEach(type -> listeners.put(type, new ArrayList<>()));
    }

    public void subscribe(EventType type, Object listener) {
        listeners.get(type).add(listener);
    }

    public void unsubscribe(EventType type, Object listener) {
        listeners.get(type).remove(listener);
    }

    public void notify(EventType type, String message) {
        List<Object> list = listeners.get(type);
        for (Object listener : list) {
            try {
                Method method = listener.getClass()
                        .getMethod("onEvent", EventType.class, String.class);
                method.invoke(listener, type, message);
            } catch (Exception ignored) {
            }
        }
    }
}
