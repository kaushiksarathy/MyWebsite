package Model.Mappers;

import Model.Bean.Blog;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kaushik.p on 3/30/16.
 */
/*
ID binary(36) primary key,TITLE text NOT NULL, DESCRIPTION text DEFAULT NULL, GENRE text NOT NULL,PUBLISHED_ON date NOT NULL
 */
public class BlogMapper implements ResultSetMapper<Blog> {
    @Override
    public Blog map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Blog(resultSet.getString("ID"),resultSet.getString("TITLE"),resultSet.getString("DESCRIPTION"),resultSet.getString("GENRE"),resultSet.getTimestamp("PUBLISHED_ON"));
    }
}
