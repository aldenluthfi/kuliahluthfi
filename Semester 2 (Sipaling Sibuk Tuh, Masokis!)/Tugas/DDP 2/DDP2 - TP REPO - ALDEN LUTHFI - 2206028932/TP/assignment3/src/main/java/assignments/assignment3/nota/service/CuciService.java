package assignments.assignment3.nota.service;

public class CuciService implements LaundryService {

    private boolean isDone;

    @Override
    public String doWork() {
        isDone = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
