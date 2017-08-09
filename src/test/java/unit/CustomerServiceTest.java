package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.buscapecompany.poc.cucumbermicroservice.model.Customer;
import com.buscapecompany.poc.cucumbermicroservice.repository.CustomerRepository;
import com.buscapecompany.poc.cucumbermicroservice.services.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repository;
	private CustomerServiceImpl service;
	
	@Before
	public void setup(){
		repository = mock(CustomerRepository.class);
		service = new CustomerServiceImpl(repository);
	}
	
	@Test
	public void testGetAllCustomer(){
		when(repository.findAll())
			.thenReturn(Arrays.asList(1l,2l,3l,4l)
			.stream()
			.map(id -> new Customer(id, "CUstomer"+id, "CUstomer"+id, "CUstomer"+id, 99))
			.collect(Collectors.toList()));
		List<Customer> result = service.findAll();
		assertEquals(4, result.size());
	}
	
	@Test
	public void testGetCustomerById(){
		when(repository.findOne(1L)).thenReturn(new Customer(1l, "CUstomer1", "CUstomer1", "CUstomer1", 99));
		Customer result = service.findOne(1);
		
		assertEquals(true, new Long(1).equals(result.getId()));
		assertEquals("CUstomer1", result.getFirstName());
		assertEquals("CUstomer1", result.getSurName());
		assertEquals("CUstomer1", result.getLastName());
		assertEquals(true, new Integer(99).equals(result.getAge()));
	}
	
	@Test
	public void saveCustomer(){
		Customer Customer = new Customer(8l, "CUstomer1", "CUstomer1", "CUstomer1", 99);
		when(repository.save(Customer)).thenReturn(Customer);
		Customer result = service.save(Customer);
		assertEquals(true, new Long(8).equals(result.getId()));
		assertEquals("CUstomer1", result.getFirstName());
		assertEquals("CUstomer1", result.getSurName());
		assertEquals("CUstomer1", result.getLastName());
		assertEquals(true, new Integer(99).equals(result.getAge()));
	}
	
	@Test
	public void removeCustomer(){
		Customer Customer = new Customer(8l, "CUstomer1", "CUstomer1", "CUstomer1", 99);
		service.delete(Customer);
        verify(repository, times(1)).delete(Customer);
	}
}
