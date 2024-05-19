package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {

    static public int notaCounter;                                              /* Menghitung semua nota yang dibuat  */

    final private int idNota, berat;                                            /* Field private untuk semua instance */
    final private String paket, tanggalMasuk, idMember;
    final private Member member;

    private boolean isReady;
    private int sisaHariPengerjaan;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {  /* Constructor sesuai soal            */
        this.idNota = notaCounter++;
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggalMasuk;
        this.idMember = this.member.getID();

        switch (paket.toLowerCase()) {
            case "express" -> sisaHariPengerjaan = 1;
            case "fast" -> sisaHariPengerjaan = 2;
            case "reguler" -> sisaHariPengerjaan = 3;
        }
    }

    public boolean getIsReady() {
        return isReady;
    }

    public int getID() {
        return this.idNota;
    }

    public void decrementSisa() {                                               /* Decrement sisa hari dan update     */
        isReady = (sisaHariPengerjaan == 0 ? 0 : --sisaHariPengerjaan) == 0;    /* isReady                            */
    }

    public String toVerboseString() {                                           /* Full string nota                   */
        return NotaGenerator.generateNota(
            idMember,
            paket,
            berat,
            tanggalMasuk,
            member.getBonus() == 3 ? 2 : 1
        ) + "\nStatus : %s"
            .formatted(
                sisaHariPengerjaan > 0 ?
                "Belum bisa diambil :(" :
                "Sudah dapat diambil!");
    }

    public String toString() {
        return "- [%d] Status : %s".formatted(
            this.idNota,
            sisaHariPengerjaan > 0 ?
            "Belum bisa diambil :(" :
            "Sudah dapat diambil!"
        );
    }

    public Nota[] appendTo(Nota[] array) {                                      /* Efficient dynamic array            */

        if (array == null) array = new Nota[1];

        Nota[] newArray = new Nota[array.length];
        int idx;

        if (array[array.length - 1] != null) {                                  /* Kalau sudah penuh                  */
            newArray =  new Nota[array.length * 2];                             /* Create new array twice the size */
        }

        for (idx = 0; idx < array.length && array[idx] != null; idx++) {        /* Copy arrays                        */
            newArray[idx] = array[idx];                                         /* Inserting values                   */
        }

        newArray[idx] = this;                                                   /* Adding the new element             */

        return newArray;
    }

    public Nota[] popFrom(Nota[] array) {                                       /* Method menghapus object this       */

        Nota[] newArray = new Nota[array.length];
        int idx = 0;

        if (array.length > 2 && array[array.length / 2 + 1] == null) {
            newArray = new Nota[array.length / 2];                              /* Create new array half the size     */
        }

        for (Nota n: array) if (n != null && n != this) newArray[idx++] = n;    /* Adding all other elements          */

        return newArray;
    }

    @Override
    public boolean equals(Object value) {
        return this.idNota == (int) value;
    }

}
