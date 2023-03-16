
class Pembeli {

    private final int MAKS_JUMLAH_BARANG = 20;                                  /* Konstanta maksimal barang          */

    private String nama;                                                        /* Deklarasi field yang digunakan     */
    private long jumlahUang;
    private Pesanan[] listPesanan = new Pesanan[MAKS_JUMLAH_BARANG];            /* Why a constant then not use it?    */

    public Pembeli(String nama, long jumlahUang){                               /* Konstruktor yang disesuaikan       */
        this.nama = nama;
        this.jumlahUang = jumlahUang;
    }

    public String tambahPesanan(Barang barang, int jumlah){                     /* Driver method tambah pesanan       */

        String namaBarang = barang.getNama();
        int stokBarang = barang.getStok();

        Pesanan pesanan = Diskonpedia.<Pesanan>find(listPesanan, namaBarang);   /* handy generic type method          */

        if (pesanan == null) pesanan = new Pesanan(barang, 0);                  /* membuat object baru kalau blom ada */

        long totalHarga = kumulatifHarga() + (barang.getHarga() * jumlah);      /* Total harga jika barang dibeli     */

        if (jumlah > stokBarang) {                                              /* Stok kurang                        */
            return
                ("Tidak bisa memesan %s sebanyak" +
                " %d buah. Stok barang tidak cukup\n")
                .formatted(namaBarang, jumlah);
        } else if (kumulatifJumlah() + jumlah > MAKS_JUMLAH_BARANG) {           /* lebih dari maksimum barang         */
            return
                ("Tidak bisa memesan %s sebanyak %d " +
                "buah. List pesanan %s melebihi kapasitas\n")
                .formatted(namaBarang, jumlah, this.nama);
        } else if (totalHarga > jumlahUang) {                                   /* Uang kurang                        */
            return
                ("Tidak bisa memesan %s sebanyak %d" +
                " buah. Uang %s tidak cukup\n")
                .formatted(namaBarang, jumlah, this.nama);
        }

        pesanan.setjumlah(pesanan.getJumlah() + jumlah);                        /* Update jumlah pesanan              */
        barang.setStok(stokBarang - jumlah);

        for (int i = 0; i < listPesanan.length; i++) {                          /* memasukkan pesanan ke list         */
            if (listPesanan[i] == pesanan || listPesanan[i] == null) {
                listPesanan[i] = pesanan;
                break;
            }
        }

        return "%s berhasil memesan %s sebanyak %d buah\n"                      /* return pesan berhasil              */
            .formatted(this.nama, namaBarang, jumlah);
    }

    public long kumulatifHarga() {                                              /* Harga total didalam list pesanan   */
        long result = 0;
        for (Pesanan p: listPesanan) if (p != null) result += p.totalHarga();
        return result;
    }

    public long kumulatifJumlah() {                                             /* Jumlah total dalam list pesanan    */
        long result = 0;
        for (Pesanan p: listPesanan) if (p != null) result += p.getJumlah();
        return result;
    }

    public void resetPesanan(){
        listPesanan = new Pesanan[MAKS_JUMLAH_BARANG];
    }

    public String getNama() {                                                   /* write code with clarity in mind    */
        return this.nama;                                                       /* don't forget "this" keyword        */
    }

    public long getUang() {                                                     /* Some getter and setter             */
        return this.jumlahUang;
    }

    public void setUang(long uang) {
        this.jumlahUang = uang;
    }

    public Pesanan[] getPesanan() {
        return this.listPesanan;
    }

    public String pesananString() {                                             /* list pesanan as a String           */
        String result = "";
        for (Pesanan p: listPesanan) if (p != null) {
            result +=
                "\n%s: %d * %d = %d"
                .formatted(
                    p.getBarang().getNama(),
                    p.getBarang().getHarga(),
                    p.getJumlah(),
                    p.totalHarga()
                );
        }
        return result;
    }

    @Override
    public boolean equals(Object value) {                                       /* Overriding equals methos           */
        return this.nama.equals((String) value);
    }

}
