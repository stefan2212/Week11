package beans;

import entities.User;
import service.IUserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable{

    private User newUser;
    private String password;

    private FacesContext context;

    @EJB
    private IUserService userService;

    @PostConstruct
    public void init(){
        newUser = new User();
    }

    public void addUser(ActionEvent event){
        context = FacesContext.getCurrentInstance();
        try {
            userService.addUser(newUser);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Successful",  "Added username: " + newUser) );
            context.addMessage(null, new FacesMessage("Redirecting to login..."));
            newUser = new User();

            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath()+"/index.xhtml");
        } catch (Exception e) {
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR ,"ERROR", newUser + " already exists") );
        }
    }


    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
