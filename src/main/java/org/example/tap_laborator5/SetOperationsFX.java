package org.example.tap_laborator5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SetOperationsFX extends Application {

    private final Set<Integer> set1 = new HashSet<>();
    private final Set<Integer> set2 = new HashSet<>();
    private TextArea set1Input;
    private TextArea set2Input;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Operații cu Seturi");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        set1Input = new TextArea();
        set2Input = new TextArea();
        Button concatenateButton = new Button("Concatenare");
        Button intersectButton = new Button("Intersecție");
        resultLabel = new Label();

        GridPane.setConstraints(set1Input, 0, 0, 2, 1);
        GridPane.setConstraints(set2Input, 0, 1, 2, 1);
        GridPane.setConstraints(concatenateButton, 0, 2, 1, 1);
        GridPane.setConstraints(intersectButton, 1, 2, 1, 1);
        GridPane.setConstraints(resultLabel, 0, 3, 2, 1);


        grid.getChildren().addAll(set1Input, set2Input, concatenateButton, intersectButton, resultLabel);

        concatenateButton.setOnAction(e -> concatenateSets());
        intersectButton.setOnAction(e -> intersectSets());

        primaryStage.setScene(new Scene(grid, 300, 200));
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void concatenateSets() {
        parseInputSets();
        Set<Integer> concatenatedSet = SetOperations.concatenateSets(set1, set2);
        resultLabel.setText("Rezultat concatenare: " + concatenatedSet);
    }

    private void intersectSets() {
        parseInputSets();
        Set<Integer> intersectionSet = SetOperations.intersectSets(set1, set2);
        resultLabel.setText("Rezultat intersecție: " + intersectionSet);
    }

    private void check(String num) {
        int number = Integer.parseInt(num.trim());
        if (number < 0) {
            throw new NegativeNumberException("Valoare negativă: " + number);
        }
        set1.add(number);
    }

    private void parseInputSets() {
        set1.clear();
        set2.clear();

        try {
            if (set1Input.getText().isEmpty() || set2Input.getText().isEmpty()) {
                throw new IllegalArgumentException("Date lipsă. Introduceți date valide în ambele câmpuri.");
            }

            String[] set1Numbers = set1Input.getText().split("\\s+");
            String[] set2Numbers = set2Input.getText().split("\\s+");

            for (String num : set1Numbers) {
                try {
                    int number = Integer.parseInt(num.trim());
                    if (number < 0) {
                        throw new NegativeNumberException("Valoare negativă: " + number);
                    }
                    set1.add(number);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Valoare invalidă: " + num.trim());
                } finally {
                    showAlert("Finally", "Se indeplineste");
                }
            }

            for (String num : set2Numbers) {
                try {
                    int number = Integer.parseInt(num.trim());
                    if (number < 0) {
                        throw new NegativeNumberException("Valoare negativă: " + number);
                    }
                    set2.add(number);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Valoare invalidă: " + num.trim());
                }
            }
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        } catch (NegativeNumberException e) {
            showAlert("Error", e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String message) {
        super(message);
    }
}
