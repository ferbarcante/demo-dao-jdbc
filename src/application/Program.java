package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {

	public static void main(String[] args) {

		Department obj = new Department(1, "Books");
		System.out.println(obj);

		Seller sl = new Seller(23, "bob", "bob@gmail.com", new Date(), 3000.00, obj);

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println(sl);
	}

}
