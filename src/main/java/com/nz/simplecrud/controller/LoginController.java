package com.nz.simplecrud.controller;

import com.nz.simplecrud.dao.LoginDAO;
import com.nz.simplecrud.util.DateUtility;
import com.nz.simplecrud.util.SessionUtils;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Login Controller class allows only authenticated users to log in to the web
 * application.
 *
 * @author Emre Simtay <emre@simtay.com>
 */
/*
@Named
@SessionScoped
*/
//@ManagedBean(name = "loginController", eager = true)
//@RequestScoped
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private transient Logger logger;
    private String uname;
    private String password;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    //  Getters and Setters
    /**
     * @return uname
     */
    public String getUname() {
        return uname;
    }

    /**
     *
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    public String login() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigationTo = "";
        try {
            String navigateString = "";
            // Checks if username and password are valid if not throws a ServletException
            boolean valid = LoginDAO.validate(uname, password);

            if (valid) {
                //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("uname", uname);
                navigationTo = "admin";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Passowrd",
                                "Please enter correct username and Password"));
                navigationTo =  "login1";
            }

            //request.login(username, password);
            // gets the user principle and navigates to the appropriate page
            /*Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("Administrator")) {
                navigateString = "/admin/AdminHome.xhtml";
            } else if (request.isUserInRole("Manager")) {
                navigateString = "/manager/ManagerHome.xhtml";
            } else if (request.isUserInRole("User")) {
                navigateString = "/user/UserHome.xhtml";
            }
            try {
                logger.log(Level.INFO, "User ({0}) loging in #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "IOException, Login Controller" + "Username : " + principal.getName(), ex);
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }*/
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }
         /*catch (ServletException e) {
            logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }*/
        // context.getExternalContext().redirect(request.getContextPath() + navigationTo);
        return navigationTo;
    }



    /**
     * Listen for button clicks on the #{loginController.login} action,
     * validates the username and password entered by the user and navigates to
     * the appropriate page.
     *
     * @param actionEvent
     */
    public String login(ActionEvent actionEvent) throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigationTo = "";
        try {
            String navigateString = "";
            // Checks if username and password are valid if not throws a ServletException
            boolean valid = LoginDAO.validate(uname, password);

            if (valid) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("uname", uname);
                navigationTo = "admin";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Passowrd",
                                "Please enter correct username and Password"));
                navigationTo =  "login1";
            }

            //request.login(username, password);
            // gets the user principle and navigates to the appropriate page
            /*Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("Administrator")) {
                navigateString = "/admin/AdminHome.xhtml";
            } else if (request.isUserInRole("Manager")) {
                navigateString = "/manager/ManagerHome.xhtml";
            } else if (request.isUserInRole("User")) {
                navigateString = "/user/UserHome.xhtml";
            }
            try {
                logger.log(Level.INFO, "User ({0}) loging in #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "IOException, Login Controller" + "Username : " + principal.getName(), ex);
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }*/
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }
         /*catch (ServletException e) {
            logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }*/
       // context.getExternalContext().redirect(request.getContextPath() + navigationTo);
        return navigationTo;
    }

    /**
     * Listen for logout button clicks on the #{loginController.logout} action
     * and navigates to login screen.
     */
    public String logout() {

        //HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpSession session = SessionUtils.getSession();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      //  logger.log(Level.INFO, "User ({0}) loging out #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
        }
        return "logout";
        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/login1.xhtml?faces-redirect=true");
    }
}
