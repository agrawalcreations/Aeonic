package login;

/**
 * Created by raviagrawal on 27/7/16.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean(name = "userLogin", eager = true)
@RequestScoped
public class UserLogin {
    private String message ="Enter username and password.";
    private String username;
    private String password;
    public String login(){
        if("1".equalsIgnoreCase(username) && "1".equalsIgnoreCase(password)) {
            message ="Successfully logged-in.";
            return "success";
        } else {
            message ="Wrong credentials.";
            return "login";
        }
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
