package br.com.ticlab.dsdeliver2.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ticlab.dsdeliver2.dto.OrderDTO;
import br.com.ticlab.dsdeliver2.dto.ProductDTO;
import br.com.ticlab.dsdeliver2.entities.Order;
import br.com.ticlab.dsdeliver2.entities.OrderStatus;
import br.com.ticlab.dsdeliver2.entities.Product;
import br.com.ticlab.dsdeliver2.repositories.OrderRepository;
import br.com.ticlab.dsdeliver2.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional()
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order); 
	}

}
