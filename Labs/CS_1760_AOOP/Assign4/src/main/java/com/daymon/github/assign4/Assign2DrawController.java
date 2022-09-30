package com.daymon.github.assign4;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Assign2DrawController {

    @FXML
    private Label thirdLabel;

    @FXML
    private Label firstLabel;

    @FXML
    private Label secondLabel;

    @FXML
    private Label fourthLabel;

    @FXML
    private Pane display;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton circleRadio;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField firstField;

    @FXML
    private TextField forthField;

    @FXML
    private RadioButton lineRadio;

    @FXML
    private ToggleGroup radioButton;

    @FXML
    private RadioButton rectangleRadio;

    @FXML
    private TextField secondField;

    @FXML
    private TextField thirdField;

    private final List<TextField> fieldList = new ArrayList<>();
    private double WIDTH;
    private double HEIGHT;

    @FXML
    void onDrawPressed(ActionEvent event)
    {

        var displayChildren = display.getChildren();
        var rButton = (RadioButton) radioButton.getSelectedToggle();
        var selected = rButton.getText();
        var fieldsValidated = validateFields(selected);

        if (fieldsValidated.isEmpty()) return;

        switch (selected)
        {
            case "Line" -> {
                var line = new Line(
                        fieldsValidated.get(0),
                        fieldsValidated.get(1),
                        fieldsValidated.get(2),
                        fieldsValidated.get(3)
                );
                line.setStroke(comboBox.getValue().equals("Light") ? colorPicker.getValue().darker() : colorPicker.getValue());
                displayChildren.add(line);
            }

            case "Rectangle" -> {

                var rectangle = new Rectangle(fieldsValidated.get(0), fieldsValidated.get(1), fieldsValidated.get(2), fieldsValidated.get(3));
                rectangle.setFill(comboBox.getValue().equals("Empty") ? Color.TRANSPARENT : (comboBox.getValue().equals("Light") ? colorPicker.getValue().brighter() : colorPicker.getValue()));
                if ("Empty".equals(comboBox.getValue())) rectangle.setStroke(colorPicker.getValue()); else  rectangle.setFill(comboBox.getValue().equals("Light") ? colorPicker.getValue().brighter() : colorPicker.getValue());
                displayChildren.add(rectangle);

            }

            case "Circle" -> {
                var circle = new Circle(fieldsValidated.get(0), fieldsValidated.get(1), fieldsValidated.get(2), comboBox.getValue().equals("Empty") ? Color.TRANSPARENT : (comboBox.getValue().equals("Light") ? colorPicker.getValue().brighter() : colorPicker.getValue()));
                if ("Empty".equals(comboBox.getValue())) circle.setStroke(colorPicker.getValue()); else  circle.setFill(comboBox.getValue().equals("Light") ? colorPicker.getValue().brighter() : colorPicker.getValue());
                displayChildren.add(circle);
            }
        }
    }

    private List<Integer> validateFields(String field)
    {
        var subList = fieldList.subList(0, 3);
        var blankFields = field.equals("Circle") ?
                subList.stream().map(TextInputControl::getText).anyMatch(ele -> ele.equals(""))
                : fieldList.stream().map(TextInputControl::getText).anyMatch(ele -> ele.equals(""));


        if (blankFields)
        {
            showWarningAlert("Some of your fields are blank");
            return Collections.emptyList();
        }


        var numbers = field.equals("Circle") ?
                subList.stream()
                        .map(ele -> Integer.parseInt(ele.getText()))
                .toList()
                :
                fieldList.stream()
                .map(d -> Integer.parseInt(d.getText()))
                .toList();

        switch (field)
        {
            case "Circle" -> {
                if ((numbers.get(0) > WIDTH || numbers.get(1) > HEIGHT)
                        || (numbers.get(0) + numbers.get(2)  > WIDTH || numbers.get(1) + numbers.get(2)  > HEIGHT)
                        || (numbers.get(0) - numbers.get(2)  < 0 || numbers.get(1) - numbers.get(2)  < 0))
                {
                    showWarningAlert("Incorrect values");
                    return Collections.emptyList();
                }
            }

            case "Rectangle" -> {
                if ((numbers.get(0) > WIDTH || numbers.get(1) >  HEIGHT ) || (numbers.get(2)  > WIDTH || numbers.get(3) > HEIGHT))
                {
                    showWarningAlert("Incorrect values");
                    return Collections.emptyList();
                }
            }

            case "Line" -> {
                if ((numbers.get(0) > WIDTH || numbers.get(1) > WIDTH )|| (numbers.get(2) < HEIGHT || numbers.get(3) < HEIGHT))
                {
                    showWarningAlert("Those parameters would put the shape/line out of bounds of the screen!");
                    return Collections.emptyList();
                }
            }
        }

        if (comboBox.getValue() == null)
        {
            showWarningAlert("Combobox is empty");
            return Collections.emptyList();
        }

        display.getChildren().clear();
       return numbers;
    }

    @FXML
    void onRadioChanged(ActionEvent event)
    {
        var rButton  =  (RadioButton) radioButton.getSelectedToggle();
        var selected = rButton.getText();

        switch (selected)
        {
            case "Line" -> {
                forthField.setVisible(true);
                thirdLabel.setText("Second X Point");
                fourthLabel.setText("Second Y Point");

            }

            case "Rectangle" -> {
                forthField.setVisible(true);
                thirdLabel.setText("Width");
                fourthLabel.setText("Height");
            }

            case "Circle" -> {
                forthField.setVisible(false);
                thirdLabel.setText("Radius");
                fourthLabel.setText("");
            }

            default -> throw new IllegalStateException("%s is not a radio button I can handle".formatted(selected));
        }
    }

    @FXML
    void initialize()
    {
        HEIGHT = display.getPrefHeight();
        WIDTH = display.getPrefWidth();
        comboBox.getItems().addAll("Dark", "Light", "Empty");
        fieldList.addAll(List.of(firstField, secondField, thirdField, forthField));

        fieldList.forEach(field -> field.textProperty().addListener(((observable, oldValue, newValue) ->
        {
            if (!newValue.matches("C=(\\d+\\.\\d+)"))
                field.setText(newValue.replaceAll("[^C=(\\d+.)]", ""));

            var count = newValue.length() - newValue.replace(".", "").length();

            if (count >= 2) field.setText(oldValue);
        }
        )));
    }

    private void showWarningAlert(String text)
    {

        var s = "sddd".chars().allMatch(Character::isDigit);
        var alert = new Alert(Alert.AlertType.WARNING, text);
        alert.show();
    }


}
