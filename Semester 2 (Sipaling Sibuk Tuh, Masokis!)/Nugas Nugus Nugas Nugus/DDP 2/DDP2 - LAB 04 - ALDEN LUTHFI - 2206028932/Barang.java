
class Barang {                                                                  /* I added nothing here               */
    private long harga;
    private String nama;
    private int stok;

    public Barang(String nama, long harga, int stok) {                          /* unordered arguments now ordered    */
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public long getHarga() {                                                    /* Some getters and setters           */
        return this.harga;
    }

    public int getStok() {
        return this.stok;
    }

    public String getNama() {
        return this.nama;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public boolean equals(Object value) {                                       /* Overridden equals method           */
        return this.nama.equals((String) value);
    }
}
