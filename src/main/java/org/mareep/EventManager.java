package org.mareep;

import org.mareep.annotations.EventHandler;
import org.mareep.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<Class<? extends Event>, List<Method>> listeners = new HashMap<>();

    public void registerListener(EventListener listener) {
        Class<?> clazz = listener.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && Event.class.isAssignableFrom(parameterTypes[0])) {
                    Class<? extends Event> eventType = parameterTypes[0].asSubclass(Event.class);
                    listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(method);
                }
            }
        }
    }

    public void fireEvent(Event event) {
        List<Method> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (Method method : eventListeners) {
                try {
                    method.invoke(method.getDeclaringClass().newInstance(), event);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
