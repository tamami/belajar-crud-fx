package lab.aikibo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

/**
 * Created by tamami on 27/03/17.
 */
public class DaftarMahasiswaUI {

    public static LinkedList<Mahasiswa> data;
    private Scene scene;

    public DaftarMahasiswaUI() {
        initComponents();
        data = new LinkedList<Mahasiswa>();
    }

    private void initComponents() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Daftar Mahasiswa"));


        scene = new Scene(vbox, 300, 400);
    }

    public Scene getScene() {
        return scene;
    }

}
