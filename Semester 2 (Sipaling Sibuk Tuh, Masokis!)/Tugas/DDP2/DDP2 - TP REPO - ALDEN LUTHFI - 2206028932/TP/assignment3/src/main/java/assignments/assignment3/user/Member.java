package assignments.assignment3.user;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.nota.Nota;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class Member {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[1];

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

/*====================================***=====================================*\
|------------------------------- HELPER METHODS -------------------------------|
\*============================================================================*/

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    public void addNota(Nota nota) {
        Nota[] newArray;
        int idx;

        if (notaList[notaList.length - 1] != null) {                            /* Kalau sudah penuh                  */
            newArray = new Nota[notaList.length * 2];                           /* Create new array twice the size */
        } else {
            newArray = new Nota[notaList.length];
        }

        for (idx = 0; idx < notaList.length; idx++) {                           /* Copy arrays                        */
            if (notaList[idx] == null) break;
            newArray[idx] = notaList[idx];                                      /* Inserting values                   */
        }

        newArray[idx] = nota;                                                   /* Adding the new element             */
        notaList = newArray;
    }

    protected boolean authenticate(String password) {
        return this.password.equals(password);
    }
}