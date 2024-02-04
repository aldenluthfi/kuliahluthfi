import java.io.*;
import java.util.*;

class TP2 {

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------------ MEDAN -----------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    private static Pencatatan catat;
    private static PrintWriter cetak;

    static Siswa[] yayasan = new Siswa[1500000];
    static Sekolah sekolah;

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------ STRUKTUR DATA ---------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static class Sekolah {
        Kelas kepala;
        Kelas tunjuk;
        Kelas buntut;

        int panjang;

        Kelas urut(Kelas esa) {
            Kelas dwi, tri, sah;

            if (esa == null || esa.per == null)
                return esa;

            Kelas lincah = esa, lambat = esa, tengah;

            while (lincah.per != null && lincah.per.per != null) {
                lincah = lincah.per.per;
                lambat = lambat.per;
            }

            tengah = lambat.per;

            lambat.per = null;
            tengah.pra = null;

            esa = urut(esa);
            dwi = urut(tengah);

            tri = new Kelas(true);
            sah = tri;

            while (esa != null && dwi != null) {
                if (esa.compareTo(dwi) <= 0) {
                    tri.per = esa;
                    esa.pra = tri;

                    esa = esa.per;

                    if (esa != null) {
                        esa.pra.per = null;
                        esa.pra = null;
                    }
                } else {
                    tri.per = dwi;
                    dwi.pra = tri;

                    dwi = dwi.per;

                    if (dwi != null) {
                        dwi.pra.per = null;
                        esa.pra = null;
                    }
                }

                tri = tri.per;
            }

            if (esa == null) {
                tri.per = dwi;
                dwi.pra = tri;
            }

            if (dwi == null) {
                tri.per = esa;
                esa.pra = tri;
            }

            while (tri.per != null) {
                tri = tri.per;
            }

            buntut = tri;
            kepala = sah.per;

            kepala.pra = null;

            return kepala;
        }

        void masuk(Kelas tautan) {

            panjang++;

            if (null == tunjuk) {
                kepala = tautan;
                tunjuk = tautan;
                buntut = tautan;
            }

            if (panjang == 1)
                return;

            buntut.per = tautan;
            tautan.pra = buntut;

            buntut = tautan;
        }

        void hapus() {
            if (kepala == buntut)
                kepala = tunjuk = buntut = null;

            else if (tunjuk == kepala) {
                tunjuk.per.pra = tunjuk.pra;
                tunjuk = kepala = tunjuk.per;
            }

            else if (tunjuk == buntut) {
                tunjuk.pra.per = tunjuk.per;
                tunjuk = buntut = tunjuk.pra;
            }

            else {
                tunjuk.pra.per = tunjuk.per;
                tunjuk.per.pra = tunjuk.pra;

                tunjuk = tunjuk.per;
            }

            panjang--;
        }

        @Override
        public String toString() {
            return Percetakan.daftar(kepala);
        }
    }

    static class Kelas implements Comparable<Kelas> {
        static int jumlahKelas;

        Siswa top;
        Kelas pra;
        Kelas per;

        int plang, total;

        Kelas(boolean bohongan) {
            this.plang = bohongan ? -1 : ++jumlahKelas;
        }

        Siswa masuk(Siswa daun, Siswa baru) {
            if (daun == null)
                return baru;
            else if (baru.compareTo(daun) < 0)
                daun.kidal = masuk(daun.kidal, baru);
            else if (baru.compareTo(daun) > 0)
                daun.kanan = masuk(daun.kanan, baru);

            daun.kedalaman = perdalami(daun);
            daun.keturunan = penurunan(daun);
            daun = seimbangi(daun);

            return daun;
        }

        Siswa hapus(Siswa daun, Siswa cari) {
            Siswa bakal;

            if (daun == null)
                return daun;

            if (daun.compareTo(cari) < 0)
                daun.kanan = hapus(daun.kanan, cari);

            else if (daun.compareTo(cari) > 0)
                daun.kidal = hapus(daun.kidal, cari);

            else {
                if (daun.kanan == null || daun.kidal == null) {
                    if (daun.kidal == null)
                        daun  = daun.kanan;
                    else
                        daun = daun.kidal;
                } else {
                    bakal = tersulung(daun.kidal);
                    daun.kidal = hapus(daun.kidal, bakal);

                    bakal.kidal = daun.kidal;
                    bakal.kanan = daun.kanan;

                    daun = bakal;
                }
            }

            if (daun == null)
                return daun;

            segarkan(daun);
            daun = seimbangi(daun);

            return daun;
        }

