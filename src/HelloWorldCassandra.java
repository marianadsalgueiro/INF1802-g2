import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;
import twitter4j.GeoLocation;
import twitter4j.User;

public class HelloWorldCassandra {
    public static void main(String[] args){
        System.out.println("Hello, Cassandra!");

        Cluster cluster = null;
        try{
            cluster = Cluster.builder()
                    .addContactPoint("localhost")
                    .build();

            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));

            KeyspaceRepository sr = new KeyspaceRepository(session);
            sr.createKeyspace("library", "SimpleStrategy", 1);
            System.out.println("Create repository");

            sr.useKeyspace("library");
            System.out.println("Using repository library");

            TweetRepository tw = new TweetRepository(session);

            /* table tweets */
            //tw.createTable();
            //Tweet t1 = new Tweet(UUIDs.random(),"mariana", "teste", LocalDate.fromYearMonthDay(2019,06,27));
            //Tweet t2 = new Tweet(UUIDs.random(),"andrea", "oi", LocalDate.fromYearMonthDay(2019,06,27));
            //Tweet t3 = new Tweet(UUIDs.random(),"melissa", "tudo", LocalDate.fromYearMonthDay(2019,06,27));
            //Tweet t4 = new Tweet(UUIDs.random(),"julia", "bom", LocalDate.fromYearMonthDay(2019,06,27));
            //Tweet t5 = new Tweet(UUIDs.random(),"teste", "?", LocalDate.fromYearMonthDay(2019,06,27));
            //tw.insertTweet(t1);
            //tw.insertTweet(t2);
            //tw.insertTweet(t3);
            //tw.insertTweet(t4);
            //tw.insertTweet(t5);
            //tw.selectAll();
            //tw.deleteTweet(t1);
            //tw.deleteTable("Tweets");

            /* table tweetsbycategoria */
            tw.createTableTweetsByCategoria();

            Tweet t1 = new Tweet(UUIDs.random(), "mariana", "reforma da previdencia", LocalDate.fromYearMonthDay(2019,06,27), ".",false, new GeoLocation (20.593684, 78.962880), true, null, "politica");
            tw.insertTweetbycategoria(t1);
            //sr.deleteKeyspace("library");
            //System.out.println("Delete keyspace library");


        }
        finally {
            if(cluster != null) cluster.close();
        }
    }
}
