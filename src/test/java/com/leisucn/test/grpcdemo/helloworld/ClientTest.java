package com.leisucn.test.grpcdemo.helloworld;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import org.junit.Assert;
import org.junit.Test;


public class ClientTest {


    @Test
    public void test_01_netty_channel_call() throws Exception{
        Channel channel = getNettyChannel("localhost", App.PORT);

        Assert.assertNotNull(channel);

        EchoReply echoReply = EchoServiceGrpc.newBlockingStub(channel)
                .echo(EchoRequest.newBuilder().setSyn(1).build());

        Assert.assertNotNull(echoReply);

        Assert.assertEquals("SYN/ACK", 2, echoReply.getAck());

        ((ManagedChannel)channel).shutdownNow();

        Thread.sleep(500);

    }



    private Channel getNettyChannel(String target, int port) throws Exception{

        return NettyChannelBuilder.forAddress(target, port)
                .usePlaintext(true)
                .build();

    }


}
