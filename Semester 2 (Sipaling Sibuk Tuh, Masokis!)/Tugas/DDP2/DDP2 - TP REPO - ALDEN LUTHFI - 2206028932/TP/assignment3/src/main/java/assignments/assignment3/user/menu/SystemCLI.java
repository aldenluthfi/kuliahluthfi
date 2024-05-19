package assignments.assignment3.user.menu;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.user.Member;
import java.util.Scanner;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|--------------------------------------------------- ABSTRACT CLASS ---------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public abstract class SystemCLI {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    protected Member[] memberList = new Member[1];
    protected Member loginMember;
    protected Scanner in;

    protected abstract boolean processChoice(int choice);
    protected abstract void displaySpecificMenu();

/*====================================***=====================================*\
|--------------------------------- IO METHODS ---------------------------------|
\*============================================================================*/

    public void outln(Object x) {                                               /* Shortcut untuk println statement   */
        System.out.println(x);
    }

    public void outf(String format, Object... args) {                           /* Shortcut untuk printf statement    */
        System.out.printf(format, args);
    }

    public void out(Object x) {                                                 /* Shorcut untuk print statement      */
        System.out.print(x);
    }

    protected void displayMenu(){
        outf(
            "\nLogin as : %s\nSelamat datang %s!\n\n",
            loginMember.getId(),
            loginMember.getNama()
        );

        displaySpecificMenu();

        out("Apa yang ingin Anda lakukan hari ini? ");
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public void login(Scanner in, String inputId, String inputPassword){
        Member authMember = authUser(inputId, inputPassword);

        if (authMember != null) {
            this.in = in;
            outln("Login successful!");
            run(in, authMember);
            return;
        }

        outln("Invalid ID or password.");
    };

    public Member login(String inputId, String inputPassword){
        return authUser(inputId, inputPassword);
    };

    public void run(Scanner in, Member member){
        loginMember = member;

        boolean logout = false;

        while (!logout) {
            displayMenu();
            logout = processChoice(Integer.parseInt(in.nextLine()));
        }

        loginMember = null;
        outln("Logging out...\n");
    }

    public Member authUser(String id, String pass) {
        for (Member user : memberList) if (user != null) {
            if (!user.getId().equals(id)) continue;
            return user.login(id, pass) ? user : null;
        }
        return null;
    };

    public boolean isMemberExist(String id){
        for (Member member: memberList) if (member != null) {
            if(member.getId().equals(id)) return true;
        }
        return false;
    }
}