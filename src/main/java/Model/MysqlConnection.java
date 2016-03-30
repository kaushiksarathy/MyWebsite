package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kaushik.p on 12/12/15.
 */
@AllArgsConstructor
@Getter
public class MysqlConnection {
    private DBI jdbi;
}
