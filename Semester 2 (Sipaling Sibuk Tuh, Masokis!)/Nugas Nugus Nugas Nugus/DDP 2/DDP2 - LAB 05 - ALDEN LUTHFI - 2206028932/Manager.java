public class Manager extends Employee {

    private final double raise;

    Manager (String nama, double raise) {                                       /* Constructor sesuai soal            */
        super(nama);

        this.raise = raise;

        setGaji(2_000_000);
    }

    @Override
    public String getRole() {                                                   /* Returns the role                   */
        return "Manager";
    }

    @Override
    public void updateRole() {                                                  /* Updates the role                   */
        if (getPengalamanKerja() > 15) setJabatan("Pensiun");
        else if (getPengalamanKerja() > 10) setJabatan("Expert");
        else if (getPengalamanKerja() > 5) setJabatan("Senior");
    }

    @Override
    public String toString() {                                                  /* adds nothing new                   */
        return super.toString();
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
                    getGaji() * raise
                );
            }

            setGaji(getGaji() * raise);
        }
    }

}