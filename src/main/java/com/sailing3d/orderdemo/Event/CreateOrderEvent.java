package com.sailing3d.orderdemo.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderEvent {
  private String id;
  private int num;
  private String productName;
  private String username;
}