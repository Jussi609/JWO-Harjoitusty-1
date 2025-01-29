package com.example.application.data;

import com.example.application.data.entity.Player;
import com.example.application.data.entity.Statistics;
import com.example.application.data.entity.User;
import com.example.application.data.repository.PlayerRepository;
import com.example.application.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            PlayerRepository playerRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Luodaan käyttäjät
            if (userRepository.count() == 0) {
                // Luodaan USER-roolilla käyttäjä
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("USER");
                user.setActive(true);
                userRepository.save(user);

                // Luodaan ADMIN-roolilla käyttäjä
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole("ADMIN");
                admin.setActive(true);
                userRepository.save(admin);
            }

            // Luodaan testidataa
            if (playerRepository.count() == 0) {
                Player player1 = new Player();
                player1.setFirstName("Matti");
                player1.setLastName("Meikäläinen");
                player1.setTeam("Sotkamon Jymy");
                player1.setPosition("Lukkari");

                Statistics stats1 = new Statistics();
                stats1.setPlayer(player1);
                stats1.setRunsScored(10);
                stats1.setRunsBattedIn(15);
                stats1.setAdvancePoints(20);
                stats1.setGames(5);
                player1.setStatistics(stats1);

                playerRepository.save(player1);

                Player player2 = new Player();
                player2.setFirstName("Maija");
                player2.setLastName("Mehiläinen");
                player2.setTeam("Jyväskylän Kiri");
                player2.setPosition("Koppari");

                Statistics stats2 = new Statistics();
                stats2.setPlayer(player2);
                stats2.setRunsScored(8);
                stats2.setRunsBattedIn(12);
                stats2.setAdvancePoints(18);
                stats2.setGames(5);
                player2.setStatistics(stats2);

                playerRepository.save(player2);
            }
        };
    }
} 