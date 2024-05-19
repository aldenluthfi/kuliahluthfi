public class Secretary extends Employee {

    private final double tunjangan;

    Secretary (String nama, double tunjangan) {                                 /* Constructor sesuai soal            */
        super(nama);

        this.tunjangan = tunjangan;

        setGaji(3_000_000);
    }

    @Override
    public String getRole() {                                                   /* Returns the role                   */
        return "Secretary";
    }

    @Override
    public void updateRole(){                                                   /* Updates the role                   */
        if (getPengalamanKerja() > 15) setJabatan("Pensiun");

        else if (getPengalamanKerja() > 10) {
            setJabatan("Expert");
            setGaji(9_000_000);
        }

        else if (getPengalamanKerja() > 5) {
            setJabatan("Senior");
            setGaji(6_000_000);
        }
    }

    @Override
    public String toString() {                                                  /* adds tunjangan                     */
        return super.toString() + "Banyak Tunjangan: %.2f\n"
            .formatted(tunjangan);
    }

    @Override
    public void nextYears(int n){                                              /* Simulasi setelah gajian n tahun    */
        for (int i = 0; i < n; i++) {
            addPengalamanKerja();
            updateRole();

            if (getPengalamanKerja() > 15) deactivate();
            if (getStatus()) {
                setNetWorth(
                    getNetWorth() +
                    getGaji() +
                    tunjangan
                );
            }
        }
    }
}