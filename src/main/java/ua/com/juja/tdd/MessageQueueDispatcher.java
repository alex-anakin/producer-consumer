package ua.com.juja.tdd;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.concurrent.BlockingQueue;

/**
 * Created by alex-anakin on 15.12.2015.
 */
public class MessageQueueDispatcher implements QueueDispatcher {

    @Resource
    private BlockingQueue<String> queue;
    @Inject
    private ThreadPoolTaskExecutor taskExecutor;
    @Inject
    ApplicationContext context;

    @PostConstruct
    @Override
    public void startWork() {
        taskExecutor.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                taskExecutor.execute(createTask(queue.take()));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private QueueConsumer createTask(String message) {
        return context.getBean(MessageQueueConsumer.class, message);
    }


}
