package com.example.application.views;

import com.example.application.data.entity.Player;
import com.example.application.data.entity.Statistics;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class PlayerForm extends FormLayout {
    private Player player;
    private Statistics statistics;
    
    TextField firstName = new TextField("Etunimi");
    TextField lastName = new TextField("Sukunimi");
    DatePicker dateOfBirth = new DatePicker("Syntymäaika");
    TextField team = new TextField("Joukkue");
    TextField position = new TextField("Pelipaikka");
    
    IntegerField runsScored = new IntegerField("Lyödyt juoksut");
    IntegerField runsBattedIn = new IntegerField("Tuodut juoksut");
    IntegerField advancePoints = new IntegerField("Kärkilyöntipisteet");
    IntegerField games = new IntegerField("Pelatut pelit");
    
    Button save = new Button("Tallenna");
    Button delete = new Button("Poista");
    Button cancel = new Button("Peruuta");
    
    Binder<Player> playerBinder = new Binder<>(Player.class);
    Binder<Statistics> statisticsBinder = new Binder<>(Statistics.class);
    
    public PlayerForm() {
        addClassName("player-form");
        
        playerBinder.bindInstanceFields(this);
        statisticsBinder.bindInstanceFields(this);
        
        add(
            firstName, lastName, dateOfBirth, team, position,
            runsScored, runsBattedIn, advancePoints, games,
            createButtonLayout()
        );
    }
    
    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, player)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));
        
        return new HorizontalLayout(save, delete, cancel);
    }
    
    private void validateAndSave() {
        if (playerBinder.isValid() && statisticsBinder.isValid()) {
            fireEvent(new SaveEvent(this, player));
        }
    }
    
    public void setPlayer(Player player) {
        this.player = player;
        if (player != null) {
            this.statistics = player.getStatistics();
            if (this.statistics == null) {
                this.statistics = new Statistics();
                this.statistics.setPlayer(player);
                player.setStatistics(this.statistics);
            }
            playerBinder.readBean(player);
            statisticsBinder.readBean(this.statistics);
        }
    }
    
    // Events
    public static abstract class PlayerFormEvent extends ComponentEvent<PlayerForm> {
        private Player player;
        
        protected PlayerFormEvent(PlayerForm source, Player player) {
            super(source, false);
            this.player = player;
        }
        
        public Player getPlayer() {
            return player;
        }
    }
    
    public static class SaveEvent extends PlayerFormEvent {
        SaveEvent(PlayerForm source, Player player) {
            super(source, player);
        }
    }
    
    public static class DeleteEvent extends PlayerFormEvent {
        DeleteEvent(PlayerForm source, Player player) {
            super(source, player);
        }
    }
    
    public static class CloseEvent extends PlayerFormEvent {
        CloseEvent(PlayerForm source) {
            super(source, null);
        }
    }
    
    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    
    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }
    
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
} 