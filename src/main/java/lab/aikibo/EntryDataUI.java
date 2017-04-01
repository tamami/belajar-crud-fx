package lab.aikibo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.joda.time.DateTime;

import static lab.aikibo.MainUI.getMhsUI;
import static lab.aikibo.MainUI.getPrimaryStage;

/**
 * Created by tamami on 28/03/17.
 */
public class EntryDataUI {

    public static final int ADD_DATA = 1;
    public static final int EDIT_DATA = 2;

    private Scene entryScene;
    private GridPane gridPane;

    private TextField tfNim;
    private Label lblNim;
    private Label lblNama;
    private TextField tfNama;
    private Label lblTempatLahir;
    private TextField tfTempatLahir;
    private Label lblTglLahir;
    private DatePicker dpTglLahir;
    private Label lblJenisKelamin;
    private ComboBox<String> cbJenisKelamin;
    private Label lblAlamat;
    private TextArea taAlamat;
    private Button btnSimpan;
    private Button btnHapus;
    private Button btnBatal;

    private int state;

    public EntryDataUI() {
        getPrimaryStage().setTitle("Entry Data");
        initComponents();
    }

    private void initComponents() {
        lblNim = new Label("NIM");
        setTfNim(new TextField());
        lblNama = new Label("Nama");
        setTfNama(new TextField());
        lblTempatLahir = new Label("Tempat Lahir");
        setTfTempatLahir(new TextField());
        lblTglLahir = new Label("Tanggal Lahir");
        setDpTglLahir(new DatePicker());
        lblJenisKelamin = new Label("Jenis Kelamin");
        setCbJenisKelamin(new ComboBox<String>());
        lblAlamat = new Label("Alamat");
        setTaAlamat(new TextArea());
        btnSimpan = new Button("Simpan");
        btnHapus = new Button("Hapus");
        btnBatal = new Button("Batal");

        getCbJenisKelamin().getItems().addAll("Laki-laki", "Perempuan");
        getTaAlamat().setMaxSize(200, 100);
        getTaAlamat().setWrapText(true);

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 5, 0, 10)); // padding for gridpane

        GridPane.setConstraints(lblNim, 1, 1);
        GridPane.setConstraints(getTfNim(), 2, 1);
        GridPane.setConstraints(lblNama, 1, 2);
        GridPane.setConstraints(getTfNama(), 2, 2);
        GridPane.setConstraints(lblTempatLahir, 1, 3);
        GridPane.setConstraints(getTfTempatLahir(), 2, 3);
        GridPane.setConstraints(lblTglLahir, 1, 4);
        GridPane.setConstraints(getDpTglLahir(), 2, 4);
        GridPane.setConstraints(lblJenisKelamin, 1, 5);
        GridPane.setConstraints(getCbJenisKelamin(), 2, 5);
        GridPane.setConstraints(lblAlamat, 1, 6);
        GridPane.setConstraints(getTaAlamat(), 2, 6);

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().addAll(btnSimpan, btnHapus, btnBatal);
        GridPane.setConstraints(hbox, 1, 7, 3, 1);

        gridPane.getChildren().addAll(lblNim, getTfNim(), lblNama, getTfNama(), lblTempatLahir, getTfTempatLahir(),
            lblTglLahir, getDpTglLahir(), lblJenisKelamin, getCbJenisKelamin(), lblAlamat, getTaAlamat(), hbox);

        btnSimpan.setOnAction(new BtnSimpanOnClick());
        btnBatal.setOnAction(new BtnBatalOnClick());

        entryScene = new Scene(gridPane, 350, 350);
    }

    public void show(int state) {
        if(state == EntryDataUI.ADD_DATA) {
            clearForm();
            tfNim.setEditable(true);
            btnHapus.setDisable(true);
        } else {
            tfNim.setEditable(false);
            btnHapus.setDisable(false);
        }
        getPrimaryStage().setScene(entryScene);
        getPrimaryStage().show();
    }

    private void clearForm() {
        getTfNim().clear();
        getTfNama().clear();
        getTfTempatLahir().clear();
        getDpTglLahir().setValue(null);
        getCbJenisKelamin().setValue(null);
        getTaAlamat().clear();
    }

    public TextField getTfNim() {
        return tfNim;
    }

    public void setTfNim(TextField tfNim) {
        this.tfNim = tfNim;
    }

    public TextField getTfNama() {
        return tfNama;
    }

    public void setTfNama(TextField tfNama) {
        this.tfNama = tfNama;
    }

    public TextField getTfTempatLahir() {
        return tfTempatLahir;
    }

    public void setTfTempatLahir(TextField tfTempatLahir) {
        this.tfTempatLahir = tfTempatLahir;
    }

    public DatePicker getDpTglLahir() {
        return dpTglLahir;
    }

    public void setDpTglLahir(DatePicker dpTglLahir) {
        this.dpTglLahir = dpTglLahir;
    }

    public ComboBox<String> getCbJenisKelamin() {
        return cbJenisKelamin;
    }

    public void setCbJenisKelamin(ComboBox<String> cbJenisKelamin) {
        this.cbJenisKelamin = cbJenisKelamin;
    }

    public TextArea getTaAlamat() {
        return taAlamat;
    }

    public void setTaAlamat(TextArea taAlamat) {
        this.taAlamat = taAlamat;
    }


    // alert
    private void panggilPesanInformasi(String header, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void panggilPesanError(String header, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kesalahan");
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    // -- inner class

    private class BtnSimpanOnClick implements EventHandler<ActionEvent> {

        public Mahasiswa proses(String nim, String nama, String tempatLahir, DateTime tglLahir,
                                String jnsKelamin, String alamat) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.setNim(nim);
            mhs.setNama(nama);
            mhs.setTempatLahir(tempatLahir);
            mhs.setTanggalLahir(tglLahir);
            mhs.setJenisKelamin(jnsKelamin);
            mhs.setAlamat(alamat);

            return mhs;
        }

        @SuppressWarnings("Since15")
        public void handle(ActionEvent event) {
            int idx;
            if((idx = getMhsUI().isExists(getTfNim().getText())) < 0) {
                if(getMhsUI().addData(getTfNim().getText(), getTfNama().getText(), getTfTempatLahir().getText(),
                        new DateTime(getDpTglLahir().getValue().getYear(), getDpTglLahir().getValue().getMonthValue(),
                                getDpTglLahir().getValue().getDayOfMonth(), 0, 0),
                        getCbJenisKelamin().getValue(), getTaAlamat().getText())) {
                    panggilPesanInformasi("Informasi Tambah Data", "Data Berhasil Ditambahkan");
                } else {
                    panggilPesanInformasi("Informasi Penambahan Data", "Data Gagal Ditambahkan");
                }
            } else {
                if(getTfNim().getText() == null || getTfNim().getText().trim().equals("")) {
                    panggilPesanError("Kesalahan Pengguna", "Silahkan isikan Nomor Induk Mahasiswa lebih dulu");
                    return;
                }
                getTfNim().setDisable(true);
                getMhsUI().updateData(idx, getTfNim().getText(), getTfNama().getText(), getTfTempatLahir().getText(),
                        new DateTime(getDpTglLahir().getValue().getYear(), getDpTglLahir().getValue().getMonthValue(),
                                getDpTglLahir().getValue().getDayOfMonth(), 0, 0),
                        getCbJenisKelamin().getValue(), getTaAlamat().getText());
            }
            getMhsUI().show();
        }
    }

    private class BtnBatalOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            getMhsUI().show();
        }
    }

    private class BtnHapusOnClick implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            int idx;
            if((idx = getMhsUI().isExists(getTfNim().getText())) < 0) {

            }
        }
    }

}
