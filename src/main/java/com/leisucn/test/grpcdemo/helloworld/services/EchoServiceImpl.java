package com.leisucn.test.grpcdemo.helloworld.services;

import com.leisucn.test.grpcdemo.helloworld.EchoReply;
import com.leisucn.test.grpcdemo.helloworld.EchoRequest;
import com.leisucn.test.grpcdemo.helloworld.EchoServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServiceImpl extends EchoServiceGrpc.EchoServiceImplBase{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void echo(EchoRequest request, StreamObserver<EchoReply> responseObserver) {

        logger.info("syn is {}", request.getSyn());

        int ack = request.getSyn() + 1;

        logger.info("ack is {}", ack);

        responseObserver.onNext(EchoReply.newBuilder()
            .setAck(ack)
            .build());

        responseObserver.onCompleted();
    }
}
