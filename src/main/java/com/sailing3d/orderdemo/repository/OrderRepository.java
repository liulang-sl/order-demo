package com.sailing3d.orderdemo.repository;

import java.util.List;

import com.sailing3d.orderdemo.domain.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String>, QueryByExampleExecutor<Order> {

  void deleteByUsername(String username);

  List<Order> findByUsername(String username);
}