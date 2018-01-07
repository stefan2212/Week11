package service;

import dao.IUploadDao;
import entities.File;
import org.postgresql.util.PSQLException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UploadService implements IUploadService {

    @EJB
    private IUploadDao iUploadDao;


    @Override
    public void addFiles(File newFile) throws PSQLException {
        iUploadDao.addFile(newFile);
    }

    @Override
    public List<String> getUploadedFiles() {
        List<File> listOfFiles =  iUploadDao.getUploadedFiles();
        List<String> listOfFileNames=new ArrayList<String>();

        for(File file : listOfFiles) {
            listOfFileNames.add(file.getFilename());
        }
        return listOfFileNames;
    }
}
