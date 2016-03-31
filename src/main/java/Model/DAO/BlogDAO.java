package Model.DAO;

import Model.Bean.Blog;
import Model.Mappers.BlogMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kaushik.p on 3/30/16.
 */
@RegisterMapper(BlogMapper.class)
public interface BlogDAO {
    @SqlUpdate("insert into Blog (ID ,TITLE , GENRE, DESCRIPTION, PUBLISHED_ON) values (:id, :title, :genre, :desc, :pub)")
    void insert(@Bind("id") String id, @Bind("title") String title,@Bind("genre")String genre,@Bind("desc")String desc,@Bind("pub")Timestamp timestamp);

    @SqlQuery("select ID ,TITLE , GENRE, DESCRIPTION, PUBLISHED_ON from Blog")
    List<Blog> get();

    @SqlQuery("select DISTINCT TITLE from Blog ")
    List<String> getAllTitle();

    @SqlQuery("select DISTINCT GENRE from Blog ")
    List<String> getAllGenre();

    @SqlQuery("select DISTINCT PUBLISHED_ON from Blog ")
    List<Timestamp> getAllDate();

    @SqlQuery("select * from Blog where ID = :id")
    Blog findBlogById(@Bind("id") String id);

    @SqlQuery("select * from Blog where TITLE = :title")
    List<Blog> findBlogsByTitle(@Bind("title") String title);

    @SqlQuery("select * from Blog where GENRE = :genre")
    List<Blog> findBlogsByGenre(@Bind("genre") String genre);

    @SqlQuery("select * from Blog where PUBLISHED_ON = :pub")
    List<Blog> findBlogsByDate(@Bind("pub") Timestamp published);
}
