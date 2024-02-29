package assignments.assignment4.gui.member;

public interface Loginable {                                                    /* its either a member or a staff     */
    boolean login(String id, String password);                                  /* each type has unique credentials   */
    void logout();
    String getPageName();

}
