package service;

import entities.File;
import org.postgresql.util.PSQLException;
import org.primefaces.component.fileupload.FileUpload;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUploadService {
    void addFiles(File newFile) throws PSQLException;

    void delete(int id) throws PSQLException;

    List<String> getUploadedFiles();

}
