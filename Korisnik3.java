package ZadatakC3;

import com.sun.media.jfxmedia.AudioClip;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

//--module-path "C:\Users\Stefan\Desktop\javafx-sdk-13.0.1\lib" --add-modules javafx.controls,javafx.fxml
public class Korisnik3 extends Application {
    public static int brojacKoraka=0;
    public static int brojacHash=0;
   // public static int x[]=new int[9];
    public static Button start;
   public  static int nizpom[];
    public  static  Button niz[]=new Button[9];
    public  static int[] Probudi_niz(){
        int x[]=new int[9];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int)(Math.random()*9);

            for (int j = 0; j < i; j++) {
                if (x[i] == x[j]) {
                    i--;
                    break;
                }
            }
        }
        return x;
    }
    public static void main(String[] args) {


        launch(args);
    }
    public class Nit implements Runnable{
        private  int[] lepNiz;

        public Nit(int[] niz) {
            this.lepNiz = niz;
        }



        @Override
        public void run() {
            start.setDisable(true);
           // System.out.println("Nit pokrenuta ");
            for(int i=0;i<Korisnik3.niz.length;i++){
                niz[i].setStyle("-fx-background-color: #006400; -fx-text-fill: #006400; -fx-pref-height: 100px; -fx-pref-width: 100px;-fx-border-color:black;");

                niz[i].setDisable(true);
            }
            int brojac=0;
            // while(brojac<9){
            String tekst="";
           // ArrayList<B>
            for(int i=0;i<Korisnik3.niz.length;i++)
            {   for(int j=0;j<Korisnik3.niz.length;j++)

                if(String.valueOf(i).equals(Korisnik3.niz[j].getText())){
                    Korisnik3.niz[j].setStyle("-fx-background-color: yellow; -fx-text-fill: yellow; -fx-pref-height: 100px; -fx-pref-width: 100px; -fx-border-color:black;");
                    brojac++;
                    //System.out.println("Brojac : "+brojac);
                  //  System.out.println("Redni broj stampe: "+ (i+1)+ " : Pozicija dugmeta "+ (j+1));
                     tekst+=(i+1)+":"+(j+1)+" ";

                }

                try {
                    Thread.sleep(250);//tako kaze tekst
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(brojac==9){
                    for(int X=0;X<Korisnik3.niz.length;X++){
                        niz[X].setDisable(false);

                    }
                    start.setDisable(false);
                    System.out.println(tekst);
                    System.out.println("---------------------------------------");
                }
            }
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Brzo pamcenje");

        GridPane gp=new GridPane();
        start =new Button("Zapocni igru");

        start.setStyle("-fx-background-color: orange; -fx-text-fill: red; -fx-pref-height: 20px; -fx-pref-width: 300px;-fx-border-color:yellow; -fx-padding: 5 10 0 0;");

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Nit n=new Nit( nizpom=Korisnik3.Probudi_niz());
                for(int i=0;i<nizpom.length;i++){
                    niz[i].setText(String.valueOf(nizpom[i]));
                }
                Thread t=new Thread(n);
                t.start();


                for(int i=0;i<nizpom.length;i++)
                {
                //    System.out.println(nizpom[i]);
                }
            }
        });
        for(int i=0;i<niz.length;i++){

            niz[i]=new Button(String.valueOf(i));
            niz[i].setStyle("-fx-background-color: #006400; -fx-text-fill: #006600; -fx-pref-height: 100px; -fx-pref-width: 100px;-fx-border-color:black;");
            niz[i].setDisable(true);

            int finalI = i;
            niz[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {




                    if(niz[finalI].getText().equals(String.valueOf(brojacKoraka))){
                    //    System.out.println("Pogodak");
                        niz[finalI].setText("X");
                        niz[finalI].setStyle("-fx-background-color: #006400; -fx-text-fill: #006400; -fx-pref-height: 100px; -fx-pref-width: 100px;-fx-border-color:black;");
                        brojacKoraka++;
                        if(brojacKoraka==9){
                            for(int i=0;i<niz.length;i++){
                                niz[i].setDisable(true);
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Cestitke!");
                            alert.setHeaderText("Presli ste igricu");
                            alert.setContentText("Svaka cast!");
                            alert.showAndWait().ifPresent(rs -> {
                                if (rs == ButtonType.OK) {
                             //       System.out.println("Kliknuo na ok OK.");
                                }
                            });
                        }
                       // niz[finalI].setDisable(true);
                    }else{
                      //  System.out.println("Promasaj");
                        for(int i=0;i<niz.length;i++){
                            niz[i].setStyle("-fx-background-color: red; -fx-text-fill: red; -fx-pref-height: 100px; -fx-pref-width: 100px;-fx-border-color:black;");
                            niz[i].setDisable(true);
                            brojacKoraka=0;
                        }
                    }
                }
            });
        }
        StackPane root=new StackPane();//jedno dugme 100x100

        stage.setScene(new Scene(root,300,330));
        stage.setMinHeight(330);
        stage.setMaxHeight(330);
        stage.setMaxWidth(300);
        stage.setMaxWidth(300);
        gp.addRow(0,niz[0],niz[1],niz[2]);
        gp.addRow(1,niz[3],niz[4],niz[5]);
        gp.addRow(2,niz[6],niz[7],niz[8]);


        Separator separator = new Separator(Orientation.HORIZONTAL);


        VBox vBox = new VBox(gp, separator, start);

        root.getChildren().add(vBox);

        stage.show();
    }





}
