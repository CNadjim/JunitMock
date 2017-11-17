import org.junit.Test;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;  

public class ProductTest extends TestCase {
	
	private Product product;
	private User userMock;
	
	protected void setUp() throws Exception {
		super.setUp();
		userMock = mock(User.class);
		product = new Product("test",userMock);
	}


	protected void tearDown() throws Exception {
    		super.tearDown();
		product = null;
	  }

	@Test
	public void testIsValid() {
		when(userMock.isValid()).thenReturn(true);
		assertTrue(this.product.isValid());
	}

	public void testNomProduct() {
		this.product.setNom(null);
		assertFalse(this.product.isValid());
	}
	

}
