package mohawk.co858.metricmodeller.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mohawk.co858.metricmodeller.core.config.Config;
import mohawk.co858.metricmodeller.core.db.Database;
import mohawk.co858.metricmodeller.core.factor.Factors;
import mohawk.co858.metricmodeller.core.lang.Languages;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.weighting.Weightings;
import mohawk.co858.metricmodeller.ui.project.ProjectsPane;

public class App extends Application {

    private static final Map<Integer, Project> PROJECTS = new HashMap<>();

    private static Stage stage;
    private static BorderPane mainPane;
    private static ProjectsPane projectsPane;

    private static Label promptLabel;

    @Override
    public void start(final Stage stage) throws Exception {
        Config.init();
        Database.init();
        Languages.load();
        Weightings.load();
        mohawk.co858.metricmodeller.core.metric.Parameters.load();
        Factors.load();

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        App.stage = stage;

        projectsPane = new ProjectsPane();
        projectsPane.tabPane().getTabs().addListener(
                (ListChangeListener<Tab>) c -> {
                    if(projectsPane.tabPane().getTabs().isEmpty())
                        mainPane.setCenter(promptLabel);
                    else
                        mainPane.setCenter(projectsPane);
                }
        );

        promptLabel = new Label("Click the New button to get started!");
        promptLabel.setTextAlignment(TextAlignment.CENTER);
        promptLabel.setAlignment(Pos.CENTER);
        promptLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 30));

        mainPane = new BorderPane();
        mainPane.setPadding(new Insets(5));
        BorderPane.setMargin(mainPane, new Insets(10));
        mainPane.setTop(new ToolBar());
        mainPane.setCenter(promptLabel);

        stage.setScene(new Scene(mainPane, 935, 600));
        stage.setTitle("CO858 - Lying I's Metric Modeller");
        stage.centerOnScreen();
        stage.show();
    }

    public static void addProject(final Project project){
        PROJECTS.put(project.id().get(), project);
        projectsPane.add(project);
    }

    public static void main(String[] args){
        launch(args);
    }
}
