package idw.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class UserDao extends AbstractDao
{
        @Override
        public String getCollection() { return "users"; }

        public static class Uid<T> extends _Field
        {
                public Uid(T value) { super(value); }

                @Override public String getKey() { return "uid"; }
        }

        public static class Pass<T> extends _Field
        {
                public Pass(T value) { super(value); }

                @Override public String getKey() { return "pass"; }
        }

        public static class Type<T> extends _Field
        {
                public Type(T value) { super(value); }

                @Override public String getKey() { return "type"; }
        }

        public static class Email<T> extends _Field
        {
                public Email(T value) { super(value); }

                @Override public String getKey() { return "email"; }
        }

        public static class Piva<T> extends _Field
        {
                public Piva(T value) { super(value); }

                @Override public String getKey() { return "piva"; }
        }
}
