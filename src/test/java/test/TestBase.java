package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.intuit.karate.junit4.Karate;

@RunWith(Karate.class)
public class TestBase {

private static ServerStart server;
    
    @BeforeClass
    public static void beforeClass() throws Exception {
        server = new ServerStart();
        server.start(new String[]{"--server.port=0"}, false);
        System.setProperty("demo.server.port", server.getPort() + "");
    }
    
    @AfterClass
    public static void afterClass() {
        server.stop();
}
}
