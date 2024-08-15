package com.ngfrt.appmain.init;

import com.ngfrt.appmain.model.entity.Hall;
import com.ngfrt.appmain.model.entity.User;
import com.ngfrt.appmain.model.entity.UserRoleEntity;
import com.ngfrt.appmain.model.entity.enums.UserRoleEnum;
import com.ngfrt.appmain.model.mapper.UserMapper;
import com.ngfrt.appmain.repository.HallRepository;
import com.ngfrt.appmain.repository.UserRepository;
import com.ngfrt.appmain.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HallRepository hallRepository;
    private final UserRoleRepository userRoleRepository;

    public DBInit(HallRepository hallRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.hallRepository = hallRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }
    @Override
    public void run(String... args) throws Exception {


        if (userRoleRepository.count() == 0) {
            initUserRoles();
        }

        if (hallRepository.count() == 0) {
            initHalls();
        }

        if (userRepository.count() == 0) {
            initUsers();
        }
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();

        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole1 = userRoleRepository.findByRole(UserRoleEnum.USER);

        List<UserRoleEntity> user1Roles = new ArrayList<>();
        user1Roles.add(adminRole);
        user1Roles.add(userRole1);
        users.add(new User()
                .setUuid(UUID.fromString("94348ab3-ed93-4637-96f8-63412eae5de5"))
                .setFirstName("admin")
                .setLastName("admin")
                .setEmail("admin@cc.com")
                .setPhone("9090909009")
                .setPassword("bbca3b7abfa10ae8af3401e05d900857cb1eacea87dccc0650228fe74f40ae434dbd8bc24411f105a99666543455c3c5")
                .setActive(true)
                .setRoles(user1Roles)

        );
        List<UserRoleEntity> user2Roles = new ArrayList<>();
        UserRoleEntity userRole2 = userRoleRepository.findByRole(UserRoleEnum.USER);
        user2Roles.add(userRole2);
        users.add(new User()
                .setUuid(UUID.fromString("345edcde-5ae2-4029-9822-b1695829a1b2"))
                .setFirstName("User")
                .setLastName("Userov")
                .setEmail("user@cc.com")
                .setPhone("9090909009")
                .setPassword("bbca3b7abfa10ae8af3401e05d900857cb1eacea87dccc0650228fe74f40ae434dbd8bc24411f105a99666543455c3c5")
                .setActive(true)
                .setRoles(user2Roles)

        );
        userRepository.saveAll(users);
    }

    private void initHalls() {
        List<Hall> halls = new ArrayList<>();

        halls.add(new Hall()
                    .setNumber(1)
                    .setCapacity(80)
                    .setName("Platform 80")
                    .setDescription("Platform 80 is an intimate space, designed for focused discussions and smaller group sessions. With a capacity of 80 seats, this hall is ideal for workshops, training sessions, or exclusive presentations. The room is equipped with state-of-the-art audiovisual technology, ensuring that every seat has a clear view and sound quality is optimized for engagement. The minimalist, functional design makes it a practical choice for events where interaction and participation are key.")
                    .setUuid(UUID.fromString("13752743-5291-4ba3-8321-09c5b4dbe79f"))
                .setShortDescription("Seating capacity: 80, Usable area: 120 square meters. A compact and versatile space, perfect for workshops, training sessions, and intimate gatherings.")
        );
        halls.add(new Hall()
                .setNumber(2)
                .setCapacity(200)
                .setName("Jackson")
                .setDescription("Jackson is a versatile hall that accommodates up to 200 attendees, making it perfect for mid-sized seminars, panels, or breakout sessions. The seating is arranged to foster collaboration and engagement, while the modern lighting and acoustics provide a professional atmosphere. The room is designed with practicality in mind, featuring modular seating and advanced connectivity options to support a variety of event formats.")
                .setUuid(UUID.fromString("94348ab3-ed93-4637-96f8-63412eae5de5"))
                .setShortDescription("Seating capacity: 200, Usable area: 275 square meters.  Ideal for seminars, breakout sessions, and mid-sized meetings")
        );
        halls.add(new Hall()
                .setNumber(3)
                .setCapacity(450)
                .setName("Prism")
                .setDescription("Prism is a spacious hall designed for larger gatherings such as conferences, product launches, and keynote presentations. With 450 seats, this venue offers ample space while maintaining a sense of intimacy. The hall is equipped with cutting-edge audiovisual systems, flexible seating arrangements, and a design that emphasizes both elegance and practicality, making it suitable for a wide range of events.")
                .setUuid(UUID.fromString("0895c4b1-321d-43ff-af83-633e8b88dddd"))
                .setShortDescription("Seating capacity: 450, Usable area: 550 square meters. Prism provides a spacious and elegant setting for larger conferences, keynote presentations, and product launches.")
        );
        halls.add(new Hall()
                .setNumber(4)
                .setCapacity(900)
                .setName("Concourse")
                .setDescription("Concourse is a large and dynamic space, capable of accommodating up to 900 attendees. This hall is ideal for major conferences, exhibitions, or performances, offering advanced lighting and sound systems to enhance the experience. The design is modern and vibrant, with flexible configurations to suit various event formats. Concourse’s expansive layout ensures that every attendee has a clear view and excellent acoustics.")
                .setUuid(UUID.fromString("5a3ba439-29ff-41fc-8ad7-3a6650c64831"))
                .setShortDescription("Seating capacity: 900, Usable area: 1100 square meters. Concourse is a dynamic venue suitable for large conferences, exhibitions, and performances.")
        );
        halls.add(new Hall()
                .setNumber(5)
                .setCapacity(2000)
                .setName("Forum")
                .setDescription("Forum is the largest and most prestigious hall in the convention center, designed for large-scale events such as conventions, summits, and major performances. With a seating capacity of 2000, this grand venue is equipped with state-of-the-art technology, including a high-powered sound system, large projection screens, and dynamic lighting. The architecture combines modern aesthetics with functionality, providing a powerful and immersive experience for all attendees.")
                .setUuid(UUID.fromString("345edcde-5ae2-4029-9822-b1695829a1b2"))
                .setShortDescription("Seating capacity: 2000, Usable area: 2200 square meters. Forum is the grandest hall in our convention center, designed for large-scale events such as conventions, summits, and major performances.")
        );

        hallRepository.saveAll(halls);
    }

    private void initUserRoles() {
        userRoleRepository.save(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
        userRoleRepository.save(new UserRoleEntity().setRole(UserRoleEnum.USER));
    }
}
