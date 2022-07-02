package space.gavinklfong.demo.streamapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Slf4j
@DataJpaTest
class StreamApiTest {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepo productRepo;

	@Test
	@DisplayName("Obtain a list of product with category = \"Books\" and price > 100")
	void exercise1() {
		List<Product> booksWithCostHigherThan100 = productRepo.findAll()
			.stream()
				.filter(book -> {
					return book.getCategory().equals("Books") && book.getPrice() > 100;
				}
				).collect(Collectors.toList());

		//Asserts:
		Assertions.assertEquals(5, booksWithCostHigherThan100.size());
		booksWithCostHigherThan100.forEach(book -> {
			log.info("Testing product: {}", book.toString());
			Assertions.assertEquals(Boolean.TRUE, book.getPrice() > 100); // bigger than 100
			Assertions.assertEquals(Boolean.TRUE, book.getCategory().equals("Books")); // category = Books
		});
	}

	@Test
	@DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using Predicate chaining for filter)")
	void exercise1a() {
		//code the challenge
		List<Product> booksWithCostHigherThan100 = productRepo.findAll()
			.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("Books"))
				.filter(book -> book.getPrice() > 100)
				.collect(Collectors.toList());

		//Asserts:
		Assertions.assertEquals(5, booksWithCostHigherThan100.size());
		booksWithCostHigherThan100.forEach(book -> {
			log.info("Testing product: {}", book.toString());
			Assertions.assertEquals(Boolean.TRUE, book.getPrice() > 100); // bigger than 100
			Assertions.assertEquals(Boolean.TRUE, book.getCategory().equals("Books")); // category = Books
		});
	}

	@Test
	@DisplayName("Obtain a list of product with category = \"Books\" and price > 100 (using BiPredicate for filter)")
	void exercise1b() {
		//code the challenge
		BiPredicate<Product,String> checkCategory = (product,category) -> product.getCategory().equalsIgnoreCase(category);
		BiPredicate<Product,Integer> checkPrice = (product,price) -> product.getPrice() > price;
		List<Product> booksWithCostHigherThan100 = productRepo.findAll()
			.stream()
				.filter(product -> checkCategory.test(product,"Books")
						&& checkPrice.test(product,100))
				.collect(Collectors.toList());

		//Asserts:
		Assertions.assertEquals(5, booksWithCostHigherThan100.size());
		booksWithCostHigherThan100.forEach(book -> {
			log.info("Testing product: {}", book.toString());
			Assertions.assertEquals(Boolean.TRUE, book.getPrice() > 100); // bigger than 100
			Assertions.assertEquals(Boolean.TRUE, book.getCategory().equals("Books")); // category = Books
		});
	}

	@Test
	@DisplayName("Obtain a list of order with product category = \"Baby\"")
	void exercise2() {
		//code the challenge
		List<Order> ordersMapped = orderRepo.findAll()
				.stream()
				.filter(order -> order.getProducts()
						.stream()
						.anyMatch(product -> product.getCategory().equals("Baby")))
				.collect(Collectors.toList());
		ordersMapped.forEach(order ->log.info("Testing product: {}", order.toString()));

		Assertions.assertEquals(26,ordersMapped.size());
		ordersMapped.forEach(order -> order.getProducts()
				.forEach(product -> {}
				)
		);
	}

	@Test
	@DisplayName("Obtain a list of product with category = “Toys” and then apply 10% discount\"")
	public void exercise3() {
		//code the challenge

	}

	@Test
	@DisplayName("Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021")
	public void exercise4() {
		//code the challenge
	}

	@Test
	@DisplayName("Get the 3 cheapest products of \"Books\" category")
	public void exercise5() {
		//code the challenge

	}

	@Test
	@DisplayName("Get the 3 most recent placed order")
	public void exercise6() {
		//code the challenge
	}

	@Test
	@DisplayName("Get a list of products which was ordered on 15-Mar-2021")
	public void exercise7() {
		//code the challenge
	}

	@Test
	@DisplayName("Calculate the total lump of all orders placed in Feb 2021")
	public void exercise8() {
		//code the challenge
	}

	@Test
	@DisplayName("Calculate the total lump of all orders placed in Feb 2021 (using reduce with BiFunction)")
	public void exercise8a() {
		//code the challenge
	}

	@Test
	@DisplayName("Calculate the average price of all orders placed on 15-Mar-2021")
	public void exercise9() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain statistics summary of all products belong to \"Books\" category")
	public void exercise10() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a mapping of order id and the order's product count")
	public void exercise11() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a data map of customer and list of orders")
	public void exercise12() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a data map of customer_id and list of order_id(s)")
	public void exercise12a() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a data map with order and its total price")
	public void exercise13() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a data map with order and its total price (using reduce)")
	public void exercise13a() {
		//code the challenge
	}

	@Test
	@DisplayName("Obtain a data map of product name by category")
	public void exercise14() {
		//code the challenge
	}

	@Test
	@DisplayName("Get the most expensive product per category")
	void exercise15() {
		//code the challenge
	}
	
	@Test
	@DisplayName("Get the most expensive product (by name) per category")
	void exercise15a() {
		//code the challenge
	}

}
