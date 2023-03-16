class Pesanan {                                                                 /* I changed nothing here aswell      */

    private Barang barang;
    private int jumlahBarang;

    public Pesanan(Barang barang, int jumlahBarang){                            /* Konstruktor seesuai soal           */
        this.barang = barang;
        this.jumlahBarang = jumlahBarang;
    }

    public long totalHarga(){                                                   /* Menghitung total harga pesanan     */
        return barang.getHarga() * this.jumlahBarang;
    }

    public Barang getBarang() {                                                 /* getter dari field                  */
        return this.barang;
    }

    public int getJumlah() {
        return this.jumlahBarang;
    }

    public void setjumlah(int jumlahBarang) {                                   /* Setter dari jumlah barang          */
        this.jumlahBarang = jumlahBarang;
    }

    @Override
    public boolean equals(Object value) {                                       /* Overriden the equals method        */
        return this.barang.getNama().equals((String) value);
    }
}
