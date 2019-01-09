package com.ghaoxiang.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title Order
 * @Description
 * @date 2019年01月09日 09:43
 * @since V1.0
 */
public class Order implements Serializable {

    /**
     * 订单id
     */
    private int id;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 订单唯一标识
     */
    private String messageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

}
