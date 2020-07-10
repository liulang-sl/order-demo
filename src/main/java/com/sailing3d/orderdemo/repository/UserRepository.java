package com.sailing3d.orderdemo.repository;

import com.sailing3d.orderdemo.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>, QueryByExampleExecutor<User> {

  void deleteByUsername(String username);

}