package dao;

import entities.File;
import org.postgresql.util.PSQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UploadDao implements IUploadDao {
    @PersistenceContext(unitName = "postgres") private EntityManager entityManager;

    @Override
    public void addFile(File uploadFile) throws PSQLException {
        entityManager.persist(uploadFile);
    }

    @Override
    public List<File> getUploadedFiles(){
        Query query = entityManager.createQuery("SELECT f FROM File f ");
        return query.getResultList(); //???????????
    }

    @Override
    public void deleteFile(int id) throws PSQLException {
        File toDeleteFile = entityManager.find(File.class,id);
        entityManager.remove(toDeleteFile);
    }


}
