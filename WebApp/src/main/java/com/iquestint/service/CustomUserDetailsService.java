package com.iquestint.service;

import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements Spring's UserDetailsService interface.
 *
 * @author Georgian Vladutu
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;

        try {
            user = userService.getUserByPnc(s);
        }
        catch (ServiceEntityNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }

        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(
            user.getPnc(), user.getPassword(),
            user.getUserState().getName().equals("Active"), true, true, true, getGrantedAuthorities(user));

        return user1;
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType().getName()));

        return authorities;
    }
}