        Siswa cari(Siswa daun, Siswa cari) {
            if (daun == null || cari == null)
                return null;
            if (daun.compareTo(cari) < 0)
                return cari(daun.kanan, cari);
            else if (daun.compareTo(cari) > 0)
                return cari(daun.kidal, cari);
            else
                return daun;
        }

        int didik(int nilai) {
            Siswa daun = top;
            int hitung = 0;

            while (daun != null) {
                if (daun.nilai <= nilai) {
                    hitung += keturunan(daun) - keturunan(daun.kanan);
                    daun = daun.kanan;
                } else
                    daun = daun.kidal;
            }

            return hitung - 1;
        }

        void daftar(Siswa siswa) {
            top = masuk(top, siswa);

            total += siswa.nilai;
        }

        void keluar(Siswa siswa) {
            top = hapus(top, siswa);

            siswa.kidal = null;
            siswa.kanan = null;
            segarkan(siswa);

            total -= siswa.nilai;
        }

        Siswa seimbangi(Siswa daun) {
            int keseimbangan = keseimbangan(daun);
            if (keseimbangan > 1) {
                if (kedalaman(daun.kidal.kidal) >= kedalaman(daun.kidal.kanan))
                    daun = rotaka(daun);
                else {
                    daun.kidal = rotaki(daun.kidal);
                    daun = rotaka(daun);
                }
            } else if (keseimbangan < -1) {
                if (kedalaman(daun.kanan.kanan) >= kedalaman(daun.kanan.kidal))
                    daun = rotaki(daun);
                else {
                    daun.kanan = rotaka(daun.kanan);
                    daun = rotaki(daun);
                }
            }

            return daun;
        }

        Siswa rotaki(Siswa daun) {
            Siswa akar = daun.kanan;
            Siswa kananBaru = akar.kidal;

            akar.kidal = daun;
            daun.kanan = kananBaru;

            segarkan(daun);
            segarkan(akar);

            return akar;
        }

        Siswa rotaka(Siswa daun) {
            Siswa akar = daun.kidal;
            Siswa kidalBaru = akar.kanan;

            akar.kanan = daun;
            daun.kidal = kidalBaru;

            segarkan(daun);
            segarkan(akar);

            return akar;
        }

        void segarkan(Siswa daun) {
            if (daun != null) {
                daun.kedalaman = perdalami(daun);
                daun.keturunan = penurunan(daun);
            }
        }

        Siswa tersulung(Siswa daun) {
            while (daun.kanan != null)
                daun = daun.kanan;
            return daun;
        }

        Siswa terbungsu(Siswa daun) {
             while (daun.kidal != null)
                daun = daun.kidal;
            return daun;
        }

        int kedalaman(Siswa daun) {
            return daun == null ? 0 : daun.kedalaman;
        }

        int keturunan(Siswa daun) {
            return daun == null ? 0 : daun.keturunan;
        }

        int penurunan(Siswa daun) {
            return 1 + keturunan(daun.kidal) + keturunan(daun.kanan);
        }

        int perdalami(Siswa daun) {
            return 1 + Math.max(kedalaman(daun.kidal), kedalaman(daun.kanan));
        }

        int keseimbangan(Siswa daun) {
            return kedalaman(daun.kidal) - kedalaman(daun.kanan);
        }

        @Override
        public int compareTo(Kelas lain) {
            long esa, dwi;

            esa = (long) this.total * (long) keturunan(lain.top);
            dwi = (long) lain.total * (long) keturunan(this.top);

            int cekRerata = -Long.compare(esa, dwi);

            if (cekRerata == 0)
                return Integer.compare(this.plang, lain.plang);

            return cekRerata;
        }

        @Override
        public String toString() {
            String result, absen;

            result = "";
            absen = String.format("-%d", top.absen);

            result += Percetakan.pohon("    ", top.kanan, true, true);
            result += "── " + top.nilai + absen + "\n";
            result += Percetakan.pohon("    ", top.kidal, false, false);

            return result;
        }
    }

