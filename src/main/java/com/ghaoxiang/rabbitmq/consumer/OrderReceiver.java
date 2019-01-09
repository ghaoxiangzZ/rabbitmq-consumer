/*
* 文件名: com.ghaoxiang.rabbitmq.consumer
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 10:13
* 修改人:
* 修改时间: 2019年01月09日 10:13
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.consumer;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ghaoxiang.rabbitmq.entity.Order;
import com.rabbitmq.client.Channel;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title OrderReceiver
 * @Description
 * @date 2019年01月09日 10:13
 * @since V1.0
 */
@Component
public class OrderReceiver {

    // 配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order-queue", durable = "true"),
        exchange = @Exchange(name="order-exchange", durable = "true", type = "topic"), key = "order.*")
    )

    // 监听队列
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        // 消费者消费队列信息
        System.out.println("---------收到消息，开始消费---------");
        System.out.println("订单ID："+order.getId());
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        boolean multiple = false;
        // 业务逻辑处理完之后，确认一条消息已经被消费(配置文件配置ack为手动)
        channel.basicAck(deliveryTag,multiple);
    }
}
