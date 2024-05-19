package assignments.assignment3;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

import static assignments.assignment1.NotaGenerator.generateId;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class LoginManager {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public LoginManager(EmployeeSystem employeeSys, MemberSystem memberSys) {
        this.employeeSystem = employeeSys;
        this.memberSystem = memberSys;
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)) return memberSystem;

        if(employeeSystem.isMemberExist(id)) return employeeSystem;

        return null;
    }

    public Member register(String nama, String noHp, String password) {
        String id = generateId(nama, noHp);

        return memberSystem.isMemberExist(id) ? null :
        memberSystem.newMember(new Member(nama, id, password));
    }
}