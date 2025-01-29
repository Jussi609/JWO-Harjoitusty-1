package com.example.application.views;

import com.example.application.data.entity.Team;
import com.example.application.service.TeamService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "teams", layout = MainLayout.class)
@PageTitle("Joukkueet")
@RolesAllowed({"ADMIN"})
public class TeamView extends VerticalLayout {
    private final TeamService teamService;
    private final Grid<Team> grid = new Grid<>(Team.class);
    private final TextField filterText = new TextField();

    public TeamView(TeamService teamService) {
        this.teamService = teamService;
        addClassName("team-view");
        setSizeFull();

        configureGrid();
        configureFilter();

        Button addTeamBtn = new Button("Lisää joukkue");
        addTeamBtn.addClickListener(e -> {
            Team team = new Team();
            // Tähän voisi lisätä lomakkeen joukkueen lisäämistä varten
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTeamBtn);
        add(toolbar, grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("team-grid");
        grid.setSizeFull();
        grid.setColumns("name", "city", "division");
        grid.getColumnByKey("name").setHeader("Nimi");
        grid.getColumnByKey("city").setHeader("Kaupunki");
        grid.getColumnByKey("division").setHeader("Sarja");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureFilter() {
        filterText.setPlaceholder("Hae nimellä...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        grid.setItems(teamService.findAllTeams());
    }
} 