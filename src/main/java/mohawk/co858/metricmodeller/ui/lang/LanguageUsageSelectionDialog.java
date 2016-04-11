package mohawk.co858.metricmodeller.ui.lang;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.lang.Language;
import mohawk.co858.metricmodeller.core.lang.LanguageUsages;
import mohawk.co858.metricmodeller.core.lang.Languages;
import mohawk.co858.metricmodeller.core.lang.Usage;
import mohawk.co858.metricmodeller.core.project.Project;

public class LanguageUsageSelectionDialog extends Dialog<LanguageUsages.Entry> {

    private final Project project;

    private final TextField searchBox;

    private FilteredList<Language> model;
    private ListView<Language> list;

    private TextField nameBox;
    private TextField levelBox;
    private TextField averageBox;

    private final ComboBox<Usage> usageBox;

    private final ButtonType okButton;
    private final ButtonType cancelButton;

    public LanguageUsageSelectionDialog(final Project project){
        this.project = project;

        final Predicate<Language> validPredicate = lang -> !project.languageUsages().contains(lang);

        searchBox = new TextField();
        searchBox.setPromptText("Search for a language...");
        searchBox.textProperty().addListener(
                (ob, o, n) -> {
                    final String search = searchBox.getText();
                    if(search != null && !search.isEmpty()){
                        model.setPredicate(validPredicate.and(l -> l.name().toLowerCase().startsWith(search.toLowerCase())));
                        if(!list.getItems().isEmpty())
                            list.getSelectionModel().select(list.getItems().get(0));
                    }else{
                        list.getSelectionModel().clearSelection();
                        model.setPredicate(validPredicate);
                    }
                }
        );

        model = new FilteredList<>(FXCollections.observableArrayList(Languages.values()), validPredicate);

        list = new ListView<>(model);
        BorderPane.setMargin(list, new Insets(5, 0, 5, 0));
        list.setCellFactory(callback -> new ListCell<Language>(){
            @Override
            protected void updateItem(final Language lang, final boolean empty){
                super.updateItem(lang, empty);

                if(lang == null){
                    setText(null);
                    setTooltip(null);
                    return;
                }

                setText(lang.name());
                setTooltip(new Tooltip(String.format("%s | Level %1.2f | Average %1.2f", lang.name(), lang.productivityLevel(), lang.locPerFp())));
            }
        });
        list.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> {
                    if(n != null){
                        nameBox.setText(n.name());
                        levelBox.setText(String.format("%1.2f", n.productivityLevel()));
                        averageBox.setText(String.format("%1.2f", n.locPerFp()));
                    }else{
                        nameBox.setText(null);
                        levelBox.setText(null);
                        averageBox.setText(null);
                    }
                }
        );

        final Label nameLabel = new Label("Language:");
        nameLabel.setAlignment(Pos.CENTER_RIGHT);
        nameLabel.setTextAlignment(TextAlignment.RIGHT);

        nameBox = new TextField();
        nameBox.setEditable(false);

        final Label levelLabel = new Label("Level:");
        levelLabel.setAlignment(Pos.CENTER_RIGHT);
        levelLabel.setTextAlignment(TextAlignment.RIGHT);

        levelBox = new TextField();
        levelBox.setEditable(false);

        final Label averageLabel = new Label("Average:");
        averageLabel.setAlignment(Pos.CENTER_RIGHT);
        averageLabel.setTextAlignment(TextAlignment.RIGHT);

        averageBox = new TextField();
        averageBox.setEditable(false);

        final Label usageLabel = new Label("Usage:");
        usageLabel.setAlignment(Pos.CENTER_RIGHT);
        usageLabel.setTextAlignment(TextAlignment.RIGHT);

        usageBox = new ComboBox<>();
        usageBox.getItems().addAll(Usage.VALUES);
        usageBox.getSelectionModel().select(Usage.MEDIUM);

        final GridPane infoPanel = new GridPane();
        infoPanel.setHgap(5);
        infoPanel.setVgap(5);
        infoPanel.addRow(0, nameLabel, nameBox);
        infoPanel.addRow(1, levelLabel, levelBox);
        infoPanel.addRow(2, averageLabel, averageBox);
        infoPanel.addRow(3, usageLabel, usageBox);

        final BorderPane content = new BorderPane();
        BorderPane.setMargin(content, new Insets(10));
        content.setTop(searchBox);
        content.setCenter(list);
        content.setBottom(infoPanel);

        okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        setTitle("Language Picker");
        setHeaderText("Select a Language");
        setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.CODE, "32px"));
        setResultConverter(callback -> {
            if(callback.getButtonData() != ButtonBar.ButtonData.OK_DONE)
                return null;
            final Language lang = list.getSelectionModel().getSelectedItem();
            if(lang == null)
                return null;
            final Usage usage = usageBox.getSelectionModel().getSelectedItem();
            return new LanguageUsages.Entry(lang, usage);
        });

        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
    }
}
