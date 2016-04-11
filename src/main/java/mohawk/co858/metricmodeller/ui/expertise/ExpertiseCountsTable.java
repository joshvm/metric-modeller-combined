package mohawk.co858.metricmodeller.ui.expertise;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import mohawk.co858.metricmodeller.core.expertise.Expertise;
import mohawk.co858.metricmodeller.core.project.Project;

public class ExpertiseCountsTable extends BorderPane {

    private final Project project;

    private final Button clearButton;

    private final TableColumn<Expertise, Expertise.Level> levelColumn;
    private final TableColumn<Expertise, Double> costColumn;
    private final TableColumn<Expertise, Integer> countColumn;
    private final TableView<Expertise> table;

    public ExpertiseCountsTable(final Project project){
        this.project = project;

        clearButton = GlyphsDude.createIconButton(FontAwesomeIcon.TRASH, "Clear", "24px", "16px", ContentDisplay.LEFT);
        clearButton.setOnAction(e -> project.expertiseCounts().clear());

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);
        actionsPane.getChildren().addAll(clearButton);

        levelColumn = new TableColumn<>("Expertise Level");
        levelColumn.setCellValueFactory(c -> c.getValue().level());
        levelColumn.setCellFactory(c -> new TableCell<Expertise, Expertise.Level>(){
            @Override
            protected void updateItem(final Expertise.Level level, final boolean empty){
                super.updateItem(level, empty);

                if(level == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    return;
                }

                setText(level.title());
                setTextAlignment(TextAlignment.CENTER);
                setAlignment(Pos.CENTER);
            }
        });

        costColumn = new TableColumn<>("Cost");
        costColumn.setEditable(true);
        costColumn.setCellValueFactory(c -> c.getValue().cost());
        costColumn.setCellFactory(c -> new TextFieldTableCell<Expertise, Double>(){

            {
                setConverter(new DoubleStringConverter() {
                    @Override
                    public Double fromString(final String value){
                        try{
                            final Double d = super.fromString(value);
                            if(d == null || d < 0)
                                throw new Exception();
                            return d;
                        }catch(Exception ex){
                            return 1d;
                        }
                    }
                });
            }

            @Override
            public void updateItem(final Double cost, final boolean empty){
                super.updateItem(cost, empty);

                if(cost == null){
                    setText(null);
                    setAlignment(null);
                    setTextAlignment(null);
                    return;
                }

                setText(DecimalFormat.getCurrencyInstance().format(cost));
                setAlignment(Pos.CENTER);
                setTextAlignment(TextAlignment.CENTER);
            }
        });


        countColumn = new TableColumn<>("Count");
        countColumn.setEditable(true);
        countColumn.setCellValueFactory(c -> c.getValue().count());
        countColumn.setCellFactory(c -> new TextFieldTableCell<Expertise, Integer>(){

            {
                setConverter(new IntegerStringConverter() {
                    @Override
                    public Integer fromString(final String value){
                        try{
                            final Integer i = super.fromString(value);
                            if(i == null || i < 0)
                                throw new Exception();
                            return i;
                        }catch(Exception ex){
                            return 0;
                        }
                    }
                });
            }

            @Override
            public void updateItem(final Integer count, final boolean empty){
                super.updateItem(count, empty);

                if(count == null){
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
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(levelColumn, costColumn, countColumn);
        table.getItems().addAll(project.expertiseCounts().values());

        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.USER, "People", "32px", "24px", ContentDisplay.LEFT);
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
