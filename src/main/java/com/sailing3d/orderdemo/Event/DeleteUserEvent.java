package com.sailing3d.orderdemo.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserEvent {
  private String id;
  private String username;
}