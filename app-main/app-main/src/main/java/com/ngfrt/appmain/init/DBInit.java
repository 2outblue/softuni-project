package com.ngfrt.appmain.init;

import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.repository.HallRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    HallRepository hallRepository;

    public DBInit(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    @Override
    public void run(String... args) throws Exception {

        if (hallRepository.count() == 0) {
            initHalls();
        }
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
