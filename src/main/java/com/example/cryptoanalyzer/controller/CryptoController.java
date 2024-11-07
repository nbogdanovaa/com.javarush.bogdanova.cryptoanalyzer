package com.example.cryptoanalyzer.controller;

import com.example.cryptoanalyzer.services.BruteForce;
import com.example.cryptoanalyzer.services.EncryptDecrypt;
import com.example.cryptoanalyzer.services.FileManager;
import com.example.cryptoanalyzer.services.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class CryptoController {
    @FXML
    private TextField encryptKeyField;
    @FXML
    private TextField decryptKeyField;
    @FXML
    private Button actionEncrypt;
    @FXML
    private Button actionDecrypt;
    @FXML
    private Button actionBruteForce;
    @FXML
    private Tab bruteForceTab;
    private File fileRead;
    private File fileWrite;

    @FXML
    protected void onEncryptButtonClick() {
        try {
            String text = FileManager.readFile(fileRead.getPath());
            FileManager.writeFile(EncryptDecrypt.encrypt(text, Integer.parseInt(encryptKeyField.getText())), fileWrite.getPath());
        } catch (NumberFormatException e) {
            showErrorDialog("Ошибка конвертации", "Ошибка конвертации: " + e.getMessage());
        } catch (IOException e) {
            showErrorDialog("Ошибка чтения/записи файла", "Ошибка чтения/записи файла: " + e.getMessage());
        }

        showSuccessMessage("Success!");
        actionEncrypt.setDisable(true);
        encryptKeyField.clear();
    }

    @FXML
    protected void onDecryptButtonClick() {
        try {
            String text = FileManager.readFile(fileRead.getPath());
            FileManager.writeFile(EncryptDecrypt.decrypt(text, Integer.parseInt(decryptKeyField.getText())), fileWrite.getPath());
        } catch (NumberFormatException e) {
            showErrorDialog("Ошибка конвертации", "Ошибка конвертации: " + e.getMessage());
        } catch (IOException e) {
            showErrorDialog("Ошибка чтения/записи файла", "Ошибка чтения/записи файла: " + e.getMessage());
        }

        showSuccessMessage("Success!");
        actionDecrypt.setDisable(true);
        decryptKeyField.clear();
    }

    @FXML
    protected void onBruteForceButtonClick() {
        try {
            String text = FileManager.readFile(fileRead.getPath());
            FileManager.writeFile(BruteForce.decryptByBruteForce(text, EncryptDecrypt.getALPHABET()), fileWrite.getPath());
        } catch (IOException e) {
            showErrorDialog("Ошибка чтения/записи файла", "Ошибка чтения/записи файла: " + e.getMessage());
        }

        showSuccessMessage("Success!");
        actionBruteForce.setDisable(true);
    }

    @FXML
    protected void onChooseReadFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileRead = fileChooser.showOpenDialog(null);

        if (fileRead != null) {
            try {
                Validator.isFileValid(fileRead.getPath());

            } catch (IOException e) {
                showErrorDialog("Ошибка валидации файла", "Ошибка валидации файла: " + e.getMessage());
            }
        }

        if (bruteForceTab.isSelected()) checkButtonBruteForce();
    }

    @FXML
    protected void onChooseWriteFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileWrite = fileChooser.showOpenDialog(null);

        if (bruteForceTab.isSelected()) checkButtonBruteForce();
    }

    @FXML
    public void initialize() {
        encryptKeyField.textProperty().addListener((observable, oldValue, newValue) ->
                checkButtonState(encryptKeyField, actionEncrypt));

        decryptKeyField.textProperty().addListener((observable, oldValue, newValue) ->
                checkButtonState(decryptKeyField, actionDecrypt));
    }

    private void checkButtonState(TextField textField, Button button) {
        boolean isFileSelected = fileRead != null && fileWrite != null;
        boolean isTextFilled = !textField.getText().trim().isEmpty();
        button.setDisable(!(isFileSelected && isTextFilled));
    }

    private void checkButtonBruteForce() {
        boolean isFileSelected = fileRead != null && fileWrite != null;
        actionBruteForce.setDisable(!isFileSelected);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


