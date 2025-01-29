package com.example.application.views;

import com.example.application.data.entity.Player;
import com.example.application.data.service.PesapalloService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.PageTitle;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Pesäpallo Tilastot")
@RolesAllowed({"USER", "ADMIN"})
public class MainView extends VerticalLayout {
    private final PesapalloService service;
    private final Grid<Player> grid = new Grid<>(Player.class);
    private final TextField filterText = new TextField();
    private final PlayerForm form = new PlayerForm();
    private final Dialog dialog = new Dialog();

    public MainView(PesapalloService service) {
        this.service = service;
        addClassName("main-view");
        setSizeFull();
        configureGrid();
        configureDialog();
        
        filterText.setPlaceholder("Hae nimellä...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addPlayerBtn = new Button("Lisää pelaaja");
        addPlayerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setPlayer(new Player());
            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addPlayerBtn);

        add(toolbar, grid);
        updateList();
        
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                form.setPlayer(event.getValue());
                dialog.open();
            }
        });
    }

    private void configureGrid() {
        grid.addClassName("player-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "team", "position");
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getRunsScored() : 0).setHeader("Lyödyt juoksut");
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getRunsBattedIn() : 0).setHeader("Tuodut juoksut");
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getAdvancePoints() : 0).setHeader("Kärkilyöntipisteet");
        
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        // Suomenkieliset otsikot
        grid.getColumnByKey("firstName").setHeader("Etunimi");
        grid.getColumnByKey("lastName").setHeader("Sukunimi");
        grid.getColumnByKey("team").setHeader("Joukkue");
        grid.getColumnByKey("position").setHeader("Pelipaikka");
    }
    
    private void configureDialog() {
        dialog.add(form);
        dialog.setModal(true);
        dialog.setDraggable(true);
        
        form.addSaveListener(event -> {
            service.savePlayer(event.getPlayer());
            updateList();
            dialog.close();
        });
        
        form.addDeleteListener(event -> {
            service.deletePlayer(event.getPlayer());
            updateList();
            dialog.close();
        });
        
        form.addCloseListener(event -> dialog.close());
    }

    private void updateList() {
        grid.setItems(service.findAllPlayers());
    }
} 