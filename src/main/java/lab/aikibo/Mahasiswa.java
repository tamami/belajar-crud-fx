package lab.aikibo;


import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by tamami on 26/03/17.
 */
public class Mahasiswa implements Serializable {

    private String nim;
    private String nama;
    private String tempatLahir;
    private DateTime tanggalLahir;
    private String jenisKelamin;
    private String alamat;

    public Mahasiswa() {}

    public Mahasiswa(String nim, String nama, String tempatLahir, DateTime tanggalLahir, String jenisKelamin, String alamat) {
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return String.format("%02d", tanggalLahir.getDayOfMonth()) + "-" +
               String.format("%02d", tanggalLahir.getMonthOfYear()) + "-" +
               String.format("%04d", tanggalLahir.getYear());
    }

    public void setTanggalLahir(DateTime tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
