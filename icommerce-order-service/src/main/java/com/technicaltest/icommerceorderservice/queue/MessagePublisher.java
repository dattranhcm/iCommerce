package com.technicaltest.icommerceorderservice.queue;

public interface MessagePublisher {
    void publish(final String message);
}
