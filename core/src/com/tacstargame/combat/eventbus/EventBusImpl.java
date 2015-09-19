package com.tacstargame.combat.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventBusImpl implements EventBus {

    private static EventBus EVENTBUS = new EventBusImpl();

    private HashMap<EventBusEvent, List<EventBusListener>> eventListener = new HashMap<EventBusEvent, List<EventBusListener>>();

    private EventBusImpl() {
        if (EVENTBUS != null) {
            throw new IllegalStateException("EventBus is allready initiated!");
        }
        for (EventBusEvent event : EventBusEvent.values()) {
            eventListener.put(event, new ArrayList<EventBusListener>());
        }
    }

    public static EventBus getInstance() {
        return EVENTBUS;
    }

    @Override
    public void registerForEvent(EventBusListener eventBusListener,
            EventBusEvent busEvent) {
        eventListener.get(busEvent).add(eventBusListener);
    }

    @Override
    public void deregisterForEvent(EventBusListener eventBusListener,
            EventBusEvent busEvent) {
        eventListener.get(busEvent).remove(eventBusListener);
    }

    @Override
    public void deregisterForAllEvents(EventBusListener eventBusListener) {
        for (EventBusEvent event : EventBusEvent.values()) {
            deregisterForEvent(eventBusListener, event);
        }
    }

    @Override
    public void fireEvent(EventBusEvent busEvent, Object... args) {
        for (EventBusListener listener : eventListener.get(busEvent)) {
            listener.OnEventFired(busEvent, args);
        }
    }

    @Override
    public void registerForMultipleEvents(EventBusListener eventBusListener,
            EventBusEvent... events) {
        for (EventBusEvent event : events) {
            registerForEvent(eventBusListener, event);
        }
    }

}
