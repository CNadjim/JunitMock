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
	private Date from;
	private Date to;
	
	protected void setUp() throws Exception {
		super.setUp();
		userMock = mock(User.class);
		productMock = mock(Product.class);
		from = new Date(new Date().getTime() + 1000 * 60 * 60 * 24); // initialise la date de départ au jour suivant
		to = new Date(from.getTime() + 1000 * 60 * 60 * 24); // 1 jour après la date from
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
		when(dbconnectMock.saveUser(exchange.getProduct().getOwner())).thenReturn(true);
		when(dbconnectMock.saveProduct(productMock)).thenReturn(true);
		when(dbconnectMock.saveExchange(exchange)).thenReturn(true);
		when(gmailSenderMock.sendMessage(exchange.getReceiver().getEmail(), "alert", "VOUS ETES MINEUR")).thenReturn(true);
		assertTrue(this.exchange.save());
	}
	
	@Test
	public void testSaveNotValidDateNotValid() {
		when(productMock.isValid()).thenReturn(true);
		when(userMock.isValid()).thenReturn(true);
		exchange.setDateFrom(new Date());
		exchange.setDateTo(new Date());
		assertFalse(this.exchange.save());	
	}
	

	@Test
	public void testSaveNotValidProductNotValid() {
		when(productMock.isValid()).thenReturn(false);
		when(userMock.isValid()).thenReturn(true);
		assertFalse(this.exchange.save());
	}
	
	@Test
	public void testSaveNotValidReceiverNotValid() {
		when(productMock.isValid()).thenReturn(true);
		when(userMock.isValid()).thenReturn(false);
		assertFalse(this.exchange.save());
	}
	
	@Test
	public void testSaveNotValidDbConnectNotValid() {
		when(productMock.isValid()).thenReturn(true);
		when(userMock.isValid()).thenReturn(true);
		when(dbconnectMock.saveUser(exchange.getReceiver())).thenReturn(false);
		when(dbconnectMock.saveUser(exchange.getProduct().getOwner())).thenReturn(false);
		when(dbconnectMock.saveProduct(productMock)).thenReturn(false);
		when(dbconnectMock.saveExchange(exchange)).thenReturn(false);
		when(gmailSenderMock.sendMessage(exchange.getReceiver().getEmail(), "alert", "VOUS ETES MINEUR")).thenReturn(true);
		assertFalse(this.exchange.save());
		
	}
	
	@Test
	public void testSaveNotValidGmailSenderNotValid() {
		when(productMock.isValid()).thenReturn(true);
		when(userMock.isValid()).thenReturn(true);
		when(dbconnectMock.saveUser(exchange.getReceiver())).thenReturn(true);
		when(dbconnectMock.saveUser(exchange.getProduct().getOwner())).thenReturn(true);
		when(dbconnectMock.saveProduct(productMock)).thenReturn(true);
		when(dbconnectMock.saveExchange(exchange)).thenReturn(true);
		when(gmailSenderMock.sendMessage(exchange.getReceiver().getEmail(), "alert", "VOUS ETES MINEUR")).thenReturn(false);
		assertFalse(this.exchange.save());
	}
	

}
