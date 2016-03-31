package Model.Resource;

import Model.Bean.Blog;
import Model.Bean.CompleteBlog;
import Model.DAO.BlogDAO;
import Model.DAO.ExternalUrlDAO;
import Model.MysqlConnection;
import Model.ReadData;
import com.google.gdata.util.ServiceException;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.List;
import java.util.UUID;
@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class AddBlog {
    MysqlConnection mysqlConnection;


    @POST
    @Path("/add")
    public Response publishBlog(@Valid CompleteBlog completeBlog) throws SQLException {
        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);

        blogDAO.insert(completeBlog.getId(),completeBlog.getTitle(),completeBlog.getGenre(),completeBlog.getDescription(),completeBlog.getPublishedOn());
        List<String> urls=completeBlog.getUrls();
        String blogid=completeBlog.getId();
        for(String url:urls)
            externalUrlDAO.insert(blogid,url);

        return Response.ok().build();
    }


    @POST
    @Path("/addBlogFromSheet")
    public Response publishBlogFromSheet() throws SQLException, ServiceException, IOException, GeneralSecurityException {
        System.out.println("Hi");
        List<CompleteBlog> completeBlogList=new ReadData().getData();
        System.out.println(completeBlogList.get(0).getTitle());
        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        for(CompleteBlog completeBlog:completeBlogList) {
            try {
                blogDAO.insert(completeBlog.getId(),completeBlog.getTitle(),completeBlog.getGenre(),completeBlog.getDescription(),completeBlog.getPublishedOn());
                List<String> urls=completeBlog.getUrls();
                String blogid=completeBlog.getId();
                for(String url:urls)
                    externalUrlDAO.insert(blogid,url.trim().replaceAll("\\r|\\n", ""));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return Response.ok().build();
    }

}
