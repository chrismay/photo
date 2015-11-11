package uk.co.blackpepper.photocomp.service;


import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;

import uk.co.blackpepper.photocomp.service.api.CommandDispatcher;

public class AxonCommandBus implements CommandDispatcher
{
    private final CommandGateway commandGateway;

    public AxonCommandBus(CommandGateway commandGateway)
    {
        this.commandGateway = commandGateway;
    }

    public void dispatchCommand(Object command)
    {

        if (command.getClass().getInterfaces().length > 0)
        {
            commandGateway.send(new GenericCommandMessage(
                command.getClass().getInterfaces()[0].getName(), command, null));
        }
        else
        {
            commandGateway.send(command);
        }
    }
}
