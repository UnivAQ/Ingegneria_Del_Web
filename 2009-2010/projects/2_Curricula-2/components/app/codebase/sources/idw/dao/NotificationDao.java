package idw.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class NotificationDao extends AbstractDao
{
        @Override
        public String getCollection() { return "noties"; }

        public static class Uid<T> extends _Field
        {
                public Uid(T value) { super(value); }

                @Override public String getKey() { return "user_id"; }
        }

        public static class Cid<T> extends _Field
        {
                public Cid(T value) { super(value); }

                @Override public String getKey() { return "company_id"; }
        }

}
