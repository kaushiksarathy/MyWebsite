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


    @NotBlank
    @JsonProperty("DESCRIPTION")
    private String genre;


    @JsonProperty("GENRE")
    private String description;

//    private String[] tags;

    @JsonProperty("PUBLISHED_ON")
    private Timestamp publishedOn ;

    @URL
    @NotBlank
    @JsonProperty("URLS")
    private List<String> urls;




    @Override
    public String toString(){
        JSONObject blogString = new JSONObject();
        blogString.put("ID", this.getId());
        blogString.put("TITLE", this.getTitle());
        blogString.put("DESCRIPTION", this.getDescription());
        blogString.put("GENRE", this.getGenre());
        blogString.put("PUBLISHED_ON", this.getPublishedOn());
        blogString.put("URLS", urls);

        return blogString.toJSONString();
    }

}
