package com.example.projektpp.Serwisy;

import com.example.projektpp.Repos.User_repo;
import com.example.projektpp.models.MyUserDetails;
import com.example.projektpp.models.Role;
import com.example.projektpp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private User_repo userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        else {
            if (user.isActive() == false) {
                throw new ExceptionInInitializerError("Urzytkownik nie został aktywowany");
            }

            return convertToUserDetails(user);
        }
    }


    private UserDetails convertToUserDetails(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getType().toString()));
            System.out.println(role.getType().toString());
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }


}
