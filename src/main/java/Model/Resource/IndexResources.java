package Model.Resource;

import Model.Bean.Blog;
import Model.Bean.CompleteBlog;
import Model.Bean.ExternalUrl;
import Model.DAO.BlogDAO;
import Model.DAO.ExternalUrlDAO;
import Model.MysqlConnection;
import lombok.AllArgsConstructor;
import org.skife.jdbi.v2.DBI;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/blogs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class IndexResources {
    MysqlConnection mysqlConnection;

    @GET
    public List<CompleteBlog> index( @QueryParam(value = "category") final String category,@QueryParam(value = "title") final String title, @QueryParam(value = "date")final String  date) throws SQLException {

        List<CompleteBlog> completeBlogList=new ArrayList<>();

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        List<Blog> blogs=new ArrayList<>();
        if(category==null&&title==null&&date==null)
            blogs=blogDAO.get();
        else if(category!=null)
            blogs.addAll(blogDAO.findBlogsByGenre(category));
        else if(title!=null)
            blogs.addAll(blogDAO.findBlogsByTitle(title));
        else if(date!=null)
            blogs.addAll(blogDAO.findBlogsByDate(Timestamp.valueOf(date)));

        Set<Blog>uniqueblog=new HashSet<>(blogs);

        for(Blog blog:uniqueblog) {
            List<String> urls = new ArrayList<>();
            List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
            for(ExternalUrl externalUrl:externalUrlList)
                urls.add(externalUrl.getUrl());

            completeBlogList.add(new CompleteBlog(blog.getId().trim(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls));
        }




        return completeBlogList;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

    @GET
    @Path("/categories")
    public List<String> getAllCategory() throws SQLException {

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        List<String> genres=blogDAO.getAllGenre();

        return genres;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

    @GET
    @Path("/titles")
    public List<String> getAllTitles() throws SQLException {

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        List<String> titles=blogDAO.getAllTitle();
        return titles;

    }

    @GET
    @Path("/{id}")
    public CompleteBlog indexById(@PathParam("id") String id) throws SQLException {
        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        Blog blog=blogDAO.findBlogById(id);
        List<String> urls = new ArrayList<>();
        List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
        for(ExternalUrl externalUrl:externalUrlList)
            urls.add(externalUrl.getUrl());

        return new CompleteBlog(blog.getId(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls);

//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

    @GET
    @Path("/dates")
    public List<Timestamp> indexByDate() throws SQLException {
        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        List<Timestamp> dates=blogDAO.getAllDate();
        return dates;

    }


}