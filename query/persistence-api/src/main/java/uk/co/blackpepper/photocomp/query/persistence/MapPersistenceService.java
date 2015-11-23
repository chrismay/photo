package uk.co.blackpepper.photocomp.query.persistence;

import java.util.Map;

public interface MapPersistenceService
{

    void put(String mapname, String key, Object value);

    <T> Map<String, T> getAll(String mapname, Class<T> targetType);

    <T> T get(String mapname, String key, Class<T> targetType);

    void remove(String mapname, String key);
}
