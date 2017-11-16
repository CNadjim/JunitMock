import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class DbConnectTest extends TestCase {
	
	private DbConnect dbconnect;
	private Exchange exchange;
	private GmailSender gmailSender;
	private Date from;
	private Date to;
	private User owner;
	private User user;
	private Product product;
	
	protected void setUp() throws Exception {
		super.setUp();
		dbconnect = new DbConnect("3306","mydb","root","root");
		user = new User("test@test.test","test","test",1);
		product = new Product("produit1",user);
		owner = new User("test@test.test","test","test",1);
		from = new Date();
		to = new Date();
		gmailSender = new GmailSender();
		exchange = new Exchange(owner, product,from,to,dbconnect,gmailSender);
	}
	
	protected void tearDown() throws Exception {
    		super.tearDown();
    		dbconnect = null;
    		user = null;
    		product = null;
    		owner = null;
    		from = null;
    		to = null;
    		gmailSender = null;
    		exchange = null;
	}

	@Test
	public void testSaveProduct() {
		assertTrue(this.dbconnect.saveProduct(product));
	}

	@Test
	public void testSaveUser() {
		assertTrue(this.dbconnect.saveUser(user));
	}

	@Test
	public void testSaveExchange() {
		assertTrue(this.dbconnect.saveExchange(exchange));
	}

}
