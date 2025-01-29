package com.example.application.views;

import com.example.application.data.entity.Player;
import com.example.application.data.service.PesapalloService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;
import java.util.Comparator;

@Route(value = "statistics", layout = MainLayout.class)
@PageTitle("Tilastot")
@RolesAllowed({"USER", "ADMIN"})
public class StatisticsView extends VerticalLayout {
    private final Grid<Player> grid = new Grid<>(Player.class);
    private final PesapalloService service;

    public StatisticsView(PesapalloService service) {
        this.service = service;
        addClassName("statistics-view");
        setSizeFull();
        configureGrid();
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("statistics-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "team");
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getRunsScored() : 0)
            .setHeader("Lyödyt juoksut")
            .setSortable(true);
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getRunsBattedIn() : 0)
            .setHeader("Tuodut juoksut")
            .setSortable(true);
        grid.addColumn(player -> player.getStatistics() != null ? 
            player.getStatistics().getAdvancePoints() : 0)
            .setHeader("Kärkilyöntipisteet")
            .setSortable(true);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        add(grid);
    }

    private void updateList() {
        var players = service.findAllPlayers();
        players.sort((p1, p2) -> {
            var stats1 = p1.getStatistics();
            var stats2 = p2.getStatistics();
            if (stats1 == null) return 1;
            if (stats2 == null) return -1;
            return Integer.compare(stats2.getRunsScored(), stats1.getRunsScored());
        });
        grid.setItems(players);
    }
} 