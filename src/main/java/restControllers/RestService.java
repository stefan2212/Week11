package restControllers;


import beans.UploadBean;
import dao.IUploadDao;
import entities.File;
import org.postgresql.util.PSQLException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;
import service.IUploadService;
import service.IUserService;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/documents")
@ApplicationPath("/MyRestService")
public class RestService extends Application {

    @EJB
    private IUploadDao iUploadDao;

    @EJB
    private IUploadService uploadService;

   // http://localhost:8080/wekk11/resources/MyRestService/storeDocument
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("")
    public void postDocument(String data)
    {
        System.out.println("A venit un request de tip POST :: cu String-ul");
        JSONObject jsonObj = new JSONObject(data);

        System.out.println(jsonObj.get("name"));
        System.out.println(jsonObj.get("content"));

       /* File toUploadFile = new File();
        toUploadFile.setFilename(jsonObj.get("name").toString());
        toUploadFile.setContent(jsonObj.get("content").toString().getBytes());*/

       File toUploadFile = new File();
       toUploadFile.setContent(jsonObj.get("content").toString().getBytes());
       toUploadFile.setFilename(jsonObj.get("name").toString());

        try {
            iUploadDao.addFile(toUploadFile);
        } catch (PSQLException e) {
            System.out.println("eroare");
        }
    }
    // Content-Type application/json  header in Postman

    @DELETE
    @Path("{id}")
    public Response deleteDocument(@PathParam("id")int id)
    {
        try {
            uploadService.delete(id);
            return Response.ok("File  " + id).build();
        }
        catch(PSQLException e)
        {
            return Response.ok("psql exception").build();
        }
        catch (EJBException e)
        {
            return Response.serverError().build();
        }

    }
}
