package mohawk.co858.metricmodeller.ui.project;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.ui.database.DatabaseComplexityPane;
import mohawk.co858.metricmodeller.ui.expertise.ExpertiseCountsTable;
import mohawk.co858.metricmodeller.ui.factor.FactorRatingsTable;
import mohawk.co858.metricmodeller.ui.lang.LanguageUsagesTable;
import mohawk.co858.metricmodeller.ui.metric.MetricMeasurementsTable;
import mohawk.co858.metricmodeller.ui.output.OutputPane;
import mohawk.co858.metricmodeller.ui.team.TeamPane;

public class ProjectPane extends BorderPane {

    private final Project project;

    private final LanguageUsagesTable languageUsagesTable;
    private final MetricMeasurementsTable metricMeasurementsTable;
    private final FactorRatingsTable factorRatingsTable;
    private final DatabaseComplexityPane databaseSizePane;
    private final ExpertiseCountsTable expertiseCountsTable;
    private final TeamPane teamPane;
    private final OutputPane outputPane;

    public ProjectPane(final Project project){
        this.project = project;

        setMargin(this, new Insets(10));

        languageUsagesTable = new LanguageUsagesTable(project);

        metricMeasurementsTable = new MetricMeasurementsTable(project);

        factorRatingsTable = new FactorRatingsTable(project);

        databaseSizePane = new DatabaseComplexityPane(project);

        expertiseCountsTable = new ExpertiseCountsTable(project);

        teamPane = new TeamPane(project);

        outputPane = new OutputPane(project);
        outputPane.setPrefHeight(150);

        final Tab languagesTab = new Tab();
        languagesTab.setContent(languageUsagesTable);
        languagesTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.CODE, "Languages", "24px", "16px", ContentDisplay.LEFT));

        final Tab metricsTab = new Tab();
        metricsTab.setContent(metricMeasurementsTable);
        metricsTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.BAR_CHART, "Parameters", "24px", "16px", ContentDisplay.LEFT));

        final Tab factorsTab = new Tab();
        factorsTab.setContent(factorRatingsTable);
        factorsTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.QUESTION, "Factors", "24px", "16px", ContentDisplay.LEFT));

        final Tab databaseTab = new Tab();
        databaseTab.setContent(databaseSizePane);
        databaseTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.DATABASE, "Database Size", "24px","16px", ContentDisplay.LEFT));

        final Tab peopleTab = new Tab();
        peopleTab.setContent(expertiseCountsTable);
        peopleTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.USER, "People", "24px", "16px", ContentDisplay.LEFT));

        final Tab teamTab = new Tab();
        teamTab.setContent(teamPane);
        teamTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.USERS, "Team", "24px", "16px", ContentDisplay.LEFT));

        final Tab outputTab = new Tab();
        outputTab.setContent(outputPane);
        outputTab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.CALCULATOR, "Output", "24px", "16px", ContentDisplay.LEFT));

        final TabPane tabs = new TabPane(languagesTab, metricsTab, factorsTab, databaseTab, peopleTab, teamTab, outputTab);
        tabs.setSide(Side.BOTTOM);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> {
                    if(n == outputTab)
                        outputPane.update();
                    if(n == teamTab)
                        teamPane.update();
                }
        );

        setCenter(tabs);
//        setBottom(outputPane);
    }


}
