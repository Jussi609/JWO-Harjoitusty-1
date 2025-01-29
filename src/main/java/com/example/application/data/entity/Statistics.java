package com.example.application.data.entity;

import jakarta.persistence.*;

@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
    
    private int runsScored;
    private int runsBattedIn;
    private int advancePoints;
    private int games;
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getRunsScored() {
        return runsScored;
    }
    
    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }
    
    public int getRunsBattedIn() {
        return runsBattedIn;
    }
    
    public void setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn = runsBattedIn;
    }
    
    public int getAdvancePoints() {
        return advancePoints;
    }
    
    public void setAdvancePoints(int advancePoints) {
        this.advancePoints = advancePoints;
    }
    
    public int getGames() {
        return games;
    }
    
    public void setGames(int games) {
        this.games = games;
    }
} 