package com.anonimo.api.model;

public class EventParticipants {

	private final long eventId;
	private final int participants;
	
	public EventParticipants(long eventId, int numberOfParticipants) {
		this.eventId = eventId;
		this.participants = numberOfParticipants;
	}

	public long getEventId() {
		return eventId;
	}

	public int getParticipants() {
		return participants;
	}
}
