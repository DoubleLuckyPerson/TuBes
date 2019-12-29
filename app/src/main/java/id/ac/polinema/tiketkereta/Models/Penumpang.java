package id.ac.polinema.tiketkereta.Models;

public class Penumpang {
    private String nama;
    private String alamat;
    private String ntlp;
    private int jpenumpang;
    private String tglb;
    private String kota_asal;
    private String kota_tj;
    private String kereta;
    private String total;
    private String key;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNtlp() {
        return ntlp;
    }

    public void setNtlp(String ntlp) {
        this.ntlp = ntlp;
    }

    public int getJpenumpang() {
        return jpenumpang;
    }

    public void setJpenumpang(int jpenumpang) {
        this.jpenumpang = jpenumpang;
    }

    public String getTglb() {
        return tglb;
    }

    public void setTglb(String tglb) {
        this.tglb = tglb;
    }

    public String getKota_asal() {
        return kota_asal;
    }

    public void setKota_asal(String kota_asal) {
        this.kota_asal = kota_asal;
    }

    public String getKota_tj() {
        return kota_tj;
    }

    public void setKota_tj(String kota_tj) {
        this.kota_tj = kota_tj;
    }

    public String getKereta() {
        return kereta;
    }

    public void setKereta(String kereta) {
        this.kereta = kereta;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Penumpang() {

    }

    public Penumpang(String nama, String alamat, String ntlp, int jpenumpang, String tglb, String kota_asal, String kota_tj, String kereta) {
        this.nama = nama;
        this.alamat = alamat;
        this.ntlp = ntlp;
        this.jpenumpang = jpenumpang;
        this.tglb = tglb;
        this.kota_asal = kota_asal;
        this.kota_tj = kota_tj;
        this.kereta = kereta;
    }

    public Penumpang(String nama, String alamat, String ntlp, int jpenumpang, String tglb, String kota_asal, String kota_tj, String kereta, String total) {
        this.nama = nama;
        this.alamat = alamat;
        this.ntlp = ntlp;
        this.jpenumpang = jpenumpang;
        this.tglb = tglb;
        this.kota_asal = kota_asal;
        this.kota_tj = kota_tj;
        this.kereta = kereta;
        this.total = total;
    }
}
