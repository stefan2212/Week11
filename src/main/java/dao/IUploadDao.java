package dao;

import entities.File;
import org.postgresql.util.PSQLException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUploadDao {

    void addFile(File uploadFile) throws PSQLException;

    public List<File> getUploadedFiles();

    void deleteFile(int id)  throws PSQLException;

}
