package beans;

import entities.User;
import service.IUserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean  implements Serializable{

    private User user;

    @EJB
    private IUserService userService;

    private FacesContext context;

    @PostConstruct
    public void init(){
        user = new User();
    }

    public void verifyUser(ActionEvent event){

        context = FacesContext.getCurrentInstance();
        if (userService.verifUser(user)) {
            try {
                context.addMessage(null, new FacesMessage("Successful logged in"));
                context.getExternalContext()
                        .redirect(context.getExternalContext().getRequestContextPath() + "/upload.xhtml");
            } catch(Exception e){
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Something went wrong"));
            }
        }
        else
            context.addMessage(null, new FacesMessage("Username or password are incorrect "));

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
