package uk.co.blackpepper.photocomp.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.axonframework.commandhandling.gateway.Timeout;

public interface AxonCommandGateway
{

    // fire and forget
    void sendCommand(Object command);

    // alternative that throws exceptions on timeout
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    void sendCommandAndWaitForCompletion(Object command)
        throws TimeoutException, InterruptedException;
}
