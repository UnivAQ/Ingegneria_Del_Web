package idw.factory;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansFactory
{
        protected @Value("${mongo.host}") String _mongoHost;
        protected @Value("${mongo.port}") String _mongoPort;

        @Bean
        public DB newDb()
        {
                DB db = null;

                try {
                        Mongo mongo = new Mongo(
                                this._mongoHost,
                                Integer.parseInt(this._mongoPort)
                        );
                        db = mongo.getDB("ingweb");
                } catch (UnknownHostException e) {
                        Logger.getLogger(
                                BeansFactory.class.getName()
                        ).log(Level.SEVERE, null, e);
                } catch (MongoException e) {
                        Logger.getLogger(
                                BeansFactory.class.getName()
                        ).log(Level.SEVERE, null, e);
                }

                return db;
        }

        @Bean(name="log4jBugFixer")
        public Boolean newLog4jBugFixer()
        {
                System.setProperty("org.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES", "false");

                return true;
        }
}
