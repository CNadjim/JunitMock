import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Principal {

	public static void main(String[] args) {
		
		DbConnect dbconnect = new DbConnect("3306","mydb","root","root");
		GmailSender gmailSender = new GmailSender();
		User user = new User("c.nadjim@gmail.com","chabane","nadjim",18);
		Product product = new Product("produit1",user);
		User owner = new User("c.nadjim@gmail.com","test","test",13);
		Date from = new Date(new Date().getTime() + 1000 * 60 * 60 * 24);
		Date to = new Date(from.getTime() + 1000);
		Exchange exchange = new Exchange(owner, product,from,to,dbconnect,gmailSender);
		exchange.save();
	}

}
