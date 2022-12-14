package com.leaderhackdemo.servicerequests.intlayer.wiring.adapters.rest.incoming.model;

public enum MessageSeverity {

    CRITICAL("CRITICAL"),
    ERROR("ERROR"),
    WARNING("WARNING");

    public final String severity;
    private MessageSeverity(String severity) {
        this.severity = severity;
    }
}
