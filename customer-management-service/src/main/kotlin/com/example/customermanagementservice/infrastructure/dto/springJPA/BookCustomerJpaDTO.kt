package com.example.customermanagementservice.infrastructure.dto.springJPA

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Date

@Entity(name = "BookCustomer")
 class BookCustomerJpaDTO {
@Id lateinit var loginIDRef : String
lateinit var profileRef : String
lateinit var addressRef : String
 }

@Entity(name = "CustomerLoginCredentials")
class CustomerLoginCredentials{
@Id lateinit var customerIDRef : String
lateinit var email : String
lateinit var password : String
}

@Entity(name = "CustomerProfile")
class CustomerProfile{
@Id lateinit var customerIDRef : String
 lateinit var firstName : String
 var lastName : String?=null
 var biography : String?=null
 var birthdate : Date?=null
 var phoneNumber : String?=null
}

@Entity(name = "CustomerAddress")
class CustomerAddress{
 @Id lateinit var customerIDRef : String
 lateinit var line1IDRef : String
 var line2IDRef : String?=null
 var location : String?=null
}


@Entity(name = "CustomerAddressLine")
class CustomerAddressLine{
 @Id lateinit var addressIDRef : String
 var buildingNumber : Short?=null
 var streetAddress : String?=null
}

@Entity(name = "CustomerLocation")
class CustomerLocation{
 @Id lateinit var addressIDRef : String
  var latitude : Double?=null
  var longitude : Double?=null
}

