import org.junit.Test;
import junit.framework.TestCase;

public class GmailSenderTest extends TestCase {
	private GmailSender gmailSender;
	
	protected void setUp() throws Exception {
		super.setUp();
		gmailSender = new GmailSender();

	}
	
	protected void tearDown() throws Exception {
    		super.tearDown();
	}

	@Test
	public void testSendMessage() {
		assertTrue(this.gmailSender.sendMessage("c.nadjim@gmail.com", "test", "test"));
	}

}
