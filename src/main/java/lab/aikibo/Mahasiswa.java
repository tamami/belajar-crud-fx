package lab.aikibo;


import org.joda.time.DateTime;

/**
 * Created by tamami on 26/03/17.
 */
public class Mahasiswa {

    private String nim;
    private String nama;
    private String tempatLahir;
    private DateTime tanggalLahir;
    private boolean jenisKelamin; // true = pria
    private String alamat;

    public Mahasiswa(String nim, String nama, String tempatLahir, DateTime tanggalLahir, boolean jenisKelamin, String alamat) {
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

    //public Date getTanggalLahir() {
    //    return tanggalLahir;
    //}

    public String getTanggalLahir() {
        return tanggalLahir.getDayOfMonth() + "-" + tanggalLahir.getMonthOfYear() + "-" + tanggalLahir.getYear();
    }

    public void setTanggalLahir(DateTime tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public boolean isJenisKelamin() {
        return jenisKelamin;
    }

    public String getJenisKelamin() {
        return (jenisKelamin) ? "Laki-laki" : "Perempuan";
    }

    public void setJenisKelamin(boolean jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
