package lab.aikibo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.LinkedList;

import static lab.aikibo.MainUI.getPrimaryStage;

/**
 * Created by tamami on 27/03/17.
 */
public class DaftarMahasiswaUI {

    private VBox vbox;

    public static ObservableList<Mahasiswa> data;
    private Scene scene;
    private Scene mainScene;
    private TableView tblMahasiswa;
    private Button btnEntryData;
    private Button btnUbahData;
    private Button btnExport;
    private Button btnImport;

    public DaftarMahasiswaUI() {
        data = FXCollections.observableArrayList();
        data.addAll(new Mahasiswa("16090001", "tamami", "jakarta", new DateTime(), true,
            "jl veteran no. 11 brebes"));
        data.addAll(new Mahasiswa("16090002", "priyanto", "brebes", new DateTime(), true,
            "jatibarang brebes"));

        initComponents();
    }



    private void initComponents() {
        vbox = new VBox(5);
        vbox.getChildren().addAll(new Label("Daftar Mahasiswa"));

        tblMahasiswa = new TableView();
        tblMahasiswa.setEditable(false);
        TableColumn nimCol = new TableColumn("NIM");
        TableColumn namaCol = new TableColumn("Nama");
        TableColumn tempatLahirCol = new TableColumn("Tempat Lahir");
        TableColumn tglLahirCol = new TableColumn("Tanggal Lahir");
        TableColumn jenisKelaminCol = new TableColumn("Jenis Kelamin");
        TableColumn alamatCol = new TableColumn("Alamat");
        nimCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nim"));
        namaCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nama"));
        tempatLahirCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("tempatLahir"));
        tglLahirCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("tanggalLahir"));
        jenisKelaminCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("jenisKelamin"));
        alamatCol.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("alamat"));
        tblMahasiswa.getColumns().addAll(nimCol, namaCol, tempatLahirCol, tglLahirCol, jenisKelaminCol, alamatCol);
        tblMahasiswa.setItems(data);
        vbox.getChildren().addAll(tblMahasiswa);

        btnEntryData = new Button("Tambah Data");
        btnUbahData = new Button("Ubah Data");
        btnExport = new Button("Export");
        btnImport = new Button("Import");

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.getChildren().addAll(btnEntryData, btnUbahData, btnExport, btnImport);
        vbox.getChildren().addAll(hbox);

        btnEntryData.setOnAction(new BtnEntryDataOnClick());

        scene = new Scene(vbox, 700, 400);
    }

    public void show() {
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
    }


    // -- inner class

    private class BtnEntryDataOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            MainUI.getEntryUI().show();
            MainUI.getPrimaryStage().centerOnScreen();
            MainUI.getPrimaryStage().requestFocus();
        }
    }

}
