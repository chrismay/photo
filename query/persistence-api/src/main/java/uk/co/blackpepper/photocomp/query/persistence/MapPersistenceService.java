package uk.co.blackpepper.photocomp.query.persistence;

public interface MapPersistenceService
{

    void put(String mapname, String key, Object value);

    <T> T get(String mapname, String key, Class<T> targetType);

    void remove(String mapname, String key);
}
