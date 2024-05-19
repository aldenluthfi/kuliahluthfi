package assignments.assignment3.nota;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.user.Member;
import static assignments.assignment1.NotaGenerator.generateNota;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class Nota {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[1];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;                                                /* why not in NotaManager is beyond me*/

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;

        switch (paket.toLowerCase()) {
            case "express" -> {
                sisaHariPengerjaan = 1;
                this.baseHarga = berat * 12000;
            }
            case "fast" -> {
                sisaHariPengerjaan = 2;
                this.baseHarga = berat * 10000;
            }
            case "reguler" -> {
                sisaHariPengerjaan = 3;
                this.baseHarga = berat * 7000;
            }
        }

        this.addService(new CuciService());
    }

/*====================================***=====================================*\
|------------------------------- HELPER METHODS -------------------------------|
\*============================================================================*/

    public String getPaket() {
        return paket;
    }

    public Member getMember() {
        return member;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    public boolean isDone() {
        for (LaundryService service : services) if (service != null) {
            if (!service.isDone()) return false;
        }
        isDone = true;
        return true;
    }

    public LaundryService[] getServices(){
        return services;
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public void addService(LaundryService service){
        LaundryService[] newArray;
        int idx;

        if (services[services.length - 1] != null) {                            /* Kalau sudah penuh                  */
            newArray = new LaundryService[services.length * 2];                 /* Create new array twice the size    */
        } else {
            newArray = new LaundryService[services.length];
        }

        for (idx = 0; idx < services.length; idx++) {                           /* Copy arrays                        */
            if (services[idx] == null) break;
            newArray[idx] = services[idx];                                      /* Inserting values                   */
        }

        newArray[idx] = service;                                                /* Adding the new element             */
        services = newArray;
    }

    public String kerjakan(){
        for (LaundryService service : services) if (service != null) {
            if (!service.isDone()) {
                return "Nota %d : %s".formatted(id, service.doWork());
            }
        }
        return getNotaStatus();
    }

    public void toNextDay() {
        if (!isDone) sisaHariPengerjaan--;
    }

    public long calculateHarga(){
        long result = baseHarga;

        for (LaundryService service : services) if (service != null) {
            result += service.getHarga(berat);
        }

        if (sisaHariPengerjaan < 0) {
            result -= 2000 * Math.abs(sisaHariPengerjaan);
        }

        return result < 0 ? 0 : result;
    }

    public String getNotaStatus(){
        return "Nota %d : ".formatted(id) + (isDone() ?
            "Sudah selesai." :
            "Belum selesai."
        );
    }

    @Override
    public String toString(){
        String result = "[ID Nota = %d]\n".formatted(id);

        result += generateNota(member.getId(), paket, berat, tanggalMasuk);
        result += "\n--- SERVICE LIST ---\n";

        for (LaundryService service : services) if (service != null) {
            result += "-%s @ Rp.%d\n".formatted(
                service.getServiceName(),
                service.getHarga(berat)
            );
        }

        result += "Harga Akhir: ";

        if (sisaHariPengerjaan < 0) {
            result += "%d Ada kompensasi keterlambatan %d * 2000 hari"
                .formatted(calculateHarga(), Math.abs(sisaHariPengerjaan));
        } else {
            result += "%d".formatted(calculateHarga());
        }
        return result;
    }
}
