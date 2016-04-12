package mohawk.co858.metricmodeller.ui.team;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.NumberFormat;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import mohawk.co858.metricmodeller.core.calc.Calculators;
import mohawk.co858.metricmodeller.core.project.Project;
import mohawk.co858.metricmodeller.core.team.Team;
import mohawk.co858.metricmodeller.ui.res.Res;

public class TeamPane extends BorderPane {

    private final Project project;

    private final ToggleButton enabledButton;
    private final Button clearButton;

    private ComboBox<Team.Coordination> coordinationBox;
    private ComboBox<Team.Leadership> leadershipBox;

    private final TextField locBox;
    private final TextField teamFactorBox;

    public TeamPane(final Project project){
        this.project = project;

        enabledButton = GlyphsDude.createIconToggleButton(FontAwesomeIcon.CLOSE, "Disabled", "24px", "16px", ContentDisplay.LEFT);
        enabledButton.selectedProperty().addListener(
                (ob, o, n) -> {
                    if(n){
                        enabledButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.CHECK, "24px"));
                        enabledButton.setText("Enabled");
                        coordinationBox.setDisable(false);
                        leadershipBox.setDisable(false);
                    }else{
                        enabledButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.CLOSE, "24px"));
                        enabledButton.setText("Disabled");
                        coordinationBox.setDisable(true);
                        leadershipBox.setDisable(true);
                    }

                    update();
                }
        );
        enabledButton.setSelected(project.team().enabled().get());
        project.team().enabled().bind(enabledButton.selectedProperty());

        clearButton = GlyphsDude.createIconButton(FontAwesomeIcon.TRASH, "Clear", "24px", "16px", ContentDisplay.LEFT);
        clearButton.setOnAction(e -> {
            coordinationBox.getSelectionModel().select(Team.Coordination.MEDIUM);
            leadershipBox.getSelectionModel().select(Team.Leadership.AVERAGE);
        });

        final HBox actionsPane = new HBox();
        setMargin(actionsPane, new Insets(5, 0, 5, 0));
        actionsPane.setAlignment(Pos.CENTER);
        actionsPane.setSpacing(3);
        actionsPane.getChildren().addAll(enabledButton, clearButton);

        final Font bigFont = Font.font("Verdana", FontWeight.NORMAL, 16);

        final Label coordinationLabel = new Label("Team Coordination:");
        coordinationLabel.setFont(bigFont);
        coordinationLabel.textAlignmentProperty().set(TextAlignment.RIGHT);

        coordinationBox = new ComboBox<>();
        coordinationBox.setDisable(!project.team().enabled().get());
        coordinationBox.getItems().addAll(Team.Coordination.VALUES);
        coordinationBox.getSelectionModel().select(project.team().coordination().get());
        project.team().coordination().bind(coordinationBox.getSelectionModel().selectedItemProperty());
        coordinationBox.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> update()
        );

        final Label leadershipLabel = new Label("Leadership:");
        leadershipLabel.setFont(bigFont);
        leadershipLabel.textAlignmentProperty().set(TextAlignment.RIGHT);

        leadershipBox = new ComboBox<>();
        leadershipBox.setDisable(!project.team().enabled().get());
        leadershipBox.getItems().addAll(Team.Leadership.VALUES);
        leadershipBox.getSelectionModel().select(project.team().leadership().get());
        project.team().leadership().bind(leadershipBox.getSelectionModel().selectedItemProperty());
        leadershipBox.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, n) -> update()
        );



        final Label locLabel = new Label("Lines Of Communication");
        locLabel.setFont(bigFont);


        locBox = new TextField("0");
        locBox.setFont(bigFont);
        locBox.setEditable(false);

        final Label teamFactorLabel = new Label("Team Factor");
        teamFactorLabel.setFont(bigFont);



        teamFactorBox = new TextField("0");
        teamFactorBox.setFont(bigFont);
        teamFactorBox.setEditable(false);

        ImageView pic1 = new ImageView(Res.FORMULAS[0]);
        ImageView pic2 = new ImageView(Res.FORMULAS[1]);



        final HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(5, 0, 5, 0));
        hbox1.getChildren().add(locLabel);
        hbox1.getChildren().add(pic1);
        hbox1.getChildren().add(locBox);
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.spacingProperty().set(5);


        final HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(5, 0, 5, 0));
        hbox2.getChildren().add(teamFactorLabel);
        hbox2.getChildren().add(pic2);
        hbox2.getChildren().add(teamFactorBox);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.spacingProperty().set(5);


        final GridPane fields = new GridPane();
        fields.setMinWidth(800D);
        fields.setHgap(5);
        fields.setVgap(5);
        fields.addRow(1, coordinationLabel, coordinationBox);
        fields.addRow(2, leadershipLabel, leadershipBox);
        fields.add(new Separator(Orientation.HORIZONTAL), 0, 3, 2, 1);
        fields.add(hbox1, 0, 5, 5, 1);
        fields.add(hbox2, 0, 7, 5, 1);




        final Label titleLabel = GlyphsDude.createIconLabel(FontAwesomeIcon.USERS, "Team", "32px", "24px", ContentDisplay.LEFT);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setAlignment(Pos.CENTER);

        final BorderPane top = new BorderPane();
        top.setPadding(new Insets(5, 0, 5, 0));
        top.setCenter(titleLabel);
        top.setBottom(actionsPane);

        setTop(top);
        setCenter(fields);

        coordinationBox.getSelectionModel().select(Team.Coordination.MEDIUM);
        leadershipBox.getSelectionModel().select(Team.Leadership.AVERAGE);
    }

    public void update(){
        if(project.team().enabled().get()){
            final int people = project.expertiseCounts().values().stream()
                    .mapToInt(e -> e.count().get())
                    .sum();
            final double linesOfCommunication = people * (people - 1) / 2;
            locBox.setText(NumberFormat.getInstance().format(linesOfCommunication));
            teamFactorBox.setText(String.format("%1.3f", Calculators.teamFactorCalculator().calculate(project)));
        }else{
            locBox.setText("0");
            teamFactorBox.setText("0");
        }
    }
}
