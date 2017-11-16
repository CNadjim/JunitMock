import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
	private String email;
	private String nom;
	private String prenom;
	private int age;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	
	public User(String email,String nom,String prenom,int age) {
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	
	public boolean isValid() {
		if (VALID_EMAIL_ADDRESS_REGEX .matcher(this.email).find()) {
			if (this.nom != null && this.prenom != null && this.age >= 13 ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isMajeur() {
		return this.age >= 18;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

	

}
