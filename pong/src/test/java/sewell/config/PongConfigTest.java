package sewell.config;

import com.lmax.disruptor.RingBuffer;
import com.sewell.config.PongConfig;
import com.sewell.event.PongEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PongConfigTest {

    @Autowired
    private PongConfig pongConfig;

    @Test
    public void ringBufferTest() {
        RingBuffer<PongEvent> ringBuffer = pongConfig.ringBuffer();
        ringBuffer.publishEvent((event, sequence) -> {
            event.setName("name");
            event.setText("text");
        });
    }

}
