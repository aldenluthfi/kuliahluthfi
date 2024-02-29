public abstract class Employee {                                                /* because we dont need an instance   */

    private String nama, jabatan;                                               /* Private fields                     */
    private int pengalamanKerja;
    private boolean status;
    private double netWorth, gaji;

    public abstract void nextYears(int tahun);                                  /* abstract methods                   */
    public abstract void updateRole();                                          /* Implemented in the subclass        */
    public abstract String getRole();

    Employee(String nama) {
        this.nama = nama;
        this.status = true;
        this.jabatan = "Junior";
    }

    public String toString() {                                                  /* Returns a formatted string         */
        return """
            \r\n- Nama: %s
            \rPengalaman Kerja: %d
            \rStatus: %b
            \rNetWorth: Rp%.2f
            \rJabatan: %s
            \rRole: %s
        """
            .formatted(
                nama,
                pengalamanKerja > 15 ? 16 : pengalamanKerja,                    /* Pengalaman stops at 15             */
                status,
                netWorth,
                jabatan,
                this.getRole()
            );
    };

    public void deactivate() {                                                  /* deactivate employee                */
        status = false;
    }

    public void addPengalamanKerja(){                                           /* Increment pengalaman kerja         */
        pengalamanKerja++;
    }

    public String getNama() {                                                   /* A lot of getters                   */
        return nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public int getPengalamanKerja(){
        return pengalamanKerja;
    }

    public double getNetWorth(){
        return netWorth;
    }

    public double getGaji() {
        return gaji;
    }

    public boolean getStatus() {
        return status;
    }

    public void setNama(String nama) {                                          /* a lot of setters                   */
        this.nama = nama;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setNetWorth(double netWorth){
        this.netWorth = netWorth;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

}