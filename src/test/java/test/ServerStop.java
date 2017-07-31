package test;

import org.junit.Test;

/**
 *
 * @author pthomas3
 */
public class ServerStop {

	@Test
	public void stopServer() {
		MonitorThread.stop(8081);
	}
	
}