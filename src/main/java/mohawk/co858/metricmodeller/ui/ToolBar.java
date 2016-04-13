package mohawk.co858.metricmodeller.ui;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Optional;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.util.Utils;

public class ToolBar extends BorderPane {

    private final Button newButton;
    private final Button importButton;
    private final Button exportButton;

    private final Button githubButton;
    private final Button resourcesButton;

    public ToolBar(){
        newButton = GlyphsDude.createIconButton(FontAwesomeIcon.PLUS, "New", "24px", "16px", ContentDisplay.TOP);
        newButton.setOnAction(e -> {
            final TextInputDialog dialog = new TextInputDialog("Project " + Project.counter());
            dialog.setTitle("New Project");
            dialog.setHeaderText("Project Name");
            dialog.setContentText("Enter project name");
            dialog.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PAPERCLIP, "32px"));
            final Optional<String> opt = dialog.showAndWait();
            opt.ifPresent(name -> App.addProject(Project.create(name)));
        });

        importButton = GlyphsDude.createIconButton(FontAwesomeIcon.CLOUD_DOWNLOAD, "Import", "24px", "16px", ContentDisplay.TOP);

        exportButton = GlyphsDude.createIconButton(FontAwesomeIcon.CLOUD_UPLOAD, "Export", "24px", "16px", ContentDisplay.TOP);

        final HBox leftActions = new HBox();
        leftActions.setSpacing(5);
        leftActions.getChildren().addAll(newButton, importButton, exportButton, new Separator(Orientation.VERTICAL));

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.I_CURSOR, "Lying I's Metric Modeller", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        githubButton = GlyphsDude.createIconButton(FontAwesomeIcon.GITHUB, "GitHub", "24px", "16px", ContentDisplay.TOP);
        githubButton.setOnAction(e -> Utils.browse("https://github.com/joshvm/metric-modeller-combined"));

        final ContextMenu resourcesMenu = new ContextMenu();
        resourcesMenu.getItems().addAll(
                createMenuItem("Function Point Analysis", "https://cs.uwaterloo.ca/~apidduck/CS846/Seminars/abbas.pdf"),
                createMenuItem("Factor Influence", "http://waset.org/publications/5568/an-examination-of-the-factors-influencing-software-development-effort-"),
                createMenuItem("Simple Modeller", "http://groups.engin.umd.umich.edu/CIS/course.des/cis525/js/f00/artan/functionpoints.htm")
        );

        resourcesButton = GlyphsDude.createIconButton(FontAwesomeIcon.FILE, "Resources", "24px", "16px", ContentDisplay.TOP);
        resourcesButton.setOnAction(e -> resourcesMenu.show(resourcesButton, Side.BOTTOM, 0, 0));

        final HBox rightActions = new HBox();
        rightActions.setSpacing(5);
        rightActions.getChildren().addAll(githubButton, resourcesButton);

        setLeft(leftActions);
        setCenter(titleLabel);
        setRight(rightActions);
    }

    private MenuItem createMenuItem(final String title, final String url){
        final MenuItem item = new MenuItem(title);
        item.setOnAction(e -> Utils.browse(url));
        return item;
    }
}
