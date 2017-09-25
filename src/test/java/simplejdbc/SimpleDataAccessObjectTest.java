package simplejdbc;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleDataAccessObjectTest {
	private SimpleDataAccessObject myDAO; // L'objet à tester
	private DataSource myDataSource; // La source de données à utiliser
	

	@Before
	public void setUp() throws SQLException {
		myDataSource = DataSourceFactory.getDataSource();
		myDAO = new SimpleDataAccessObject(myDataSource);
	}
	
	/**
	 * Test of numberOfCustomers method, of class SimpleDataAccessObject.
	 * @throws java.sql.SQLException
	 */
	@Test
	public void testNumberOfCustomers() throws SQLException {
		int result = myDAO.numberOfCustomers();
		assertEquals(13, result);
	}

	/**
	 * Test of numberOfOrdersForCustomer method, of class SimpleDataAccessObject.
	 * @throws java.sql.SQLException
	 */
	@Test
	public void testNumberOfOrdersForCustomer() throws SQLException {
		int customerId = 36;
		int expResult = 2;
		int result = myDAO.numberOfOrdersForCustomer(customerId);
		assertEquals(expResult, result); // Le client 36 a 2 bons de commande
	}

	/**
	 * Test of findCustomer method, of class SimpleDataAccessObject.
	 * @throws java.sql.SQLException
	 */
	@Test
	public void testFindCustomer() throws SQLException {
		int customedID = 1;
		CustomerEntity result = myDAO.findCustomer(customedID);
		assertEquals("Jumbo Eagle Corp", result.getName());
	}

	/**
	 * Test of customersInState method, of class SimpleDataAccessObject.
	 * @throws java.sql.SQLException
	 */
	@Test
	public void testCustomersInState() throws SQLException {
		String state = "CA";
		List<CustomerEntity> result = myDAO.customersInState(state);
		assertEquals(4, result.size());
	}
	
}
