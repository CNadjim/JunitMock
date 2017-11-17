import java.sql.SQLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Exchange {
	private User receiver;
	private Product product;
	private Date dateFrom;
	private Date dateTo;
	private DbConnect dbconnect;
	private GmailSender gmailSender;
	
	public Exchange(User receiver,Product product,Date dateFrom, Date dateTo) {
		this.receiver = receiver;
		this.product = product;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	
	public Exchange(User receiver,Product product,Date dateFrom, Date dateTo,DbConnect dbconnect,GmailSender gmailSender) {
		this.receiver = receiver;
		this.product = product;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.dbconnect = dbconnect;
		this.gmailSender = gmailSender;
	}
	
	
	public Boolean save() {
		if (receiver.isValid() && product.isValid() && this.dateIsValid()) {
			
			if (!receiver.isMajeur()) {
				if( ! gmailSender.sendMessage(receiver.getEmail(), "alert", "VOUS ETES MINEUR")) return false;
				
			}
			if (!(dbconnect.saveUser(this.receiver) && dbconnect.saveUser(this.product.getOwner()) && dbconnect.saveProduct(this.product) &&dbconnect.saveExchange(this))) return false;
		
			return true;
		}
		return false;
	}
	
	public Boolean dateIsValid() {
		return (dateFrom.after(new Date()) && dateFrom.before(dateTo)) ;
	}
	
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	
	

}
