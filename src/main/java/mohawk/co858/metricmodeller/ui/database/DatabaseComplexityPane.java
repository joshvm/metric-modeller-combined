package mohawk.co858.metricmodeller.ui.database;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.database.DatabaseComplexity;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.team.Team;

import java.text.NumberFormat;

public class DatabaseComplexityPane extends BorderPane {

    private final Project project;

    private ComboBox<DatabaseComplexity.DatabaseComplexityFactor> dbComplexityBox;

    private final TextField dbComplexityFactor;

    public DatabaseComplexityPane(final Project project) {
        this.project = project;

        final Font bigFont = Font.font("Verdana", FontWeight.NORMAL, 20);

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);

        final Label dbComplexityComboLabel = new Label("Select database complexity/size:");
        dbComplexityComboLabel.setFont(bigFont);

        dbComplexityBox = new ComboBox<>();
        dbComplexityBox.getItems().addAll(DatabaseComplexity.DatabaseComplexityFactor.VALUES);
        dbComplexityBox.getSelectionModel().select(project.dbComplexity().dbComplexityFactor().get());
        project.dbComplexity().dbComplexityFactor().bind(dbComplexityBox.getSelectionModel().selectedItemProperty());
        dbComplexityBox.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> update()
        );
        dbComplexityBox.setDisable(false);




        final Label databaseComplexitFactor = new Label("Database Complexity Factor:");
        databaseComplexitFactor.setFont(bigFont);

        dbComplexityFactor = new TextField("1");
        dbComplexityFactor.setFont(bigFont);
        dbComplexityFactor.setEditable(false);


        final GridPane fields = new GridPane();
        fields.setHgap(5);
        fields.setVgap(5);
        fields.addRow(1, dbComplexityComboLabel, dbComplexityBox);

        fields.addRow(11, databaseComplexitFactor, dbComplexityFactor);

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.DATABASE, "Database Size/Complexity", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        final BorderPane top = new BorderPane();
        top.setPadding(new Insets(5, 0, 5, 0));
        top.setCenter(titleLabel);
        top.setBottom(actionsPane);

        setTop(top);
        setCenter(fields);

        dbComplexityBox.getSelectionModel().select(DatabaseComplexity.DatabaseComplexityFactor.MIDWAY);

    }

    public void update() {
        double dataBaseFactor = project.dbComplexity().dbComplexityFactor().getValue().value();
        dbComplexityFactor.setText(String.format("%1.1f", dataBaseFactor));
    }
}

