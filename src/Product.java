
public class Product {
	
	private String nom;
	private User owner;
	
	public Product(String nom, User owner) {
		this.nom = nom;
		this.owner = owner;
	}
	
	public boolean isValid() {
		if (this.nom != null && this.owner.isValid()) {
			return true;
		}
		return false;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
