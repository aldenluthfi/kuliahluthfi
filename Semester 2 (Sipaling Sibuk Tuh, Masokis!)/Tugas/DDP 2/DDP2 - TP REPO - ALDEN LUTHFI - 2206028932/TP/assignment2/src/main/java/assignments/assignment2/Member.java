package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {                                                           /* Representasi sebuah member         */

    final private String nama, noHp, id;                                        /* Field yang tidak bisa diubah       */
    private int bonusCounter;

    public Member(String nama, String noHp) {                                   /* Contrusctor sesuai ketenruan       */
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
    }

    public String getNama() {                                                   /* Beberapa getter dan setter         */
        return this.nama;
    };

    public String getNoHP() {
        return this.noHp;
    };

    public String getID() {
        return this.id;
    }

    public int getBonus() {
        return this.bonusCounter;
    }

    public void incrementBonus() {                                              /* Setter bonus untuk diskon          */
        this.bonusCounter = this.bonusCounter > 3 ? 0 : ++this.bonusCounter;
    }

    public Member[] appendTo(Member[] array) {                                  /* Efficient dynamic array            */

        if (array == null) array = new Member[1];                               /* Jika array belum diinisialisasi    */

        Member[] newArray = new Member[array.length];                           /* Membuat array baru                 */
        int idx;

        if (array[array.length - 1] != null) {                                  /* Kalau sudah penuh                  */
            newArray =  new Member[array.length * 2];                           /* Create new array twice the size */
        }

        for (idx = 0; idx < array.length && array[idx] != null; idx++) {        /* Copy arrays                        */
            newArray[idx] = array[idx];                                         /* Inserting values                   */
        }

        newArray[idx] = this;                                                   /* Adding the new element             */

        return newArray;
    }

    public boolean isIn(Member[] array) {                                       /* Validasi array                     */
        for (Member m: array) if (this.id.equals(m.getID())) return true;       /* Mengecek apkah member ada di array */

        return false;                                                           /* else                               */
    }

    public String toString() {                                                  /* Representasi string member         */
        return "- %s : %s".formatted(this.id, this.nama);
    }

    @Override
    public boolean equals(Object value) {                                       /* Overriding equals methos           */
        return this.id.equals((String) value);
    }

}
