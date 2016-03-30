package Model.Mappers;

import Model.Bean.ExternalUrl;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kaushik.p on 3/30/16.
 */
/*
BLOG_ID binary(36) primary key,URL text NOT NULL,foreign key (BLOG_ID) references Blog(ID)
 */
public class ExternalUrlMapper implements ResultSetMapper<ExternalUrl> {
    @Override
    public ExternalUrl map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ExternalUrl(resultSet.getString("BLOG_ID"),resultSet.getString("URL"));
    }
}
