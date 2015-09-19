package com.tacstargame.combat.eventbus;

public interface EventBusListener {
	
	/**
	 * Is invoked if a event that the EventBusListener is registered to is fired.
	 * 
	 * @param busEvent The event that has been fired.
	 * @param args Arguments of the Event.
	 */
	void OnEventFired(EventBusEvent busEvent, Object ... args);
}
