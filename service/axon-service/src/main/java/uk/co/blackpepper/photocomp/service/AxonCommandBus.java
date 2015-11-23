package uk.co.blackpepper.photocomp.service;


import java.util.concurrent.TimeoutException;

import org.axonframework.commandhandling.GenericCommandMessage;

import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;

public class AxonCommandBus implements CommandDispatcher
{
    private final AxonCommandGateway commandGateway;

    public AxonCommandBus(AxonCommandGateway commandGateway)
    {
        this.commandGateway = commandGateway;
    }

    public void dispatchCommand(Object command)
    {

        try
        {
            if (command.getClass().getInterfaces().length > 0)
            {
                commandGateway.sendCommandAndWaitForCompletion(new GenericCommandMessage(
                    command.getClass().getInterfaces()[0].getName(), command, null));
            }
            else

            {
                commandGateway.sendCommandAndWaitForCompletion(command);
            }
        }
        catch (TimeoutException e)
        {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
