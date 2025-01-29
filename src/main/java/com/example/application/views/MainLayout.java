package com.example.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
        createFooter();
    }

    private void createHeader() {
        H1 logo = new H1("Pesäpallo Tilastot");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM);

        Button logout = new Button("Kirjaudu ulos", e -> getUI().ifPresent(ui -> ui.getPage().setLocation("/logout")));

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink playerList = new RouterLink("Pelaajat", MainView.class);
        RouterLink statistics = new RouterLink("Tilastot", StatisticsView.class);
        RouterLink teams = new RouterLink("Joukkueet", TeamView.class);

        addToDrawer(new VerticalLayout(
            playerList,
            statistics,
            teams
        ));
    }

    private void createFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setHeight("50px");
        footer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        footer.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        footer.add(new Span("© 2024 Pesäpallo Tilastot"));
        footer.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
        footer.getStyle().set("padding", "var(--lumo-space-m)");
        addToDrawer(footer);
    }
} 