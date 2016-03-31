package Model.DAO;

import Model.Bean.ExternalUrl;
import Model.Mappers.ExternalUrlMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kaushik.p on 3/30/16.
 */
@RegisterMapper(ExternalUrlMapper.class)
public interface ExternalUrlDAO {
    @SqlUpdate("insert into ExternalURL (BLOG_ID ,URL) values (:bid, :url)")
    void insert(@Bind("bid") String id, @Bind("url") String url);

    @SqlQuery("select * from ExternalURL where BLOG_ID = :id")
    List<ExternalUrl> findBlogById(@Bind("id") String id);
}
