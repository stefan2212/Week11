package entities;

import javax.persistence.*;

@Entity
@Table(name="files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Column(unique=true,name="filename")
    private String filename;

    @Column(name = "content")
    private byte[] content;
}
