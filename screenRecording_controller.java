package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


class takess extends Thread{

     private Boolean extifromloop = false;

     public void setExtifromloop (Boolean extifromloop){
         this.extifromloop =extifromloop;
     }

     ///pause
     private  Boolean pausefromloop =false;

     public void setPausefromloop(Boolean pausefromloop){
         this.pausefromloop = pausefromloop;
     }



    @Override
    public void run() {
        int i=0;
        try {
           while (true) {
               if (extifromloop){
                   break;
               }
           while (pausefromloop==true){
               onSpinWait();
           }
                Robot robot = new Robot();
                Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
                i++;
                System.out.println(i);
                ImageIO.write(bufferedImage, "jpg", new File("src\\sample\\screenshots\\pic0" + i + ".jpg"));
           }


        } catch (AWTException | IOException | IllegalMonitorStateException e) {
         e.printStackTrace();
     }

    }
}

public class screenRecording_controller extends Thread implements Initializable{

    @FXML
    private Button record,stop,pause,Exit;

    @FXML
    private AnchorPane anchorPane;

    int i;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    ///////////////////
    takess obj=new takess();
    int recordcheck;
    public void record(ActionEvent event)  {
        recordcheck++;
        if (recordcheck==1){
            delete();
            obj.start();
        }
    }

    int change;
    public void pause(ActionEvent event) {

         if (change%2==0){
             change++;
             obj.setPausefromloop(true);
         }
         else {
             change++;
             obj.setPausefromloop(false);
         }
    }

    public void stop(ActionEvent event) throws IOException, InterruptedException {
        obj.setExtifromloop(true);
        //
        JFileChooser jFileChooser=new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("D:\\"));
        int check = jFileChooser.showSaveDialog(null);

        if (check == JFileChooser.APPROVE_OPTION){
            File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
            String pathsaver=file+".mp4";
           // System.out.println(pathsaver);
            Process process= Runtime.getRuntime().exec("ffmpeg -r 10 -f image2 -s 1600x900 -start_number 1 -i \"src\\sample\\screenshots\\pic0%d.jpg\" -vframes 1000000 -vcodec libx264 -crf 25  -pix_fmt yuv420p "+pathsaver+"");
            System.exit(1);
        }
    }

    public void delete(){
      //  System.out.println("checking delete");
        File file = new File("src/sample/screenshots");

        for (File subfile : file.listFiles()) {
            subfile.delete();
        }

    }

    public void exit(ActionEvent event) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Do you want to exit");
        alert.setHeaderText("Without save video. Do you want to do");
        alert.setContentText("Do you want to exit without saving program");

        if (alert.showAndWait().get()== ButtonType.OK){
            System.exit(0);
        }

       }

}