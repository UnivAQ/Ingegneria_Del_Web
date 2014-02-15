package idw.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class ShotDao extends AbstractDao
{
        @Override
        public String getCollection() { return "shots"; }

        public static class Cid<T> extends _Field
        {
                public Cid(T value) { super(value); }

                @Override public String getKey() { return "company_id"; }
        }

}
