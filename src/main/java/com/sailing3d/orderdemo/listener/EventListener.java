package com.sailing3d.orderdemo.listener;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sailing3d.orderdemo.Event.CreateOrderEvent;
import com.sailing3d.orderdemo.Event.DeleteUserEvent;
import com.sailing3d.orderdemo.domain.Order;
import com.sailing3d.orderdemo.repository.OrderRepository;
import com.sailing3d.orderdemo.repository.UserRepository;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserRepository userRepository;

  @Bean
  public MessageConverter messageConverter() { // 声明一个Json转化器，这个很关键
    return new Jackson2JsonMessageConverter();
  }

  @RabbitListener(queues = "createOrderQueue")
  public void listen1(Message message) {
    ObjectMapper om = new ObjectMapper();
    CreateOrderEvent createOrder = new CreateOrderEvent();
    try {
      createOrder = om.readValue(message.getBody(), CreateOrderEvent.class);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(createOrder.toString());
    orderRepository.save(
        new Order(createOrder.getId(), createOrder.getNum(), createOrder.getProductName(), createOrder.getUsername()));

  }

  @RabbitListener(queues = "deleteUserQueue")
  public void listen2(Message message) {
    ObjectMapper om = new ObjectMapper();
    DeleteUserEvent deleteUser = new DeleteUserEvent();
    try {
      deleteUser = om.readValue(message.getBody(), DeleteUserEvent.class);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (deleteUser.getUsername() != null) {
      userRepository.deleteByUsername(deleteUser.getUsername());
      orderRepository.deleteByUsername(deleteUser.getUsername());
    }

  }

  @RabbitListener(queues = "queryOrdersQueue")
  public String listen3(String name) {
    System.out.println("name:" + name);
    List<Order> orders = orderRepository.findByUsername(name);

    return orders.toString();
  }
}