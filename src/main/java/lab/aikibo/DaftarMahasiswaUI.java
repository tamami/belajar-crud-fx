package lab.aikibo;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by tamami on 27/03/17.
 */
public class DaftarMahasiswaUI {

    private Scene scene;

    public DaftarMahasiswaUI() {
        initComponents();
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
