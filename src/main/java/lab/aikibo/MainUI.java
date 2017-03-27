package lab.aikibo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Created by tamami on 27/03/17.
 */
public class MainUI extends Application {

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

    public MainUI() {
        initComponent();
    }

    private void initComponent() {
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

        cbJenisKelamin.getItems().addAll("Laki-laki", "Perempuan");
        taAlamat.setMaxSize(200, 100);
        taAlamat.setWrapText(true);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Data Mahasiswa");
        GridPane gridPane = new GridPane();
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
        GridPane.setConstraints(btnSimpan, 3, 7);

        gridPane.getChildren().addAll(lblNim, tfNim, lblNama, tfNama, lblTempatLahir, tfTempatLahir,
                lblTglLahir, dpTglLahir, lblJenisKelamin, cbJenisKelamin, lblAlamat, taAlamat, btnSimpan);


        btnSimpan.setOnAction(new BtnSimpanOnClick(primaryStage));

        Scene scene = new Scene(gridPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // -- inner class

    private class BtnSimpanOnClick implements EventHandler<ActionEvent> {
        Stage primaryStage;
        DaftarMahasiswaUI mhsUI;

        public BtnSimpanOnClick(Stage primaryStage) {
            this.primaryStage = primaryStage;
            mhsUI = new DaftarMahasiswaUI();
        }

        public void handle(ActionEvent event) {
            primaryStage.setScene(mhsUI.getScene());
        }
    }
}
