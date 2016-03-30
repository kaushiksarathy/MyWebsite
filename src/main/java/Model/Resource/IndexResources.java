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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class IndexResources {
    MysqlConnection mysqlConnection;

    @GET
    public List<CompleteBlog> index() throws SQLException {

        List<CompleteBlog> completeBlogList=new ArrayList<>();

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        List<Blog> blogs=blogDAO.get();

        for(Blog blog:blogs) {
            List<String> urls = new ArrayList<>();
            List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
            for(ExternalUrl externalUrl:externalUrlList)
                urls.add(externalUrl.getUrl());

            completeBlogList.add(new CompleteBlog(blog.getId(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls));
        }




        return completeBlogList;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

    @GET
    @Path("/{category}")
    public List<CompleteBlog> indexByCategory(@PathParam("category") String category) throws SQLException {

        List<CompleteBlog> completeBlogList=new ArrayList<>();

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        List<Blog> blogs=blogDAO.findBlogsByGenre(category);

        for(Blog blog:blogs) {
            List<String> urls = new ArrayList<>();
            List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
            for(ExternalUrl externalUrl:externalUrlList)
                urls.add(externalUrl.getUrl());

            completeBlogList.add(new CompleteBlog(blog.getId(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls));
        }




        return completeBlogList;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

    @GET
    @Path("/{title}")
    public List<CompleteBlog> indexByTitle(@PathParam("title") String title) throws SQLException {

        List<CompleteBlog> completeBlogList=new ArrayList<>();

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        List<Blog> blogs=blogDAO.findBlogsByTitle(title);

        for(Blog blog:blogs) {
            List<String> urls = new ArrayList<>();
            List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
            for(ExternalUrl externalUrl:externalUrlList)
                urls.add(externalUrl.getUrl());

            completeBlogList.add(new CompleteBlog(blog.getId(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls));
        }




        return completeBlogList;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
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
    @Path("/{date}")
    public List<CompleteBlog> indexByDate(@PathParam("date") String date) throws SQLException {

        List<CompleteBlog> completeBlogList=new ArrayList<>();
        Timestamp ts = Timestamp.valueOf(date);

        BlogDAO blogDAO=mysqlConnection.getJdbi().onDemand(BlogDAO.class);
        ExternalUrlDAO externalUrlDAO=mysqlConnection.getJdbi().onDemand(ExternalUrlDAO.class);
        List<Blog> blogs=blogDAO.findBlogsByDate(ts);

        for(Blog blog:blogs) {
            List<String> urls = new ArrayList<>();
            List<ExternalUrl> externalUrlList=externalUrlDAO.findBlogById(blog.getId());
            for(ExternalUrl externalUrl:externalUrlList)
                urls.add(externalUrl.getUrl());

            completeBlogList.add(new CompleteBlog(blog.getId(),blog.getTitle(),blog.getGenre(),blog.getDescription(),blog.getPublishedOn(),urls));
        }




        return completeBlogList;
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }


}