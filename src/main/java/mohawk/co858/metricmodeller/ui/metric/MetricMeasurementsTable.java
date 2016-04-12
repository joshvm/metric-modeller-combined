package mohawk.co858.metricmodeller.ui.metric;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.util.converter.IntegerStringConverter;
import mohawk.co858.metricmodeller.core.metric.MetricMeasurements;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import mohawk.co858.metricmodeller.core.weighting.Weightings;

public class MetricMeasurementsTable extends BorderPane {

    private final Project project;

    private final Button addButton;
    private final Button removeButton;
    private final Button clearButton;

    private final TableColumn<MetricMeasurements.Entry, String> titleColumn;
    private final TableColumn<MetricMeasurements.Entry, Integer> countColumn;
    private final TableColumn<MetricMeasurements.Entry, Weighting> weightingColumn;

    private TableView<MetricMeasurements.Entry> table;

    public MetricMeasurementsTable(final Project project){
        this.project = project;

        addButton = GlyphsDude.createIconButton(FontAwesomeIcon.PLUS, "Add", "24px", "16px", ContentDisplay.LEFT);
        addButton.setOnAction(e -> {
            final MetricMeasurementSelectionDialog metricSelection = new MetricMeasurementSelectionDialog(project);
            final Optional<MetricMeasurements.Entry> opt = metricSelection.showAndWait();
            if(!opt.isPresent())
                return;
            final MetricMeasurements.Entry entry = opt.get();
            entry.count().addListener(
                    (ob, o, n) -> System.out.println("new value: " + n)
            );
            project.metricMeasurements().add(entry);
            table.getItems().add(entry);
        });

        removeButton = GlyphsDude.createIconButton(FontAwesomeIcon.MINUS, "Remove", "24px", "16px", ContentDisplay.LEFT);
        removeButton.setDisable(true);
        removeButton.setOnAction(e -> {
            final MetricMeasurements.Entry selected = table.getSelectionModel().getSelectedItem();
            if(selected == null)
                return;
            if(project.metricMeasurements().remove(selected))
                table.getItems().remove(selected);
        });

        clearButton = GlyphsDude.createIconButton(FontAwesomeIcon.TRASH, "Clear", "24px", "16px", ContentDisplay.LEFT);
        clearButton.setDisable(true);
        clearButton.setOnAction(e -> {
            project.metricMeasurements().clear();
            table.getItems().clear();
        });

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);
        actionsPane.getChildren().addAll(addButton, removeButton, clearButton);

        titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().metric().title()));
        titleColumn.setCellFactory(c -> new TableCell<MetricMeasurements.Entry, String>(){
            @Override
            protected void updateItem(final String title, final boolean empty){
                super.updateItem(title, empty);

                if(title == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    return;
                }

                setText(title);
                setAlignment(Pos.CENTER);
                setTextAlignment(TextAlignment.CENTER);
            }
        });

        countColumn = new TableColumn<>("Count");
        countColumn.setCellValueFactory(c -> c.getValue().count());
        countColumn.setCellFactory(c -> new TextFieldTableCell<MetricMeasurements.Entry, Integer>(){

            {
                setConverter(new IntegerStringConverter() {
                    @Override
                    public Integer fromString(final String value){
                        try{
                            final Integer i = super.fromString(value);
                            if(i == null || i < 1)
                                throw new Exception();
                            return i;
                        }catch(Exception ex){
                            return 1;
                        }
                    }
                });
            }

            @Override
            public void updateItem(final Integer item, final boolean empty){
                super.updateItem(item, empty);

                if(item == null){
                    setAlignment(null);
                    setTextAlignment(null);
                    return;
                }

                setAlignment(Pos.CENTER);
                setTextAlignment(TextAlignment.CENTER);
            }
        });

        weightingColumn = new TableColumn<>("Weighting");
        weightingColumn.setEditable(true);
        weightingColumn.setCellValueFactory(c -> c.getValue().weighting());
        weightingColumn.setCellFactory(c -> new ComboBoxTableCell<MetricMeasurements.Entry, Weighting>(){

            {
                getItems().addAll(Weightings.values());
            }

            @Override
            public void updateItem(final Weighting weighting, final boolean empty){
                super.updateItem(weighting, empty);

                if(weighting == null){
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
        table.setPlaceholder(new Label("Click the Add button to add metrics!"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(titleColumn, countColumn, weightingColumn);
        table.getItems().addAll(project.metricMeasurements().values());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> removeButton.setDisable(n == null)
        );
        table.getItems().addListener(
                (ListChangeListener<MetricMeasurements.Entry>) c -> clearButton.setDisable(table.getItems().isEmpty())
        );

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.BAR_CHART, "Parameters", "32px", "24px", ContentDisplay.LEFT);
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
