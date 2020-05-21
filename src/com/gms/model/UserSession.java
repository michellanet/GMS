package com.gms.model;

/* User session Class is used when user logs in. That mean after logging in a new session should be created.
    there could be only one session. that's why the class is singleton.
    this session is cleared on logging out and set on logging in.
 */
public class UserSession {
    private static User user;
    private static UserSession userSession = new UserSession();
    private UserSession(){

    }
    public static UserSession getInstance(){
        return userSession;
    }
    public void setSession(User user){
        this.user = new User();
        this.user.setId(user.getId());
        this.user.setUser_type(user.getUser_type());
        this.user.setUsername(user.getUsername());
        this.user.setPassword(user.getPassword());
    }
    public User getSession(){
        return this.user;
    }
    public void clearSession(){
        this.user = null;
    }
}
