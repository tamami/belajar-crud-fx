package lab.aikibo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Created by tamami on 27/03/17.
 */
public class MainUI extends Application {

    private static DaftarMahasiswaUI mhsUI;
    private static EntryDataUI entryUI;
    private static Stage primaryStage;

    private void initComponent() {
        mhsUI = new DaftarMahasiswaUI();
        entryUI = new EntryDataUI();
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initComponent();

        if(mhsUI == null) {
            throw new Exception("Aplikasi error, UI Daftar Mahasiswa belum disiapkan");
        }

        //primaryStage.setScene(mhsUI.getScene());
        //primaryStage.show();
        mhsUI.show();
    }

    public static void main(String a[]) {
        MainUI app = new MainUI();
        app.launch(a);
    }


    // getter and setter

    public static DaftarMahasiswaUI getMhsUI() { return mhsUI; }

    public static EntryDataUI getEntryUI() { return entryUI; }

    public static Stage getPrimaryStage() { return primaryStage; }

}
