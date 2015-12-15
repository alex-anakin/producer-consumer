package ua.com.juja.tdd;

import javax.inject.Inject;

/**
 * Created by alex-anakin on 15.12.2015.
 */
public class MessageQueueConsumer implements QueueConsumer {
    @Inject
    private MessageService service;
    private final String message;

    public MessageQueueConsumer(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        service.processMessage(message);
    }
}
