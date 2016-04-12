package mohawk.co858.metricmodeller.ui.output;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.calc.OutputParam;
import mohawk.co858.metricmodeller.core.project.Project;

public class OutputPane extends BorderPane {

    private static class Entry {

        private final SimpleObjectProperty<OutputParam> output;
        private final SimpleObjectProperty<Double> value;

        private Entry(final OutputParam output, final double value){
            this.output = new SimpleObjectProperty<>(output);
            this.value = new SimpleObjectProperty<>(value);
        }
    }

    private final Project project;

    private final TableColumn<Entry, OutputParam> outputColumn;
    private final TableColumn<Entry, Double> valueColumn;
    private final Map<OutputParam, Entry> model;
    private final TableView<Entry> table;

    public OutputPane(final Project project){
        this.project = project;

        outputColumn = new TableColumn<>("Output");
        outputColumn.setCellValueFactory(c -> c.getValue().output);
        outputColumn.setCellFactory(c -> new TableCell<Entry, OutputParam>() {
            @Override
            protected void updateItem(final OutputParam output, final boolean empty){
                super.updateItem(output, empty);

                if(output == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    return;
                }

                setText(output.title());
                setTextAlignment(TextAlignment.CENTER);
                setAlignment(Pos.CENTER);
            }
        });

        valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(c -> c.getValue().value);
        valueColumn.setCellFactory(c -> new TableCell<Entry, Double>() {
            @Override
            protected void updateItem(final Double value, final boolean empty){
                super.updateItem(value, empty);

                if(value == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    return;
                }

                final Entry entry = table.getItems().get(getIndex());

                final String num = entry.output.get().numberFormat().format(value);

                setText(String.format(entry.output.get().format(), num));
                setTextAlignment(TextAlignment.CENTER);
                setAlignment(Pos.CENTER);
            }
        });

        model = new HashMap<>();

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(outputColumn, valueColumn);

        for(final OutputParam output : OutputParam.VALUES){
            final Entry e = new Entry(output, 0);
            model.put(output, e);
            table.getItems().add(e);
        }

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.CALCULATOR, "Output", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        final BorderPane top = new BorderPane();
        top.setPadding(new Insets(5, 0, 5, 0));
        top.setCenter(titleLabel);

        setTop(top);
        setCenter(table);
    }

    public void update(){
        for(final OutputParam output : OutputParam.VALUES)
            model.get(output).value.set(Calculators.forOutput(output).calculate(project));
    }
}
