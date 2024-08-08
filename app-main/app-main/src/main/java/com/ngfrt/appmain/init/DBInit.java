package com.ngfrt.appmain.init;

import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.repository.HallRepository;
import com.ngfrt.appmain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    HallRepository hallRepository;

    public DBInit(HallRepository hallRepository, UserRepository userRepository) {
        this.hallRepository = hallRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        if (hallRepository.count() == 0) {
            initHalls();
            initUsers();
        }
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User()
                .setUuid(UUID.randomUUID())
                .setFirstName("admin")
                .setLastName("admin")
                .setEmail("mail@example.com")
                .setPhone("9090909009")
                .setPassword("1234")
                .setActive(true)

        );
        userRepository.saveAll(users);
    }

    private void initHalls() {
        List<Hall> halls = new ArrayList<>();

        halls.add(new Hall()
                    .setNumber(1)
                    .setCapacity(80)
                    .setName("Platform 80")
                    .setDescription("A hall with capacity for 80 seats. ADD_DESCRIPTION")
                    .setUuid(UUID.randomUUID())
        );
        halls.add(new Hall()
                .setNumber(2)
                .setCapacity(250)
                .setName("Jackson")
                .setDescription("A hall with capacity for 250 seats. ADD_DESCRIPTION")
                .setUuid(UUID.randomUUID())
        );
        halls.add(new Hall()
                .setNumber(3)
                .setCapacity(350)
                .setName("Jackson")
                .setDescription("A hall with capacity for 350 seats. ADD_DESCRIPTION")
                .setUuid(UUID.randomUUID())
        );
        halls.add(new Hall()
                .setNumber(4)
                .setCapacity(550)
                .setName("Prism")
                .setDescription("A hall with capacity for 550 seats. ADD_DESCRIPTION")
                .setUuid(UUID.randomUUID())
        );

        hallRepository.saveAll(halls);
    }
}
