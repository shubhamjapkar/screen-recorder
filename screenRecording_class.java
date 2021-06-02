package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class screenRecording_class extends Application {
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane anchorPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("screenRecordingedit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Screen Recorder");

        Image image=new Image(new FileInputStream("src/sample/Screen recorder logo.png"));
        stage.getIcons().add(image);

        String css= Objects.requireNonNull(this.getClass().getResource("aplication.css")).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setOnCloseRequest(event -> {    System.exit(0);     });
        stage.show();
    }
}
