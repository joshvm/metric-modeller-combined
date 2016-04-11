package mohawk.co858.metricmodeller.ui;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Optional;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.project.Project;

public class ToolBar extends BorderPane {

    private final Button newButton;
    private final Button importButton;
    private final Button exportButton;

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

        final HBox actions = new HBox();
        actions.setSpacing(5);
        actions.getChildren().addAll(newButton, importButton, exportButton, new Separator(Orientation.VERTICAL));

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.I_CURSOR, "Lying I's Metric Modeller", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        setLeft(actions);
        setCenter(titleLabel);
    }
}
