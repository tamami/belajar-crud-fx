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

import static lab.aikibo.MainUI.getMhsUI;
import static lab.aikibo.MainUI.getPrimaryStage;

/**
 * Created by tamami on 28/03/17.
 */
public class EntryDataUI {

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
        hbox.getChildren().addAll(btnSimpan, btnHapus);
        GridPane.setConstraints(hbox, 1, 7, 3, 1);

        gridPane.getChildren().addAll(lblNim, tfNim, lblNama, tfNama, lblTempatLahir, tfTempatLahir,
            lblTglLahir, dpTglLahir, lblJenisKelamin, cbJenisKelamin, lblAlamat, taAlamat, hbox);

        btnSimpan.setOnAction(new BtnSimpanOnClick());

        entryScene = new Scene(gridPane, 300, 350);
    }

    public void show() {
        getPrimaryStage().setScene(entryScene);
        getPrimaryStage().show();
    }


    // -- inner class

    private class BtnSimpanOnClick implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            getMhsUI().show();
        }
    }

}
