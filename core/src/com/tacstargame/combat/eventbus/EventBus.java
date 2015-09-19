package com.tacstargame.combat.eventbus;

public interface EventBus {
	
	/**
	 * Registers a EventBusListener to a specific EventBusEvent.
	 * 
	 * @param eventBusListener The EventBusListener that wants to be registered to an EventBusEvent.
	 * @param busEvent The EventBusEvent the Listener wants to be registered to.
	 */
	void registerForEvent(EventBusListener eventBusListener, EventBusEvent busEvent);
	
	/**
	 * Registers a EventBusListener to multiple EventBusEvent's.
	 * 
	 * @param eventBusListener The EventBusListener that wants to be registered to multiple EventBusEvent's.
	 * @param events The EventBusEvents the EventBusListener wants to be registered to.
	 */
	void registerForMultipleEvents(EventBusListener eventBusListener, EventBusEvent ... events);
	
	/**
	 * Unregisters a EventBusListener from a specific EventBusEvent.
	 * 
	 * @param eventBusListener The EventBusListener that wants to be unregistered from an EventBusEvent.
	 * @param busEvent The EventBusEvent the Listener wants to be unregistered from.
	 */
	void deregisterForEvent(EventBusListener eventBusListener, EventBusEvent busEvent);
	
	/**
	 * Unregisters a EventBusListener from all EventBussEvents.
	 * 
	 * @param eventBusListener The EventBusListener that wants to be unregistered.
	 */
	void deregisterForAllEvents(EventBusListener eventBusListener);
	
	/**
	 * Fires a EventBusEvent to all EventBusListener that are registered to it.
	 * 
	 * @param busEvent EventBusEvent that will be fired.
	 * @param args Arguments of the event.
	 */
	void fireEvent(EventBusEvent busEvent, Object ... args);
}
