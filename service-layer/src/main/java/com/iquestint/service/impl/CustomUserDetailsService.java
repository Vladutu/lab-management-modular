package com.iquestint.service.impl;

import com.iquestint.dto.UserDto;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.service.AdministrationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    AdministrationUserService administrationUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = null;

        try {
            userDto = administrationUserService.getUserByPnc(s);
        } catch (ServiceEntityNotFoundException e) {
            LOGGER.debug("ServiceEntityNotFoundException");
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(
            userDto.getPnc(), userDto.getPassword(),
            userDto.getUserState().equals("Active"), true, true, true, getGrantedAuthorities(userDto));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userDto.getUserType()));

        return authorities;
    }
}
