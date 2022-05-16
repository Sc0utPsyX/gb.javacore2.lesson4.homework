package ru.gb.may_chat.client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.management.ListenerNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    String[] name = new String[1];
    @FXML
    private VBox mainPanel;

    @FXML
    private TextArea chatArea;

    @FXML
    private ListView<String> contacts;

    @FXML
    private TextField inputField;

    @FXML
    private Button btnSend;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("mock");
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = inputField.getText();
        if (text == null || text.isBlank()) {
            return;
        }
        if (name[0] == null) {
            chatArea.appendText("Broadcast: " + text + System.lineSeparator());
        }
        else {
            chatArea.appendText(name[0]+ ": " + text + System.lineSeparator());
        }
        inputField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> names = List.of("Vasya", "Masha", "Petya", "Valera", "Nastya");
        contacts.setItems(FXCollections.observableList(names));
        MultipleSelectionModel<String> contactsSelectionModel = contacts.getSelectionModel();
        contactsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                name[0] = newValue;
            }
        });

    }
    public void openHelp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(this.getClass().getResource("/Help.fxml"));
        Parent parent1 = loader1.load();
        Scene scene = new Scene(parent1);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