    static class Siswa implements Comparable<Siswa> {
        static int jumlahSiswa;

        int nilai;
        int absen;
        int licik;

        Siswa kidal;
        Siswa kanan;

        int kedalaman;
        int keturunan;

        Siswa (int nilai) {
            this.nilai = nilai;
            this.absen = ++jumlahSiswa;

            kedalaman = 1;
            keturunan = 1;
        }

        @Override
        public int compareTo(Siswa lain) {
            int cekNilai = Integer.compare(nilai, lain.nilai);

            if (cekNilai == 0)
                return -Integer.compare(absen, lain.absen);

            return cekNilai;
        }
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------- MASUKAN/KELUARAN -----------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static class Pencatatan {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Pencatatan(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class Percetakan {
        static String pohon(String prefiks, Siswa daun, boolean... arah) {
            String result, prefiksKidal, prefiksKanan, prefiksSiswa, absen;

            result = "";

            boolean sisi = arah[0];
            boolean luar = arah[1];

            if (daun != null) {
                prefiksKanan = prefiks + (luar ? "      " : "│     ");
                prefiksSiswa = prefiks + (sisi ? "┌─── "  : "└─── " );
                prefiksKidal = prefiks + (luar ? "│     " : "      ");

                absen = String.format("-%d", daun.absen);

                result += pohon(prefiksKanan, daun.kanan, true, true);
                result += prefiksSiswa + daun.nilai + absen + "\n";
                result += pohon(prefiksKidal, daun.kidal, false, false);
            }

            return result;
        }

        static String daftar(Kelas tautan) {
            String result = "";
            double rerata;

            while (tautan != null) {
                rerata = tautan.total / (double) tautan.keturunan(tautan.top);

                result += String.format("%.3f-%d[", rerata, tautan.plang);

                result += (tautan == sekolah.kepala ? "*" : "");
                result += (tautan == sekolah.tunjuk ? "*" : "");
                result += (tautan == sekolah.buntut ? "*" : "");

                result += (tautan.per != null ? "] → " : "]");

                tautan = tautan.per;
            }

            return result;
        }
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|---------------------------------- PERINTAH ----------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static String T() {
        int nilai, bonus;
        Siswa siswa;
        Kelas kelas;

        nilai = catat.nextInt();
        kelas = sekolah.tunjuk;
        siswa = yayasan[catat.nextInt() - 1];
        siswa = kelas.cari(kelas.top, siswa);

        if (siswa == null)
            return "-1";

        bonus = kelas.didik(siswa.nilai);
        bonus = Math.min(nilai, bonus);

        kelas.keluar(siswa);
        siswa.nilai += nilai + bonus;
        kelas.daftar(siswa);

        return Integer.toString(siswa.nilai);
    }

    static String C() {
        Siswa siswa, rekan;
        Kelas kelas, bawah;
        String result;

        kelas = sekolah.tunjuk;
        siswa = yayasan[catat.nextInt() - 1];
        siswa = kelas.cari(kelas.top, siswa);
        bawah = sekolah.buntut;

        if (siswa == null)
            return "-1";

        if (siswa.licik == 0) {
            kelas.keluar(siswa);
            siswa.nilai = 0;
            kelas.daftar(siswa);

            result = Integer.toString(siswa.nilai);
        } else if (siswa.licik == 1) {
            kelas.keluar(siswa);
            siswa.nilai = 0;
            bawah.daftar(siswa);

            result = Integer.toString(bawah.plang);
        } else {
            kelas.keluar(siswa);

            result = Integer.toString(siswa.absen);
        }

        if (kelas.keturunan(kelas.top) < 6) {
            sekolah.hapus();

            while (kelas.top != null) {
                rekan = kelas.tersulung(kelas.top);
                kelas.keluar(rekan);
                sekolah.tunjuk.daftar(rekan);
            }
        }

        siswa.licik++;
        return result;
    }

    static String G() {
        String arah = catat.next();

        if (arah.equals("L")) {
            if (sekolah.tunjuk.pra == null)
                sekolah.tunjuk = sekolah.buntut;
            else
                sekolah.tunjuk = sekolah.tunjuk.pra;
        } else {
            if (sekolah.tunjuk.per == null)
                sekolah.tunjuk = sekolah.kepala;
            else
                sekolah.tunjuk = sekolah.tunjuk.per;
        }

        return Integer.toString(sekolah.tunjuk.plang);
    }

    static String S() {
        Siswa[] terbagusB = new Siswa[3];
        Siswa[] terburukA = new Siswa[3];
        Siswa[] terbagusC = new Siswa[3];
        Siswa[] terburukC = new Siswa[3];

        Kelas a, b, c;
        String juara, beban;

        c = sekolah.tunjuk;

        if (c == sekolah.buntut) {
            a = c.pra;

            for (int i = 0; i < 3; i++) {
                terburukA[i] = a.terbungsu(a.top);
                a.keluar(terburukA[i]);

                terbagusC[i] = c.tersulung(c.top);
                c.keluar(terbagusC[i]);
            }

            for (int i = 0; i < 3; i++) {
                a.daftar(terbagusC[i]);
                c.daftar(terburukA[i]);
            }
        } else if (c == sekolah.kepala) {
            b = c.per;

            for (int i = 0; i < 3; i++) {
                terbagusB[i] = b.tersulung(b.top);
                b.keluar(terbagusB[i]);

                terburukC[i] = c.terbungsu(c.top);
                c.keluar(terburukC[i]);
            }

            for (int i = 0; i < 3; i++) {
                b.daftar(terburukC[i]);
                c.daftar(terbagusB[i]);
            }
        } else {
            a = c.pra;
            b = c.per;

            for (int i = 0; i < 3; i++) {
                terbagusB[i] = b.tersulung(b.top);
                b.keluar(terbagusB[i]);

                terburukC[i] = c.terbungsu(c.top);
                c.keluar(terburukC[i]);

                terburukA[i] = a.terbungsu(a.top);
                a.keluar(terburukA[i]);

                terbagusC[i] = c.tersulung(c.top);
                c.keluar(terbagusC[i]);
            }

            for (int i = 0; i < 3; i++) {
                b.daftar(terburukC[i]);
                c.daftar(terbagusB[i]);
                a.daftar(terbagusC[i]);
                c.daftar(terburukA[i]);
            }
        }

        juara = Integer.toString(c.tersulung(c.top).absen);
        beban = Integer.toString(c.terbungsu(c.top).absen);

        return juara + " " + beban;
    }

    static String K() {
        int hitung = 1;
        sekolah.kepala = sekolah.urut(sekolah.kepala);

        Kelas tautan = sekolah.kepala;

        while (tautan != sekolah.tunjuk) {
            hitung++;
            tautan = tautan.per;
        }

        return Integer.toString(hitung);
    }

    static String A() {

        int absensi = catat.nextInt();
        Kelas kelas = new Kelas(false);

        for (int i = 0; i < absensi; i++) {
            Siswa siswa = new Siswa(0);

            yayasan[siswa.absen - 1] = siswa;
            kelas.daftar(siswa);
        }

        sekolah.masuk(kelas);

        return Integer.toString(kelas.plang);
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------- FUNGSI UTAMA --------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    public static void main(String[] args) {
        catat = new Pencatatan(System.in);
        cetak = new PrintWriter(System.out);

        sekolah = new Sekolah();

        int M = catat.nextInt();
        int[] kapasitasKelas = new int[M];

        for (int i = 0; i < M; i++)
            kapasitasKelas[i] = catat.nextInt();

        for (int i: kapasitasKelas) {
            Kelas kelas = new Kelas(false);

            for (int j = 0; j < i; j++) {
                Siswa siswa = new Siswa(catat.nextInt());

                yayasan[siswa.absen - 1] = siswa;
                kelas.daftar(siswa);
            }
            sekolah.masuk(kelas);
        }

        int Q = catat.nextInt();

        while (--Q >= 0) {
            String perintah = catat.next();

            if (perintah.equals("T"))
                cetak.println(T());
            else if (perintah.equals("C"))
                cetak.println(C());
            else if (perintah.equals("G"))
                cetak.println(G());
            else if (perintah.equals("S"))
                cetak.println(S());
            else if (perintah.equals("K"))
                cetak.println(K());
            else if (perintah.equals("A"))
                cetak.println(A());
        }

        cetak.close();
    }
}