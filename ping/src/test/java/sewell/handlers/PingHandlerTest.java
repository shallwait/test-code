package sewell.handlers;

import cn.hutool.core.io.FileUtil;
import com.sewell.handlers.PingHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PingHandlerTest {

    @Autowired
    private PingHandler pingHandler;

    @Test
    public void writeHelloTest(){
        File[] ls = FileUtil.ls("D:\\test\\");
        pingHandler.writeHello(null);
        File[] ls2 = FileUtil.ls("D:\\test\\");
        boolean b = ls2.length - ls.length == 1;
        System.out.println("------------------------");
        System.out.println(b);
    }



}
