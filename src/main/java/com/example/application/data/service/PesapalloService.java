package com.example.application.data.service;

import com.example.application.data.entity.Player;
import com.example.application.data.entity.Statistics;
import com.example.application.data.repository.PlayerRepository;
import com.example.application.data.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesapalloService {
    private final PlayerRepository playerRepository;
    private final StatisticsRepository statisticsRepository;

    public PesapalloService(PlayerRepository playerRepository, StatisticsRepository statisticsRepository) {
        this.playerRepository = playerRepository;
        this.statisticsRepository = statisticsRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public void saveStatistics(Statistics statistics) {
        statisticsRepository.save(statistics);
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
} 