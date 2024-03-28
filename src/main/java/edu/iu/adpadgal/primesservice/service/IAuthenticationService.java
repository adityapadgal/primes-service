package edu.iu.adpadgal.primesservice.service;

import edu.iu.adpadgal.primesservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationService {
 boolean register(Customer customer) throws IOException;
 boolean login(String username, String password) throws IOException;
}