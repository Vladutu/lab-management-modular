package com.iquestint.service.impl;

import com.iquestint.dto.UserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.UserService;
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
 * This class implements UserDetailsService interface.
 *
 * @author Georgian Vladutu
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = null;

        try {
            userDto = userService.getUserByPnc(s);
        } catch (ServiceEntityNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }

        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(
            userDto.getPnc(), userDto.getPassword(),
            userDto.getUserState().equals("Active"), true, true, true, getGrantedAuthorities(userDto));

        return user1;
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userDto.getUserType()));

        return authorities;
    }
}
