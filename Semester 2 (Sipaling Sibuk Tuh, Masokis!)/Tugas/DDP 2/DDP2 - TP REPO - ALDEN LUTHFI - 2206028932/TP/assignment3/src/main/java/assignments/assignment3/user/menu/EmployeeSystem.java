package assignments.assignment3.user.menu;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class EmployeeSystem extends SystemCLI {

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public EmployeeSystem() {
        Member[] employeeList = {
            new Employee("Dek Depe", "akuDDP"),
            new Employee("Depram", "musiktualembut"),
            new Employee("Lita Duo", "gitCommitPush"),
            new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };

        this.memberList = employeeList;
    }

/*====================================***=====================================*\
|--------------------------------- IO METHODS ---------------------------------|
\*============================================================================*/

    @Override
    protected void displaySpecificMenu() {
        outln("1. It's nyuci time");
        outln("2. Display List Nota");
        outln("3. Logout");
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public void addEmployee(Employee[] employees) {
        for (Employee employee : employees) {
            addEmployee(employee);
        }
    }

    public void addEmployee(Employee employee) {
        Member[] newArray;
        int idx;

        if (memberList[memberList.length - 1] != null) {                        /* Kalau sudah penuh                  */
            newArray = new Employee[memberList.length * 2];                     /* Create new array twice the size */
        } else {
            newArray = new Employee[memberList.length];
        }

        for (idx = 0; idx < memberList.length; idx++) {                         /* Copy arrays                        */
            if (memberList[idx] == null) break;
            newArray[idx] = memberList[idx];                                    /* Inserting values                   */
        }

        newArray[idx] = employee;                                               /* Adding the new element             */
        memberList = newArray;
    }

    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;

        switch (choice) {
            case 1 -> nyuciTime();
            case 2 -> diplayListNota(notaList);
            case 3 -> logout = true;
        }

        return logout;
    }

    private void diplayListNota(Nota[] list) {
        for (Nota nota : list) if (nota != null) outln(nota.getNotaStatus());
    }

    private void nyuciTime() {
        outf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());

        for (Nota nota: notaList) if (nota != null) {
            outln(nota.kerjakan());
        }
    }
}