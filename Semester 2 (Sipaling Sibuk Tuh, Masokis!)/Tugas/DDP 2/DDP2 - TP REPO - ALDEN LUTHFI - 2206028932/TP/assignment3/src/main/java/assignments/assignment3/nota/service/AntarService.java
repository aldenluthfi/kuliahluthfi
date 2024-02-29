package assignments.assignment3.nota.service;

public class AntarService implements LaundryService {

    private boolean isDone;

    @Override
    public String doWork() {
        isDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public long getHarga(int berat) {                                           /* buat cek kalo belum 4              */
        return 2000 + (500 * (((berat - 4) >>> 31 ^ 1) == 0 ? 0 : berat - 4));  /* Bit twiddling magic                */
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
