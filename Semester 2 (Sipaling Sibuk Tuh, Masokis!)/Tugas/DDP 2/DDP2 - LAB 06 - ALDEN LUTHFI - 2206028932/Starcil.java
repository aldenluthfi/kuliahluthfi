import java.util.Scanner;

public class Starcil {                                                          /* main static class                  */
    private static Beverage[] daftarMinuman = new Beverage[0];

    private static void masukkanKeDaftarMinuman(Beverage beverage) {            /* Menambah minuman                   */
        Beverage[] newDaftarMinuman = new Beverage[daftarMinuman.length + 1];

        for (int i = 0; i < daftarMinuman.length; i++) {
            newDaftarMinuman[i] = daftarMinuman[i];
        }
        daftarMinuman = newDaftarMinuman;

        newDaftarMinuman[daftarMinuman.length - 1] = beverage;
    }

    private static Beverage getMinuman(String namaMinuman) {                    /* Mengambil minuman dari array       */
        for (Beverage drink : daftarMinuman) {
            if (drink.getName().equalsIgnoreCase(namaMinuman)) {
                return drink;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        java.io.PrintStream out = System.out;

        int n = Integer.parseInt(in.nextLine());                                /* N is not a constant, it should be  */
        for (int i = 0; i < n; i++) {                                           /* camel case, changed to n           */
            String event = in.next();

            if (event.equals("ADD")) {                                          /* Menambah pesanan                   */
                String jenisMinuman = in.next();
                String nama = in.next();
                String size = in.next();
                boolean isCold = in.next().equals("YES");

                if (jenisMinuman.equals("COFFEE")) {
                    masukkanKeDaftarMinuman(new Coffee(nama, size, isCold));
                } else {
                    masukkanKeDaftarMinuman(new Tea(nama, size, isCold));
                }
            }

            else if (event.equals("TOPPING")) {                                 /* Menambahkan topping susu atau krim */
                String namaMinuman = in.next();
                Beverage minuman = getMinuman(namaMinuman);

                if (minuman instanceof Coffee) {
                    ((Coffee) minuman).addWhipCream();
                } else {
                    ((Tea) minuman).addMilk();
                }
            }

            else if (event.equals("ORDERAN")) {                                 /* mencetak pesanan                   */
                String jenisMinuman = in.next();
                if (jenisMinuman.equals("COFFEE")) {
                    for (Beverage drink : daftarMinuman) {
                        if (drink instanceof Coffee) {
                            out.println(drink);
                        }
                    }
                } else {
                    for (Beverage drink : daftarMinuman) {
                        if (drink instanceof Tea) {
                            out.println(drink);
                        }
                    }
                }
            }

            else {
                out.println("PERINTAH TIDAK DITEMUKAN");
            }

            out.flush();
        }
        in.close();
    }
}