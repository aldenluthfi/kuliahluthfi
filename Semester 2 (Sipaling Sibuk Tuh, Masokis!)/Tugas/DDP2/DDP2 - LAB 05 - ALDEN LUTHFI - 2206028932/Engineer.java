public class Engineer extends Employee {

    private final int banyakSideJobs;

    Engineer (String nama, int banyakSideJobs){                                 /* Constructor sesuai soal            */
        super(nama);

        this.banyakSideJobs = banyakSideJobs;

        setGaji(4_000_000);
    }

    @Override
    public String getRole() {                                                   /* Returns the role                   */
        return "Engineer";
    }

    @Override
    public void updateRole(){                                                   /* Updates the role                   */
        if (getPengalamanKerja() > 15) setJabatan("Pensiun");

        else if (getPengalamanKerja() > 10) {
            setJabatan("Expert");
            setGaji(12_000_000);
        }

        else if (getPengalamanKerja() > 5) {
            setJabatan("Senior");
            setGaji(8_000_000);
        }
    }

    @Override
    public String toString() {                                                  /* adds side job                      */
        return super.toString() + "Banyak SideJobs: %d\n"
            .formatted(banyakSideJobs);
    }

    @Override
    public void nextYears(int n) {                                              /* Simulasi setelah gajian n tahun    */
        for (int i = 0; i < n; i++) {
            addPengalamanKerja();
            updateRole();

            if (getPengalamanKerja() > 15) deactivate();
            if (getStatus()) {
                setNetWorth(
                    getNetWorth() +
                    getGaji() +
                    banyakSideJobs * 500_000
                );
            }
        }
    }
}