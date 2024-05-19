package assignments.assignment3.nota;

import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NotaTest {
    private Member member;
    private int berat;
    private String paket;
    private String tanggal;

    @BeforeEach
    void setUp() {
        member = new Member("hiu", "2", "password");
        berat = 5;
        paket = "fast";
        tanggal = "01/04/2023";
        Nota.totalNota = 0;
    }

    @Test
    void testNotaInitialization() {
        Nota nota = new Nota(member, berat, paket, tanggal);

        assertEquals(paket, nota.getPaket());
        assertEquals(berat, nota.getBerat());
        assertEquals(tanggal, nota.getTanggal());
    }

    @Test
    void testAddAndGetServices() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        LaundryService additionalService = Mockito.mock(LaundryService.class);
        nota.addService(additionalService);

        List<LaundryService> services = List.of(nota.getServices());
        assertTrue(services.contains(additionalService));
    }

    @Test
    void testKerjakan() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        String expectedMessage = "Nota 0 : Sedang mencuci...";
        assertEquals(expectedMessage, nota.kerjakan());
    }

    @Test
    void testKerjakanAllDone() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        nota.kerjakan();
        String expectedMessage = "Nota 0 : Sudah selesai.";
        assertEquals(expectedMessage, nota.kerjakan());
    }

    @Test
    void testToNextDay() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        nota.toNextDay();

        assertEquals(1, nota.getSisaHariPengerjaan());
    }

    @Test
    void testCalculateHarga() {
        Nota nota = new Nota(member, berat, paket, tanggal);

        long expectedHarga = toHargaPaket(paket) * berat + new CuciService().getHarga(berat);
        assertEquals(expectedHarga, nota.calculateHarga());
    }

    @Test
    void testCalculateHargaWithLateFee() {
        Nota nota = new Nota(member, berat, paket, tanggal);

        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();

        long expectedHarga = toHargaPaket(paket) * berat + new CuciService().getHarga(berat) - 2000L;
        assertTrue(nota.toString().contains("kompensasi"));
        assertEquals(expectedHarga, nota.calculateHarga());
    }

    @Test
    void testCalculateHargaWithNoLateFee() {
        Nota nota = new Nota(member, 1, paket, tanggal);
        nota.addService(new SetrikaService());
        nota.addService(new AntarService());
        nota.kerjakan();
        nota.kerjakan();
        nota.kerjakan();
        nota.kerjakan();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();

        long expectedHarga = (toHargaPaket(paket)) + (new SetrikaService()).getHarga(1) + (new AntarService()).getHarga(4);
        assertFalse(nota.toString().contains("kompensasi"));
        assertEquals(expectedHarga, nota.calculateHarga());
    }

    @Test
    void testCalculateHargaWithLateFeeCannotBeNegative() {
        Nota nota = new Nota(member, 1, "express", tanggal);
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();

        long expectedHarga = 0;
        assertTrue(nota.toString().contains("kompensasi"));
        assertEquals(expectedHarga, nota.calculateHarga());
    }


    @Test
    void testGetNotaStatusNotDone() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        String expectedMessage = "Nota 0 : Belum selesai.";
        assertEquals(expectedMessage, nota.getNotaStatus());
    }

    @Test
    void testGetNotaStatusDone() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        nota.kerjakan();
        String expectedMessage = "Nota 0 : Sudah selesai.";
        assertEquals(expectedMessage, nota.getNotaStatus());
    }

    @Test
    void testIsDone() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        assertFalse(nota.isDone());

        nota.kerjakan();
        assertTrue(nota.isDone());
    }

    @Test
    void testToString() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        String expectedOutput = """
                [ID Nota = 0]
                ID    : 2
                Paket : fast
                Harga :
                5 kg x 10000 = 50000
                Tanggal Terima  : 01/04/2023
                Tanggal Selesai : 03/04/2023
                --- SERVICE LIST ---
                -Cuci @ Rp.0
                Harga Akhir: 50000
                """.replaceAll("\\s+","");
        assertEquals(expectedOutput, nota.toString().replaceAll("\\s+",""));
    }

    @Test
    void testToStringWithOtherService() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        nota.addService(new AntarService());
        String expectedOutput = """
                [ID Nota = 0]
                ID    : 2
                Paket : fast
                Harga :
                5 kg x 10000 = 50000
                Tanggal Terima  : 01/04/2023
                Tanggal Selesai : 03/04/2023
                --- SERVICE LIST ---
                -Cuci @ Rp.0
                -Antar @ Rp.2500
                Harga Akhir: 52500
                """.replaceAll("\\s+","");
        assertEquals(expectedOutput, nota.toString().replaceAll("\\s+",""));
    }

    @Test
    void testToStringWithOtherServiceWithLateFee() {
        Nota nota = new Nota(member, berat, paket, tanggal);
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.toNextDay();
        nota.addService(new AntarService());
        String expectedOutput = """
                [ID Nota = 0]
                ID    : 2
                Paket : fast
                Harga :
                5 kg x 10000 = 50000
                Tanggal Terima  : 01/04/2023
                Tanggal Selesai : 03/04/2023
                --- SERVICE LIST ---
                -Cuci @ Rp.0
                -Antar @ Rp.2500
                Harga Akhir: 46500 Ada kompensasi keterlambatan 3 * 2000 hari
                """.replaceAll("\\s+","");
        assertEquals(expectedOutput, nota.toString().replaceAll("\\s+",""));
    }

    public static long toHargaPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express"))
            return 12000;
        if (paket.equals("fast"))
            return 10000;
        if (paket.equals("reguler"))
            return 7000;
        return -1;
    }
}
