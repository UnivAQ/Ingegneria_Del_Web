package idw.dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCursor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class CvDao extends AbstractDao
{
        @Override
        public String getCollection() { return "cvs"; }

        public DBCursor getCvOf(String uid)
        {
                Uid u = new Uid<String>(uid);

                /* We need to check that the user has already created a curriculum. */
                if (! this.exists(u)) {
                        /*
                        * If the user has not yet created a curriculum,
                        * we create a new empty one for him.
                        */
                        this.newOne()
                            .set(u)
                            .set("new", true)
                            .save()
                        ;

                }

                return this.getBy(u);
        }

        public static class Uid<T> extends _Field
        {
                public Uid(T value) { super(value); }

                @Override public String getKey() { return "user_id"; }
        }
}
