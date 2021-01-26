package br.com.ticlab.dsdeliver2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ticlab.dsdeliver2.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
