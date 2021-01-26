package br.com.ticlab.dsdeliver2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ticlab.dsdeliver2.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByOrderByNameAsc();

}
