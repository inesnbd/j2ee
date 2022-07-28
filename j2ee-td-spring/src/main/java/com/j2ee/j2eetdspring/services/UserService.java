package com.j2ee.j2eetdspring.services;

import com.j2ee.j2eetdspring.entities.User;
import com.j2ee.j2eetdspring.exceptions.ResourceNotFoundException;
import com.j2ee.j2eetdspring.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createOrUpdate(User user) {

        if (StringUtils.isNotEmpty(user.getPassword())) {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

        }

        return userRepository.save(user);

    }

    public User getUserById(Long id) throws ResourceNotFoundException {

        //return userRepository.findById(username).orElse(null);
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent())
        {
            return user.get();
        }
        throw new ResourceNotFoundException(User.class, id);

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);

    }

    public Page<User> getUsersWithPaging(Pageable pageable) {

        return userRepository.findAll(pageable);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user != null) {

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        }

        throw new UsernameNotFoundException("User '" + username + "' not found or inactive");

    }

    public void setPassword(String username, String newPassword) {

        User user = userRepository.findByUsername(username);

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        if (user != null) {

            user.setPassword(encodedNewPassword);
            userRepository.save(user);

        }

    }

    public void setMyPassword(String username, String newPassword) {

        User user = userRepository.findByUsername(username);

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        if (user != null) {

            user.setPassword(encodedNewPassword);
            userRepository.save(user);

        }

    }

}
