package com.springstatesman.devapp.commandline;

import com.springstatesman.devapp.entity.Users;
import com.springstatesman.devapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by E.Statesman on 4/10/2021.
 */
@Service
public class DbUnit implements CommandLineRunner {

     BCryptPasswordEncoder passwordEncoder;

//    @Autowired
     UsersRepository userRepository;

    @Autowired
    public DbUnit(BCryptPasswordEncoder passwordEncoder, UsersRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        // this method is supposed to add three user to the database
        // simply uncomment the saveAll() and run the application

        Users admin = new Users("admin",passwordEncoder.encode("admin123"),"ADMIN","GENERATE_BILL","MALE",passwordEncoder.encode("admin123"),"admin@gmail.com","08137234423",true,true,true,true);
        Users dan = new Users("dan",passwordEncoder.encode("dan123"),"STUDENT","CHECK_EXAMS","MALE",passwordEncoder.encode("dan123"),"dan@gmail.com","08054432219",true,true,true,true);
        Users obi = new Users("obi",passwordEncoder.encode("obi123"),"LECTURER","GENERATE_BILL","MALE",passwordEncoder.encode("obi123"),"obi@gmail.com","07065521369",true,true,true,true);

        List<Users> users = Arrays.asList(admin,dan,obi);

//        userRepository.saveAll(users);

//        Users users = new Users("admin@gmail.com","admin2",
//                passwordEncoder.encode("admin123"),"ROLE_ADMIN",true,true,true,true);
//    this.userRepository.save(users);
//        this.userRepository.saveAll(users);
    }

}
