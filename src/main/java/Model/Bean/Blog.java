package Model.Bean;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Getter
public class Blog {

    private String id ;//= UUID.randomUUID().toString().replace("-","");

    @NotBlank
    private String title;

//    @URL
//    @NotBlank
//    private String[] urls;


    @NotBlank
    private String genre;


    private String description;

//    private String[] tags;

    private Timestamp publishedOn ;//= new Date(new java.util.Date().getTime());

}