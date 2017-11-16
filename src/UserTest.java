import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;


public class UserTest extends TestCase {
	private User user;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User("test@test.fr","test","test",18);
	}

	protected void tearDown() throws Exception {
    		super.tearDown();
		user = null;
	  }
	
	
	@Test
	public void testIsValid() {
		assertTrue("Champ invalide",this.user.isValid());
	}
	
	public void testUserMajeur() {
		user.setAge(17);
		assertFalse("Utilisateur mineur", this.user.isMajeur());
	}

	public void testEmail() {
		this.user.setEmail("");
		assertFalse("Email invalide",this.user.isValid());
	}
	
	public void testPersonne() {
	    assertNotNull("L'instance n'est pas créée", user);
    }
	
	

}
