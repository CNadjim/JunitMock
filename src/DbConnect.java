import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class DbConnect {
	
	private String port;
	private String dbName;
	private String username;
	private String password;
	private Connection connection;
	private Statement stmt;
	
	public DbConnect(String port, String dbName, String username, String password) {
		
		this.port = port;
		this.dbName = dbName;
		this.username = username;
		this.password = password;
		
		System.out.println("MySQL JDBC Connection Testing");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:"+this.port+"/"+this.dbName+"?useSSL=false",this.username, this.password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console \nDont forget to run the command 'docker-compose up -d' in your folder projet bash");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("MySQL JDBC Connection Established");
		} else {
			System.out.println("Failed to make connection");
		}
		
		
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user (email VARCHAR(255) PRIMARY KEY,nom VARCHAR(100),prenom VARCHAR(100),age INT)");
		 	stmt.executeUpdate("CREATE TABLE IF NOT EXISTS product (nom VARCHAR(100),user_id VARCHAR(255) references user(email),PRIMARY KEY (`nom`,`user_id`))");
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS exchanges (receiver_id VARCHAR(255) references user(email),product_name VARCHAR(100)  references product(nom),date_from DATE,date_to DATE,PRIMARY KEY (`receiver_id`,`product_name`,`date_from`,`date_to`))");
		        
		} catch (SQLException e) {
			System.out.println("Can not create tables");
			e.printStackTrace();
		}
       
        
	}
	
	public boolean testConnect() {
		return true;
	}
	
	public boolean saveProduct(Product product) {
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT IGNORE product VALUES ('"+product.getNom()+"','"+product.getOwner().getEmail()+"')");
			return true;
		}catch (SQLException e) {
			System.out.println("Can not insert product");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean saveUser(User user) {
		
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT IGNORE user VALUES ('"+user.getEmail()+"','"+user.getNom()+"','"+user.getPrenom()+"','"+user.getAge()+"')");
			return true;
		}catch (SQLException e) {
			System.out.println("Can not insert user");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean saveExchange(Exchange exchange) {
		
		java.sql.Timestamp sqlDateTimeFrom = new java.sql.Timestamp(exchange.getDateFrom().getTime());
		java.sql.Timestamp sqlDateTimeTo = new java.sql.Timestamp(exchange.getDateTo().getTime());
		
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("INSERT IGNORE exchanges VALUES ('"+exchange.getReceiver().getEmail()+"','"+exchange.getProduct().getNom()+"','"+sqlDateTimeFrom+"','"+sqlDateTimeTo+"')");
			return true;
		}catch (SQLException e) {
			System.out.println("Can not insert exchange");
			e.printStackTrace();
			return false;
		}
		
	}

	
	
	
	

}
