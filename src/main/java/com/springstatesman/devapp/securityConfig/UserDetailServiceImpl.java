package com.springstatesman.devapp.securityConfig;

import com.springstatesman.devapp.entity.Users;
import com.springstatesman.devapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by HP on 4/27/2021.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users myUsers = this.userRepository.findByUsername(userName);

        User user = new User(myUsers.getUsername(),myUsers.getPassword(),myUsers.isEnabled(),myUsers.isAccountNonExpired(),
                myUsers.isCredentialsNonExpired(),myUsers.isAccountNonLocked(),myUsers.getAuthorities());
        return user;
    }
}
