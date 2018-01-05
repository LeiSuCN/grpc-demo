package com.leisucn.test.grpcdemo.helloworld;

import com.leisucn.test.grpcdemo.helloworld.services.EchoServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GRPC Server
 */
public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static final int PORT = 50051;

    public static void main(String[] args) throws Exception{
        logger.info("server is starting on {}...", PORT);

        Server server = ServerBuilder.forPort(PORT)
                .addService(new EchoServiceImpl())
                .build()
                .start();

        logger.info("server has started!");

        Runtime.getRuntime().addShutdownHook( new Thread(){
            @Override
            public void run() {
                logger.info("server has stopped!");
            }
        });

        for (;;){
            Thread.sleep(3000);
        }
    }
}
