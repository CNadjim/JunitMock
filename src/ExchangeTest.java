import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;
import junit.framework.TestCase;

public class ExchangeTest extends TestCase {
	
	private Exchange exchange;
	private User userMock;
	private Product productMock;
	private DbConnect dbconnectMock;
	private GmailSender gmailSenderMock;
	
	protected void setUp() throws Exception {
		super.setUp();
		userMock = mock(User.class);
		productMock = mock(Product.class);
		Date from = new Date(new Date().getTime() + 1000 * 60 * 60 * 24); // initialise la date de départ au jour suivant
		Date to = new Date(from.getTime() + 1000 * 60 * 60 * 24); // 1 jour après la date from

		dbconnectMock = mock(DbConnect.class);
		gmailSenderMock = mock(GmailSender.class);
		
		exchange = new Exchange(userMock,productMock,from,to,dbconnectMock,gmailSenderMock);
		
	}
	
	protected void tearDown() throws Exception {
    		super.tearDown();
    		userMock = null;
    		productMock = null;
    		exchange = null;
	}

	@Test
	public void testSave() {
		when(productMock.isValid()).thenReturn(true);
		when(userMock.isValid()).thenReturn(true);
		when(dbconnectMock.saveUser(exchange.getReceiver())).thenReturn(true);
		when(dbconnectMock.saveProduct(productMock)).thenReturn(true);
		when(dbconnectMock.saveExchange(exchange)).thenReturn(true);
		when(gmailSenderMock.sendMessage("", "", "")).thenReturn(true);
		assertTrue(this.exchange.save());
	}

}
