package uk.co.blackpepper.photocomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({
    "classpath:axon-context.xml",
    "classpath:service-adapter-context.xml",
    "classpath:submission-module.xml",
    "classpath:competition-module.xml",
})
@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
