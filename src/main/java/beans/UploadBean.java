package beans;


import entities.File;
import org.postgresql.util.PSQLException;
import org.primefaces.model.UploadedFile;
import service.IUploadService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

@ManagedBean(name = "uploadBean")
@ViewScoped
public class UploadBean  implements Serializable{

    private UploadedFile file;

    private File newUploadedFile;
    private FacesContext context;

    @EJB
    private FileBean fileBean;

    @EJB
    private IUploadService uploadService;

    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
        newUploadedFile = new File();
    }

    public void upload(){
        byte[] contentInByteFormat = file.getContents();
        String fullPath = file.getFileName();

        String aux[] = fullPath.split("\\\\");
        String name = aux[aux.length - 1];

        newUploadedFile.setFilename(name);
        newUploadedFile.setContent(contentInByteFormat);

        try {
            uploadService.addFiles(newUploadedFile);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCES", "File uploaded"));
        } catch (PSQLException e) {
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
