package uk.co.blackpepper.photocomp.service.api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class AggregateId
{
    private AggregateId()
    {
    }

    public static String newAggregateId()
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String uuid = UUID.randomUUID().toString();
            byte[] digest = md5.digest(uuid.getBytes());
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            return hashtext.substring(0, 5);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
