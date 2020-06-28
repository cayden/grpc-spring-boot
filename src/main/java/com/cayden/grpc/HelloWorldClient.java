package com.cayden.grpc;

import com.cayden.grpc.helloworld.Greeting;
import com.cayden.grpc.helloworld.HelloWorldServiceGrpc;
import com.cayden.grpc.helloworld.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by cuiran on 20/6/28.
 */
@Component
@Slf4j
public class HelloWorldClient {

    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    protected void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565).usePlaintext().build();

        helloWorldServiceBlockingStub =
                HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder().setFirstName(firstName)
                .setLastName(lastName).build();
        log.info("client sending {}", person);

        Greeting greeting =
                helloWorldServiceBlockingStub.sayHello(person);
        log.info("client received {}", greeting);

        return greeting.getMessage();
    }
}
