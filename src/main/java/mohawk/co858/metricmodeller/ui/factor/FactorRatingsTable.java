package mohawk.co858.metricmodeller.ui.factor;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.factor.Factor;
import mohawk.co858.metricmodeller.core.factor.FactorRatings;
import mohawk.co858.metricmodeller.core.factor.Rating;
import mohawk.co858.metricmodeller.core.project.Project;

public class FactorRatingsTable extends BorderPane {

    private final Project project;

    private final Button clearButton;

    private final TableColumn<FactorRatings.Entry, Factor> factorColumn;
    private final TableColumn<FactorRatings.Entry, Rating> ratingColumn;
    private TableView<FactorRatings.Entry> table;

    public FactorRatingsTable(final Project project){
        this.project = project;

        clearButton = GlyphsDude.createIconButton(FontAwesomeIcon.TRASH, "Clear", "24px", "16px", ContentDisplay.LEFT);
        clearButton.setOnAction(e -> project.factorRatings().clear());

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);
        actionsPane.getChildren().addAll(clearButton);

        factorColumn = new TableColumn<>("Factor");
        factorColumn.setCellValueFactory(c -> c.getValue().factor());
        factorColumn.setCellFactory(c -> new TableCell<FactorRatings.Entry, Factor>() {
            @Override
            protected void updateItem(final Factor factor, final boolean empty){
                super.updateItem(factor, empty);

                if(factor == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    setTooltip(null);
                    return;
                }

                setText(factor.title());
                setTextAlignment(TextAlignment.CENTER);
                setAlignment(Pos.CENTER);
                setTooltip(new Tooltip(factor.title()));
            }
        });

        ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setMinWidth(200);
        ratingColumn.setEditable(true);
        ratingColumn.setCellValueFactory(c -> c.getValue().rating());
        ratingColumn.setCellFactory(c -> new ComboBoxTableCell<FactorRatings.Entry, Rating>() {

            {
                getItems().addAll(Rating.VALUES);
            }

            @Override
            public void updateItem(final Rating rating, final boolean empty){
                super.updateItem(rating, empty);

                if(rating == null){
                    setAlignment(null);
                    setTextAlignment(null);
                    return;
                }

                setAlignment(Pos.CENTER);
                setTextAlignment(TextAlignment.CENTER);
            }
        });

        table = new TableView<>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(factorColumn, ratingColumn);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getItems().addAll(project.factorRatings().values());

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.QUESTION, "Factors", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        final BorderPane top = new BorderPane();
        top.setPadding(new Insets(5, 0, 5, 0));
        top.setCenter(titleLabel);
        top.setBottom(actionsPane);

        setTop(top);
        setCenter(table);
    }
}
