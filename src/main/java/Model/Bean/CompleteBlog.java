package Model.Bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kaushik.p on 3/30/16.
 */
@AllArgsConstructor
@Getter
public class CompleteBlog {
    @JsonProperty("ID")
    private String id ;//= UUID.randomUUID().toString().replace("-","");

    @NotBlank
    @JsonProperty("TITLE")
    private String title;


    @JsonProperty("GENRE")
    private String description;

    @NotBlank
    @JsonProperty("DESCRIPTION")
    private String genre;



//    private String[] tags;

    @JsonProperty("PUBLISHED_ON")
    private Timestamp publishedOn ;

    @URL
    @NotBlank
    @JsonProperty("URLS")
    private List<String> urls;




//    @Override
//    public String toString(){
//        JSONObject blogString = new JSONObject();
//        blogString.put("ID", this.id);
//        blogString.put("TITLE", this.title);
//        blogString.put("GENRE", this.genre);
//        blogString.put("DESCRIPTION", this.description);
//        blogString.put("PUBLISHED_ON", this.publishedOn);
//        blogString.put("URLS", urls);
//
//        return blogString.toJSONString();
//    }

}
