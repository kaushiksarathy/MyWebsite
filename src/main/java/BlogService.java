import Model.Bean.Blog;
import Model.MysqlConnection;
import Model.Resource.AddBlog;
import Model.Resource.IndexResources;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


import java.util.ArrayList;

public class BlogService extends Application<BlogConfiguration> {

    public static void main(String[] args) throws Exception {
        new BlogService().run(args);
    }
    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {


    }

    @Override
    public void run(BlogConfiguration blogConfiguration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, blogConfiguration.getDataSourceFactory(), "mysql");
        MysqlConnection mysqlConnection=new MysqlConnection(jdbi);

        environment.jersey().register(new IndexResources(mysqlConnection));
        environment.jersey().register(new AddBlog(mysqlConnection));
    }
}
