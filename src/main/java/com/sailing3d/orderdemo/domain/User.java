package com.sailing3d.orderdemo.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {
  static final private long serialVersionUID = -1L;

  @Id
  private String id;

  @Column(unique = true)
  private String username;

  @Column
  private String password;

  @PrePersist
  protected void onCreateAbstractBaseEntity() {
    this.id = UUID.randomUUID().toString();
  }
}