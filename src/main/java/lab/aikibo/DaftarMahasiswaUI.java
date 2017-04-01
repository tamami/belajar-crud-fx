package lab.aikibo;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static lab.aikibo.MainUI.getPrimaryStage;

/**
 * Created by tamami on 27/03/17.
 */
public class DaftarMahasiswaUI {

    private Stage stage;

    private VBox vbox;

    public static ObservableList<Mahasiswa> data;
    private Scene scene;
    private Scene mainScene;
    private TableView tblMahasiswa;
    private Button btnEntryData;
    private Button btnUbahData;
    private Button btnExport;
    private Button btnImport;

    public DaftarMahasiswaUI(Stage stage) {
        this.stage = stage;
        data = FXCollections.observableArrayList();
        data.addAll(new Mahasiswa("16090001", "tamami", "jakarta", new DateTime(), "Laki-laki",
            "jl veteran no. 11 brebes"));
        data.addAll(new Mahasiswa("16090002", "priyanto", "brebes", new DateTime(), "Laki-laki",
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
        btnUbahData.setOnAction(new BtnUbahDataOnClick());
        btnExport.setOnAction(new BtnExportOnClick());
        btnImport.setOnAction(new BtnImportOnClick());

        scene = new Scene(vbox, 700, 400);
    }

    public void show() {
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
    }

    // return index if exists
    // return -1 if not
    public int isExists(String nim) {
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).getNim().equals(nim)) {
                return i;
            }
        }

        return -1;
    }

    private LinkedList<Mahasiswa> convertToListSerializable(ObservableList<Mahasiswa> data) {
        LinkedList<Mahasiswa> result = new LinkedList();

        Iterator<Mahasiswa> it = data.iterator();
        while(it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    private void setData(LinkedList<Mahasiswa> data) {
        this.data.removeAll(this.data);
        this.data = FXCollections.observableList(data);
        //FXCollections.copy(this.data, FXCollections.observableList(data));
        tblMahasiswa.setItems(this.data);

    }

    private boolean simpan(File file) {
        LinkedList list = convertToListSerializable(data);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println("Kesalahan saat siapkan file untuk disimpan");
            e.printStackTrace();

            return false;
        }
        return true;
    }

    private boolean muatkan(File file) {
        LinkedList<Mahasiswa> data;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            data = (LinkedList<Mahasiswa>) ois.readObject();
            setData(data);
            ois.close();
        } catch(IOException e) {
            System.out.println("Kesalahan saat membuka file");
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Kesalahan nama kelas");
        }
        return true;
    }


    // -- inner class

    private class BtnEntryDataOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            MainUI.getEntryUI().show(EntryDataUI.ADD_DATA);
            MainUI.getPrimaryStage().centerOnScreen();
            MainUI.getPrimaryStage().requestFocus();
        }
    }

    private class BtnUbahDataOnClick implements EventHandler<ActionEvent> {

        @SuppressWarnings("Since15")
        public void handle(ActionEvent event) {
            TablePosition pos = (TablePosition) tblMahasiswa.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Mahasiswa item = (Mahasiswa) tblMahasiswa.getItems().get(row);

            MainUI.getEntryUI().getTfNim().setText(item.getNim());
            MainUI.getEntryUI().getTfNim().setEditable(false);
            MainUI.getEntryUI().getTfNama().setText(item.getNama());
            MainUI.getEntryUI().getTfTempatLahir().setText(item.getTempatLahir());
            MainUI.getEntryUI().getDpTglLahir().setValue(LocalDate.of(
                    Integer.parseInt(item.getTanggalLahir().substring(6,10)),
                    Integer.parseInt(item.getTanggalLahir().substring(3,5)),
                    Integer.parseInt(item.getTanggalLahir().substring(0,2))));
            MainUI.getEntryUI().getCbJenisKelamin().setValue(item.getJenisKelamin());
            MainUI.getEntryUI().getTaAlamat().setText(item.getAlamat());

            MainUI.getEntryUI().show(EntryDataUI.EDIT_DATA);
            MainUI.getPrimaryStage().centerOnScreen();
            MainUI.getPrimaryStage().requestFocus();
            MainUI.getEntryUI().getTfNama().requestFocus();
        }
    }

    private class BtnExportOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            FileChooser fileDialog = new FileChooser();
            fileDialog.setTitle("Simpan Data");
            File file = fileDialog.showSaveDialog(stage);
            if(simpan(file)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Informasi Simpan");
                alert.setContentText("File berhasil terbentuk dan data sudah tersimpan");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Kesalahan");
                alert.setHeaderText("Kesalahan Simpan");
                alert.setContentText("Data tidak bisa di ekspor ke file");
                alert.showAndWait();
            }


        }
    }

    private class BtnImportOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            FileChooser fileDialog = new FileChooser();
            fileDialog.setTitle("Muatkan Data");
            File file = fileDialog.showOpenDialog(stage);
            if(muatkan(file)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText("Informasi Buka File");
                alert.setContentText("Data telah diimport dari file");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Kesalahan");
                alert.setHeaderText("Kesalahan Buka File");
                alert.setContentText("Data tidak bisa di import dari file");
                alert.showAndWait();
            }
        }
    }

}
