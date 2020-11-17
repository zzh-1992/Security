package com.grapefruit.springbootdemo;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.oss.driver.api.core.CqlSession;

public class CassandraApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApplication.class);

  private static Person newPerson(String name, int age) {
    return new Person(UUID.randomUUID().toString(), name, age);
  }

  public static void main(String[] args) {

    InetSocketAddress inetSocketAddress = new InetSocketAddress("47.115.42.52", 9042);

    CqlSession cqlSession = CqlSession.builder().withKeyspace("mySpace").addContactPoint(inetSocketAddress).build();


    CassandraOperations template = new CassandraTemplate(cqlSession);

    Person jonDoe = template.insert(newPerson("Jon Doe", 40));

    //LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());
    System.out.println(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());

    //template.truncate(Person.class);
    cqlSession.close();
  }

}