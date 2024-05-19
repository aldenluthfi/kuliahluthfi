package assignments.assignment3.nota;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class NotaManager {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    public static Nota[] notaList = new Nota[1];

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public static void toNextDay(){
        for (Nota nota : notaList) if (nota != null) {
            nota.toNextDay();
        }
        cal.add(Calendar.DATE, 1);
    }

    public static void addNota(Nota nota){
        Nota[] newArray;
        int idx;

        if (notaList.length == 0) notaList = new Nota[1];

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
}
