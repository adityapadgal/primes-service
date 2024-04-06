package edu.iu.adpadgal.primesservice.service;

import edu.iu.adpadgal.primesservice.model.Customer;
import edu.iu.adpadgal.primesservice.repository.AuthenticationDBRepository;
import edu.iu.adpadgal.primesservice.repository.AuthenticationFileRepository;
import edu.iu.adpadgal.primesservice.repository.IAuthenticationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {
    AuthenticationDBRepository authenticationRepository;

    public AuthenticationService(AuthenticationDBRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }
    @Override
    public Customer register(Customer customer) throws IOException {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String passwordEncoded = bc.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        return authenticationRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            Customer customer = authenticationRepository.findByUsername(username);
            if(customer==null){
                throw new UsernameNotFoundException("");
            }
            return User
                    .withUsername(username)
                    .password(customer.getPassword())
                    .build();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(String username, String password) throws IOException {
        return false;
    }
}