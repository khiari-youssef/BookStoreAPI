package com.example.customermanagementservice.infrastructure.dto.springJPA

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
 class CustomerJpaDTO {
  @Id  val id : String?=null
 }