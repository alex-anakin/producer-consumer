package ua.com.juja.tdd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by alex-anakin on 15.12.2015.
 */
@Configuration
public class QueueProcessingTestConfiguration {

    @Bean(name = "queue")
    public BlockingQueue<String> getQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public QueueDispatcher getQueueDispatcher() {
        return new MessageQueueDispatcher();
    }

    @Bean
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(50);
        return taskExecutor;
    }

    @Bean
    public MessageService getMessageService() {
        return new StringMessageService();
    }
}
