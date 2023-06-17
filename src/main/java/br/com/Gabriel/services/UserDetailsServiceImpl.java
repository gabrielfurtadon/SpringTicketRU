package br.com.Gabriel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String ra) throws UsernameNotFoundException {
        User user = userService.findByRa(ra);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with RA: " + ra);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getRa()) // Use RA as the username
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }

}
