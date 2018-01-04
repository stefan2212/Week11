package beans;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class FileBean {

    private List<String> fileNames;

    @PostConstruct
    public void init(){
        fileNames = new ArrayList<>();
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
