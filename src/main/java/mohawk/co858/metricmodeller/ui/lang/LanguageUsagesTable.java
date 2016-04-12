package mohawk.co858.metricmodeller.ui.lang;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.Optional;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.lang.Language;
import mohawk.co858.metricmodeller.core.lang.LanguageUsages;
import mohawk.co858.metricmodeller.core.lang.Usage;
import mohawk.co858.metricmodeller.core.project.Project;

public class LanguageUsagesTable extends BorderPane {

    private final Project project;

    private final Button addButton;
    private final Button removeButton;
    private final Button clearButton;

    private final TableColumn<LanguageUsages.Entry, Language> langColumn;
    private final TableColumn<LanguageUsages.Entry, Usage> usageColumn;
    private TableView<LanguageUsages.Entry> table;

    public LanguageUsagesTable(final Project project){
        this.project = project;

        addButton = GlyphsDude.createIconButton(FontAwesomeIcon.PLUS, "Add", "24px", "16px", ContentDisplay.LEFT);
        addButton.setOnAction(e -> {
            final LanguageUsageSelectionDialog langSelection = new LanguageUsageSelectionDialog(project);
            final Optional<LanguageUsages.Entry> opt = langSelection.showAndWait();
            if(!opt.isPresent())
                return;
            final LanguageUsages.Entry entry = opt.get();
            project.languageUsages().add(entry);
            table.getItems().add(entry);
        });

        removeButton = GlyphsDude.createIconButton(FontAwesomeIcon.MINUS, "Remove", "24px", "16px", ContentDisplay.LEFT);
        removeButton.setDisable(true);
        removeButton.setOnAction(e -> {
            final LanguageUsages.Entry selected = table.getSelectionModel().getSelectedItem();
            if(selected == null)
                return;
            if(project.languageUsages().remove(selected))
                table.getItems().remove(selected);
        });

        clearButton = GlyphsDude.createIconButton(FontAwesomeIcon.TRASH, "Clear", "24px", "16px", ContentDisplay.LEFT);
        clearButton.setDisable(true);
        clearButton.setOnAction(e -> {
            project.languageUsages().clear();
            table.getItems().clear();
        });

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);
        actionsPane.getChildren().addAll(addButton, removeButton, clearButton);

        langColumn = new TableColumn<>("Language");
        langColumn.setCellValueFactory(p -> p.getValue().language());
        langColumn.setCellFactory(p -> new TableCell<LanguageUsages.Entry, Language>() {
            @Override
            protected void updateItem(final Language lang, final boolean empty){
                super.updateItem(lang, empty);

                if(lang == null){
                    setText(null);
                    setTextAlignment(null);
                    setAlignment(null);
                    setTooltip(null);
                    return;
                }

                setText(lang.name());
                setAlignment(Pos.CENTER);
                setTextAlignment(TextAlignment.CENTER);
                setTooltip(new Tooltip(String.format("%s | Level: %1.2f | Average: %1.2f", lang.name(), lang.productivityLevel(), lang.locPerFp())));
            }
        });

        usageColumn = new TableColumn<>("Usage");
        usageColumn.setEditable(true);
        usageColumn.setCellValueFactory(p -> p.getValue().usage());
        usageColumn.setCellFactory(p -> new ComboBoxTableCell<LanguageUsages.Entry, Usage>(Usage.VALUES) {
            @Override
            public void updateItem(final Usage item, final boolean empty){
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

        table = new TableView<>();
        table.setEditable(true);
        table.getColumns().addAll(langColumn, usageColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Click the Add button to add languages!"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> removeButton.setDisable(n == null)
        );
        table.getItems().addListener(
                (ListChangeListener<LanguageUsages.Entry>) c -> clearButton.setDisable(table.getItems().isEmpty())
        );


        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.CODE, "Languages", "32px", "24px", ContentDisplay.LEFT);
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
