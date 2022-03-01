/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authorization;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.File;
import java.net.MalformedURLException;

/**
 * @author denis
 */
public class Authorization extends Application {

    Scene scene;
    Label infoLabel;
    CheckBox checkBox;
    PasswordField passField = new PasswordField();

    @Override
    public void start(Stage primaryStage) {
        //GridPane
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        //Label "Authorization"
        FlowPane row1 = new FlowPane();
        Label auth = new Label("Authorization");
        row1.getChildren().add(auth);
        row1.setAlignment(Pos.BOTTOM_CENTER);
        root.add(row1, 0, 0);

        //Image
        File file = new File("Java.jpg");
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = new Image(localUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        row1.getChildren().add(imageView);
        row1.setHgap(10);

        //Label "name", label "pass"
        GridPane row2 = new GridPane();
        row2.setHgap(10);
        row2.setVgap(10);
        Label nameLable = new Label("name ");
        row2.add(nameLable, 0, 0);
        TextField nameField = new TextField();
        row2.add(nameField, 1, 0);
        Label nameFieldNote = new Label("");
        row2.add(nameFieldNote, 2, 0);
        Label passLable = new Label("pass");
        row2.add(passLable, 0, 1);
        row2.add(passField, 1, 1);
        Label passFieldInfo = new Label("");
        row2.add(passFieldInfo, 2, 1);
        root.add(row2, 0, 1);

        //Button "sign in", button "Clear fields", checkbox "Show password"
        FlowPane row3 = new FlowPane();
        row3.setHgap(10);
        Button singInButton = new Button("Sing in");
        Button clear = new Button("Clear fields");
        checkBox = new CheckBox("Show password");
        singInButton.setOnAction((e) -> {
            String name = nameField.getText();
            String pass = passField.getText();
            UserData user = new UserData(name, pass);
            if (!user.checkPassword()) {
                passFieldInfo.setText("Password is not valid");
                passFieldInfo.setTextFill(Color.RED);
                if (name.equals("")) {
                    nameFieldNote.setText("Enter a name");
                    nameFieldNote.setTextFill(Color.RED);
                }
            } else if (name.equals("")) {
                passFieldInfo.setText("OK");
                passFieldInfo.setTextFill(Color.GREEN);
                nameFieldNote.setText("Enter a name");
                nameFieldNote.setTextFill(Color.RED);
            } else {
                infoLabel.setText("Password and name is valid");
                infoLabel.setTextFill(Color.GREEN);
                nameFieldNote.setText("OK");
                nameFieldNote.setTextFill(Color.GREEN);
                passFieldInfo.setText("OK");
                passFieldInfo.setTextFill(Color.GREEN);
            }
        });
        clear.setOnAction((event -> {
            nameField.clear();
            passField.clear();
            infoLabel.setText("");
            checkBox.setSelected(false);
            passFieldInfo.setText("");
            nameFieldNote.setText("");
        }));
        checkBox.setOnAction((e) -> {
            if (checkBox.isSelected()) infoLabel.setText("Current password is: " + passField.getText());
            else infoLabel.setText("");
        });
        row3.getChildren().add(checkBox);
        row3.getChildren().add(clear);
        row3.getChildren().add(singInButton);
        row3.setAlignment(Pos.BOTTOM_LEFT);
        root.add(row3, 0, 2);


        //Label "info", label "infoPasswordNote"
        FlowPane row4 = new FlowPane();
        infoLabel = new Label();
        Label infoPasswordLabel = new Label();
        infoPasswordLabel.setText("Password must contain 8 characters and consist of: A-Za-z0-9!+_");
        row4.getChildren().add(infoLabel);
        row4.getChildren().add(infoPasswordLabel);
        row4.setAlignment(Pos.TOP_CENTER);
        root.add(row4, 0, 3);


        scene = new Scene(root, 500, 250);

        primaryStage.setTitle("Authorization");
        primaryStage.getIcons().add(new Image("file:Flag.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
