public class Kelinci extends Hewan {

    public Kelinci(String nama, String warna, int berat) {
        this.nama = nama;
        this.warna = warna;
        this.berat = berat;
    }

    @Override
    public void attack() {
        System.out.printf("%s menyerang!\n", this.nama);
    }

    @Override
    public void jump() {
        System.out.printf("%s lompat!\n", this.nama);
    }
}
