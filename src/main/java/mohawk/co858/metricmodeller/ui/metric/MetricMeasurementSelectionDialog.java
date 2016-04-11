package mohawk.co858.metricmodeller.ui.metric;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.metric.Metric;
import mohawk.co858.metricmodeller.core.metric.MetricMeasurements;
import mohawk.co858.metricmodeller.core.metric.Metrics;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import mohawk.co858.metricmodeller.core.weighting.Weightings;

public class MetricMeasurementSelectionDialog extends Dialog<MetricMeasurements.Entry> {

    private final Project project;

    private ListView<Metric> list;

    private TextField titleBox;
    private TextField countBox;
    private ComboBox<Weighting> weightingBox;

    private final ButtonType okButton;
    private final ButtonType cancelButton;

    public MetricMeasurementSelectionDialog(final Project project){
        this.project = project;

        list = new ListView<>();
        list.getItems().addAll(Metrics.values());
        project.metricMeasurements().keys().forEach(list.getItems()::remove);
        BorderPane.setMargin(list, new Insets(5, 0, 5, 0));
        list.setCellFactory(callback -> new ListCell<Metric>(){
            @Override
            protected void updateItem(final Metric metric, final boolean empty){
                super.updateItem(metric, empty);

                if(metric == null){
                    setText(null);
                    return;
                }

                setText(metric.title());
            }
        });
        list.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> {
                    if(n != null){
                        titleBox.setText(n.title());
                    }else{
                        titleBox.setText(null);
                    }
                }
        );

        final Label titleLabel = new Label("Metric:");
        titleLabel.setAlignment(Pos.CENTER_RIGHT);
        titleLabel.setTextAlignment(TextAlignment.RIGHT);

        titleBox = new TextField();
        titleBox.setEditable(false);

        final Label countLabel = new Label("Count:");
        countLabel.setAlignment(Pos.CENTER_RIGHT);
        countLabel.setTextAlignment(TextAlignment.RIGHT);

        countBox = new TextField("1");

        final Label weightingLabel = new Label("Weighting:");
        weightingLabel.setAlignment(Pos.CENTER_RIGHT);
        weightingLabel.setTextAlignment(TextAlignment.RIGHT);

        weightingBox = new ComboBox<>();
        weightingBox.getItems().addAll(Weightings.values());

        final GridPane infoPanel = new GridPane();
        infoPanel.setHgap(5);
        infoPanel.setVgap(5);
        infoPanel.addRow(0, titleLabel, titleBox);
        infoPanel.addRow(1, countLabel, countBox);
        infoPanel.addRow(2, weightingLabel, weightingBox);

        final BorderPane content = new BorderPane();
        BorderPane.setMargin(content, new Insets(10));
        content.setCenter(list);
        content.setBottom(infoPanel);

        okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        setTitle("Metric Picker");
        setHeaderText("Select a Metric");
        setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.BAR_CHART, "32px"));
        setResultConverter(callback -> {
            if(callback.getButtonData() != ButtonBar.ButtonData.OK_DONE)
                return null;
            final Metric metric = list.getSelectionModel().getSelectedItem();
            if(metric == null)
                return null;
            final String countText = countBox.getText().trim();
            if(!countText.matches("\\d{1,8}"))
                return null;
            final int count = Integer.parseInt(countText);
            final Weighting weighting = weightingBox.getValue();
            if(weighting == null)
                return null;
            return new MetricMeasurements.Entry(metric, count, weighting);
        });

        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
    }
}
