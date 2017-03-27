package lab.aikibo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by tamami on 27/03/17.
 */
public class DaftarMahasiswaUI {

    public static ObservableList<Mahasiswa> data;
    private Scene scene;
    private TableView tblMahasiswa;

    public DaftarMahasiswaUI() {
        data = FXCollections.observableArrayList();
        data.addAll(new Mahasiswa("16090001", "tamami", "jakarta", new Date(), true,
            "jl veteran no. 11 brebes"));

        initComponents();
    }

    private void initComponents() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Daftar Mahasiswa"));

        tblMahasiswa = new TableView();
        tblMahasiswa.setEditable(false);
        TableColumn nimCol = new TableColumn("NIM");
        nimCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nim"));
        TableColumn namaCol = new TableColumn("Nama");
        tblMahasiswa.getColumns().addAll(nimCol, namaCol);
        vbox.getChildren().addAll(tblMahasiswa);

        scene = new Scene(vbox, 300, 400);
    }

    public Scene getScene() {
        return scene;
    }

}
