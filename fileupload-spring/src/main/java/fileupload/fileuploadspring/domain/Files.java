package fileupload.fileuploadspring.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class Files {
    @Id
    @GeneratedValue()
    int fno;

    String filename;
    String fileOriName;
    String fileurl;
}
