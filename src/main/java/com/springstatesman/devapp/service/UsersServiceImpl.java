package com.springstatesman.devapp.service;


import com.springstatesman.devapp.entity.Users;
import com.springstatesman.devapp.model.UsersegistrationModel;
import com.springstatesman.devapp.repository.UsersRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by HP on 8/27/2020.
 */
@Service
public class UsersServiceImpl  {



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    UsersRepository usersRepository;

    public Users saveUser(UsersegistrationModel users) {

        Users users1 = new Users();
        users1.setEmail(users.getEmail());
        users1.setUsername(users.getUsername());
        if (!(users.getPassword().equals(users.getConfirmPassword()))) {
            throw new NullPointerException("The provided password and the confirm password does not match!");
        }
        String encodePassword = this.passwordEncoder.encode(users.getPassword());
        users1.setPassword(encodePassword);
        users1.setPassword(encodePassword);
        users.setGender(users.getGender());
        users1.setPermissions(users.getPermissions());
        users1.setTelephoneNumber(users.getTelephoneNumber());
        users1.setAccountNonExpired(true);
        users1.setAccountNonLocked(true);
        users1.setCredentialsNonExpired(true);
        users1.setRoles(users.getRoles());
        users1.setEnabled(true);
        return usersRepository.save(users1);



    }

    public Users findById(long id){return this.usersRepository.getOne(id);}
}