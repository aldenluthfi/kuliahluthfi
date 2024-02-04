abstract class Hewan {                                                          /* abstrak karena class hewan tidak perlu di instansiasi */
    protected String nama, warna;
    protected int berat;

    public abstract void jump();                                                /* masih abstrak karena hewan lompatnya beda-beda */
    public abstract void attack();                                              /* masih abstrack karena hewan serangannya beda-beda */

    public static void main(String[] args) {
        Kelinci kelinci = new Kelinci("Bobo", "Putih", 2);
        Harimau harimau = new Harimau("Tai Lung", "Oranye", 100);
        Burung burung = new Burung("Iago", "Merah", 1);

        burung.attack();
        burung.jump();
        burung.walk();
        burung.fly();

        harimau.attack();
        harimau.jump();
        harimau.walk();

        kelinci.attack();
        kelinci.jump();
    }
}
