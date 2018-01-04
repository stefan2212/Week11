package beans;


import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "uploadBean")
@ViewScoped
public class UploadBean  implements Serializable{

    private UploadedFile file;

    private FacesContext context;

    @EJB
    private FileBean fileBean;

    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
    }

    public void upload(){
        if(file != null){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "File uploaded"));
        }
        else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Something went wrong"));
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
