public class Burung extends Hewan {

    public Burung(String nama, String warna, int berat) {
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

    public void walk() {
        System.out.printf("%s berjalan!\n", this.nama);
    }

    public void fly() {
        System.out.printf("%s terbang!\n", this.nama);
    }
}
