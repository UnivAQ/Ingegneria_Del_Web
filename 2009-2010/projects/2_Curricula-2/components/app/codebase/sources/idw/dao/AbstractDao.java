package idw.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.HashMap;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao
{
        @Autowired
        protected DB _db;

        protected DBCollection _col;

        public abstract String getCollection();

        public DBCollection col()
        {
                if (this._col == null) {
                        this._col = this._db.getCollection(this.getCollection());
                }

                return this._col;
        }

        public AbstractDao store(DBObject obj)
        {
                this.col().save(obj);

                return this;
        }

        public AbstractDao remove(DBObject obj)
        {
                this.col().remove(obj);

                return this;
        }

        public DBCursor getBy(_Field field)
        {
                BasicDBObject query;

                if (field.key().equals("_id")) {
                        query = new BasicDBObject(field.key(), new ObjectId((String) field.val()));
                } else {
                        query = new BasicDBObject(field.key(), field.val());
                }

                return this.col().find(query);
        }

        public Boolean exists(_Field field)
        {
                return this.getBy(field).count() == 1;
        }

        public New newOne()
        {
                return new New();
        }

        public class New
        {
                protected HashMap<String, Object> _data = new HashMap<String, Object>();

                public New set(String key, Object value) { this._data.put(key, value); return this; }

                public New set(_Field field) { this.set(field.key(), field.val()); return this; }

                public String save()
                {
                        BasicDBObject obj = new BasicDBObject();

                        obj.putAll(this._data);
                        AbstractDao.this.store(obj);

                        return ((ObjectId) obj.get("_id")).toString();
                }
        }

        protected static abstract class _Field<T>
        {
                protected String _key;
                protected T      _value;

                public _Field(T value)
                {
                        this._key = this.getKey();
                        this._value = value;
                }

                public String key() { return this._key; }
                public T val() { return this._value; }

                public abstract String getKey();
        }

        public static class Id<T> extends _Field
        {
                public Id(T value) { super(value); }

                @Override public String getKey() { return "_id"; }
        }
}
