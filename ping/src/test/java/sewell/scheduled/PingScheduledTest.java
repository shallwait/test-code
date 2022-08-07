package sewell.scheduled;


import com.sewell.scheduled.PingScheduled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PingScheduledTest {

    @Autowired
    private PingScheduled pingScheduled;

    @Test
    public void writeHelloTest() {
        pingScheduled.writeHello();
    }

}
