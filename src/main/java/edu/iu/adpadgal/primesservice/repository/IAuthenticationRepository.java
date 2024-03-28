package edu.iu.adpadgal.primesservice.repository;

import edu.iu.adpadgal.primesservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationRepository {

 Customer findByUsername(String username) throws IOException;

 boolean save(Customer customer) throws IOException;
}