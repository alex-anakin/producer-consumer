package ua.com.juja.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.concurrent.BlockingQueue;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex-anakin on 15.12.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QueueProcessingTestConfiguration.class)
public class QueueProcessingUnitTest {

    @Inject
    private QueueDispatcher queueDispatcher;

    @Resource
    private BlockingQueue<String> queue;

    private StringMessageService messageService;

    @Test
    public void shouldProcessOneMessage() throws Exception {
        String message = "1";
        queue.add(message);

        assertThat(queue.size(), is(0));
        verify(messageService).processMessage(message);
    }
}
