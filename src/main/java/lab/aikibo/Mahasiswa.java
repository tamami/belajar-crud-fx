package lab.aikibo;

import java.util.Date;

/**
 * Created by tamami on 26/03/17.
 */
public class Mahasiswa {

    private String nim;
    private String nama;
    private String tempatLahir;
    private Date tanggalLahir;
    private boolean jenisKelamin; // true = pria
    private String alamat;

    public Mahasiswa(String nim, String nama, String tempatLahir, Date tanggalLahir, boolean jenisKelamin, String alamat) {
        this.nim = nim;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
