package simplejdbc;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class DAOTest {
	private DAO myDAO; // L'objet à tester
	private DataSource myDataSource; // La source de données à utiliser
	

	@Before
	public void setUp() throws SQLException {
		myDataSource = DataSourceFactory.getDataSource();
		myDAO = new DAO(myDataSource);
	}
	
	/**
	 * Test of numberOfCustomers method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testNumberOfCustomers() throws DAOException {
		int result = myDAO.numberOfCustomers();
		assertEquals(13, result);
	}

	/**
	 * Test of numberOfOrdersForCustomer method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testNumberOfOrdersForCustomer() throws DAOException {
		int customerId = 36;
		int expResult = 2;
		int result = myDAO.numberOfOrdersForCustomer(customerId);
		assertEquals(expResult, result); // Le client 36 a 2 bons de commande
	}

	/**
	 * Test of findCustomer method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testFindCustomer() throws DAOException {
		int customerID = 1;
		CustomerEntity result = myDAO.findCustomer(customerID);
		assertEquals("Jumbo Eagle Corp", result.getName());
	}

	/**
	 * Test of customersInState method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testCustomersInState() throws DAOException {
		String state = "CA";
		List<CustomerEntity> result = myDAO.customersInState(state);
		assertEquals(4, result.size());
	}

	/**
	 * Test of deleteCustomer method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test
	public void testDeleteUnknownCustomer () throws DAOException {
		int id = 999;
		assertEquals(0, myDAO.deleteCustomer(id));
	}

	/**
	 * Test of deleteCustomer method, of class DAO.
	 * @throws simplejdbc.DAOException
	 */
	@Test @Ignore // Ce test est désactivé, pourquoi ?
	public void testDeleteCustomerWithoutOrder () throws DAOException {
		int id = 25; // Le client 25 n'a pas de bon de commande
		assertEquals(1, myDAO.deleteCustomer(id));
	}

	/**
	 * Test of deleteCustomer method, of class DAO.
	 */
	@Test
	public void testDeleteCustomerWithOrder () {
		int id = 1; // Le client 1 a des bons de commande
		try {
			myDAO.deleteCustomer(id); // Cette ligne doit lever une exception
			fail(); // On ne doit pas passer par ici
		} catch (DAOException e) {
			// On doit passer par ici, violation d'intégrité référentielle
		}
	}
	
}
