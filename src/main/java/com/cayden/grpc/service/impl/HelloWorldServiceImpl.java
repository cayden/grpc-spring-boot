package com.cayden.grpc.service.impl;

import com.cayden.grpc.helloworld.Greeting;
import com.cayden.grpc.helloworld.HelloWorldServiceGrpc;
import com.cayden.grpc.helloworld.Person;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cuiran on 20/6/28.
 */
@Slf4j
@GRpcService
public class HelloWorldServiceImpl  extends HelloWorldServiceGrpc.HelloWorldServiceImplBase{



    @Override
    public void sayHello(Person request,
                         StreamObserver<Greeting> responseObserver) {
        log.info("server received {}", request);

        String message = "Hello " + request.getFirstName() + " "
                + request.getLastName() + "!";
        Greeting greeting =
                Greeting.newBuilder().setMessage(message).build();
        log.info("server responded {}", greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
