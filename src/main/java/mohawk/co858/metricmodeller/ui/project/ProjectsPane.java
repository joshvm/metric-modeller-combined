package mohawk.co858.metricmodeller.ui.project;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import mohawk.co858.metricmodeller.core.project.Project;

public class ProjectsPane extends BorderPane {

    private final TabPane tabPane;

    public ProjectsPane(){
        tabPane = new TabPane();

        setMargin(this, new Insets(5));
        setCenter(tabPane);
    }

    public TabPane tabPane(){
        return tabPane;
    }

    public void add(final Project project){
        final Tab tab = new Tab();
        tab.setContent(new ProjectPane(project));
        tab.setGraphic(GlyphsDude.createIconLabel(FontAwesomeIcon.PAPERCLIP, project.name().get(), "24px", "16px", ContentDisplay.LEFT));
        tabPane.getTabs().addAll(tab);
    }
}
