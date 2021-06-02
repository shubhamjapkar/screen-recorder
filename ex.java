package sample;

import java.io.IOException;

public class ex {
    public static void main(String[] args) throws IOException {
     Process process=Runtime.getRuntime().exec("java --module-path \"D:\\Java IntellijId programs files\\JavaFX\\javafx-sdk-16\\lib\" --add-modules javafx.controls,javafx.fxml,javafx.media -jar \"D:\\Java IntellijId programs files\\zip file of all programs\\Jar Files\\Screen Recorder\\screenrecording.jar\" Main");
    }
}


