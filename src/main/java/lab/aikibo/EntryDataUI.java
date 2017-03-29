package lab.aikibo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import static lab.aikibo.MainUI.getMhsUI;
import static lab.aikibo.MainUI.getPrimaryStage;

/**
 * Created by tamami on 28/03/17.
 */
public class EntryDataUI {

    public static final int ADD_DATA = 1;
    public static final int EDIT_DATA = 2;

    Scene entryScene;
    GridPane gridPane;

    TextField tfNim;
    Label lblNim;
    Label lblNama;
    TextField tfNama;
    Label lblTempatLahir;
    TextField tfTempatLahir;
    Label lblTglLahir;
    DatePicker dpTglLahir;
    Label lblJenisKelamin;
    ComboBox<String> cbJenisKelamin;
    Label lblAlamat;
    TextArea taAlamat;
    Button btnSimpan;
    Button btnHapus;
    Button btnBatal;

    int state;

    public EntryDataUI() {
        getPrimaryStage().setTitle("Entry Data");
        initComponents();
    }

    private void initComponents() {
        lblNim = new Label("NIM");
        tfNim = new TextField();
        lblNama = new Label("Nama");
        tfNama = new TextField();
        lblTempatLahir = new Label("Tempat Lahir");
        tfTempatLahir = new TextField();
        lblTglLahir = new Label("Tanggal Lahir");
        dpTglLahir = new DatePicker();
        lblJenisKelamin = new Label("Jenis Kelamin");
        cbJenisKelamin = new ComboBox<String>();
        lblAlamat = new Label("Alamat");
        taAlamat = new TextArea();
        btnSimpan = new Button("Simpan");
        btnHapus = new Button("Hapus");
        btnBatal = new Button("Batal");

        cbJenisKelamin.getItems().addAll("Laki-laki", "Perempuan");
        taAlamat.setMaxSize(200, 100);
        taAlamat.setWrapText(true);

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 5, 0, 10)); // padding for gridpane

        GridPane.setConstraints(lblNim, 1, 1);
        GridPane.setConstraints(tfNim, 2, 1);
        GridPane.setConstraints(lblNama, 1, 2);
        GridPane.setConstraints(tfNama, 2, 2);
        GridPane.setConstraints(lblTempatLahir, 1, 3);
        GridPane.setConstraints(tfTempatLahir, 2, 3);
        GridPane.setConstraints(lblTglLahir, 1, 4);
        GridPane.setConstraints(dpTglLahir, 2, 4);
        GridPane.setConstraints(lblJenisKelamin, 1, 5);
        GridPane.setConstraints(cbJenisKelamin, 2, 5);
        GridPane.setConstraints(lblAlamat, 1, 6);
        GridPane.setConstraints(taAlamat, 2, 6);

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(btnSimpan, btnHapus, btnBatal);
        GridPane.setConstraints(hbox, 1, 7, 3, 1);

        gridPane.getChildren().addAll(lblNim, tfNim, lblNama, tfNama, lblTempatLahir, tfTempatLahir,
            lblTglLahir, dpTglLahir, lblJenisKelamin, cbJenisKelamin, lblAlamat, taAlamat, hbox);

        btnSimpan.setOnAction(new BtnSimpanOnClick());
        btnBatal.setOnAction(new BtnBatalOnClick());

        entryScene = new Scene(gridPane, 350, 350);
    }

    public void show(int state) {
        if(state == EntryDataUI.ADD_DATA) {
            clearForm();
            btnHapus.setDisable(true);
        }
        getPrimaryStage().setScene(entryScene);
        getPrimaryStage().show();
    }

    private void clearForm() {
        tfNim.clear();
        tfNama.clear();
        tfTempatLahir.clear();
        dpTglLahir.setValue(null);
        cbJenisKelamin.setValue(null);
        taAlamat.clear();
    }


    // -- inner class

    private class BtnSimpanOnClick implements EventHandler<ActionEvent> {

        @SuppressWarnings("Since15")
        public void handle(ActionEvent event) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.setNim(tfNim.getText());
            mhs.setNama(tfNama.getText());
            mhs.setTempatLahir(tfTempatLahir.getText());
            mhs.setTanggalLahir(new DateTime(dpTglLahir.getValue().getYear(),
                dpTglLahir.getValue().getMonthValue(), dpTglLahir.getValue().getDayOfMonth(), 0, 0));
            mhs.setJenisKelamin(cbJenisKelamin.getValue().equals("Laki-laki"));
            mhs.setAlamat(taAlamat.getText());

            int idx;
            if((idx = getMhsUI().isExists(tfNim.getText())) < 0) {
                tfNim.setDisable(true);
                getMhsUI().data.set(idx, mhs);
            } else {
                if(tfNim.getText() == null || tfNim.getText().trim().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Kesalahan");
                    alert.setHeaderText("Kesalahan Pengguna");
                    alert.setContentText("Silahkan isikan Nomor Induk Mahasiswanya");
                    alert.showAndWait();
                    return;
                }
                getMhsUI().data.addAll(mhs);
            }
            getMhsUI().show();
        }
    }

    private class BtnBatalOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            getMhsUI().show();
        }
    }

}
