package com.model;

import com.entity.Users;
import com.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsersRepository usersRepository;

    @Autowired
    public DataLoader(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void run(ApplicationArguments args) {

        if(usersRepository.count() == 0){

            for (int i = 0; i<=10; i ++)
            {
                Users users = new Users();
                users.setUsername("u" +i);
                users.setPassword("$2a$12$VND7nEQJJt3QV3VXyr4H4ezphzjDbRZDdK710/suJ0FH9hpIulypu");
                usersRepository.save(users);
            }

        }
         else System.out.println(usersRepository.count());
    }
}
