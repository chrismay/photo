package uk.co.blackpepper.photocomp.query.persistence;

import java.io.File;
import java.io.IOException;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapDbMapStoreService implements MapPersistenceService
{

    private final File dbFile;
    private final ObjectMapper mapper;

    public MapDbMapStoreService(String dbLocation)
    {
        this.mapper = new ObjectMapper();
        dbFile = new File(dbLocation);
        DB db = DBMaker.newFileDB(dbFile).make();
        db.compact();
        db.close();
    }

    @Override
    public void put(String mapname, String key, Object value)
    {
        DB db = DBMaker.newFileDB(dbFile).make();
        HTreeMap<String, String> map = db.getHashMap(mapname);
        try
        {
            map.put(key, mapper.writeValueAsString(value));
            db.commit();
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            db.close();
        }
    }

    @Override
    public <T> T get(String mapname, String key, Class<T> targetType)
    {
        DB db = DBMaker.newFileDB(dbFile).make();
        HTreeMap<String, String> map = db.getHashMap(mapname);
        String ret = map.get(key);
        if (ret == null)
        {
            return null;
        }
        try
        {
            return mapper.readValue(ret, targetType);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            db.close();
        }
    }

    @Override
    public void remove(String mapname, String key)
    {
        DB db = DBMaker.newFileDB(dbFile).make();
        HTreeMap<String, String> map = db.getHashMap(mapname);
        map.remove(key);
        db.commit();
        db.close();
    }
}
