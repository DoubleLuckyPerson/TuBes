package id.ac.polinema.tiketkereta.Models;

public class Pesan {

    public static final int GAJAYANA =0;
    public static final int MATARMAJA = 1;

    private int kereta;
    private int penumpang;
    private int total;

    public Pesan(int penumpang,int kereta){
        this.kereta = kereta;
        this.penumpang = penumpang;
        this.total = calculate();
    }

    public int getPenumpang() {
        return penumpang;
}

    public int getTotal(){
        return total;
    }

    private int calculate() {
        switch (kereta) {
            case 0: return (penumpang * 350000);
            case 1: return (penumpang * 110000);
            default:return 0;
        }
    }

}
