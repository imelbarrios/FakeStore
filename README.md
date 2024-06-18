
# Fake Store

Online store with your shopping cart and order processing


## Table of Contents
* [Requirements](#Requirements)
* [API Reference](#API_Reference)
* [Authors](#Authors)
* [License](#License)

## Requirements

For building and running the application you need:

* [Open JDK 17](https://openjdk.org/projects/jdk/17/)
* [Gradle](https://gradle.org/releases/)


### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.package com.store.fake.FakeApplication class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### To access

```shell
To log in to the rest api you need the following information:

```

```http
  POST /v1/FakeStore/Login
```


| Parameter  | Data     | Description                       |
| :----------| :------- | :-------------------------------- |
| `email` | `string` | **No Required**. This field is no required |
| `username` | `string` | **Required**. This field is required |
| `password` | `string` | **Required**. This field is required |




Example:
![alt text](https://github.com/imelbarrios/FakeStore/blob/feature/configuration/Token.png?raw=true)

The Token lasts only 1 hour, upon expiration you need to generate another

```http
  GET /v1/FakeStore/RefreshToken
```

Example:

![alt text](https://github.com/imelbarrios/FakeStore/blob/feature/configuration/Refresh%20Token.png?raw=true)


## API_Reference

Consult Rest Api documentation:
```http
 GET /swagger-ui/index.html
```

#### Get all items

```http
  GET /v1/FakeStore/Clients
```


#### Get item

```http
  GET /v1/FakeStore/Clients
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `integer` | **Required**. Id of item to fetch |




## Authors

- Developed by [Imelda Barrios](https://github.com/imelbarrios)

## License

Project is licensed under the [MIT](LICENSE) license.


