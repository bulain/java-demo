package com.bulain.cassandra;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

public class ClientDemo extends CassTestCase{
    
    @Test
    public void testClient() {
        createSchema();
        loadData();
        loadData2();
        querySchema();
    }

    private void createSchema() {
        session.execute("CREATE KEYSPACE IF NOT EXISTS simplex WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':3};");
        session.execute("CREATE TABLE IF NOT EXISTS simplex.songs (" + "id uuid PRIMARY KEY," + "title text,"
                + "album text," + "artist text," + "tags set<text>," + "data blob" + ");");
        session.execute("CREATE TABLE IF NOT EXISTS simplex.playlists (" + "id uuid," + "title text," + "album text, "
                + "artist text," + "song_id uuid," + "PRIMARY KEY (id, title, album, artist)" + ");");
    }

    private void loadData() {
        session.execute("INSERT INTO simplex.songs (id, title, album, artist, tags) " + "VALUES ("
                + "756716f7-2e54-4715-9f00-91dcbea6cf50," + "'La Petite Tonkinoise'," + "'Bye Bye Blackbird',"
                + "'Joséphine Baker'," + "{'jazz', '2013'})" + ";");
        session.execute("INSERT INTO simplex.playlists (id, song_id, title, album, artist) " + "VALUES ("
                + "2cc9ccb7-6221-4ccb-8387-f22b6a1b354d," + "756716f7-2e54-4715-9f00-91dcbea6cf50,"
                + "'La Petite Tonkinoise'," + "'Bye Bye Blackbird'," + "'Joséphine Baker'" + ");");
    }

    private void loadData2() {
        PreparedStatement statement = session.prepare(
                "INSERT INTO simplex.songs " + "(id, title, album, artist, tags) " + "VALUES (?, ?, ?, ?, ?);");
        BoundStatement boundStatement = new BoundStatement(statement);
        Set<String> tags = new HashSet<String>();
        tags.add("jazz");
        tags.add("2013");
        session.execute(
                boundStatement.bind(UUID.fromString("756716f7-2e54-4715-9f00-91dcbea6cf50"), "La Petite Tonkinoise'",
                        "Bye Bye Blackbird'", "Joséphine Baker", tags));

        statement = session.prepare(
                "INSERT INTO simplex.playlists " + "(id, song_id, title, album, artist) " + "VALUES (?, ?, ?, ?, ?);");
        boundStatement = new BoundStatement(statement);
        session.execute(
                boundStatement.bind(UUID.fromString("2cc9ccb7-6221-4ccb-8387-f22b6a1b354d"),
                        UUID.fromString("756716f7-2e54-4715-9f00-91dcbea6cf50"), "La Petite Tonkinoise",
                        "Bye Bye Blackbird", "Joséphine Baker"));
    }

    private void querySchema() {
        ResultSet results = session.execute("SELECT * FROM simplex.playlists "
                + "WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");
        System.out.println(String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
                "-------------------------------+-----------------------+--------------------"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s\t%-20s", row.getString("title"), row.getString("album"),
                    row.getString("artist")));
        }
        System.out.println();
    }

}