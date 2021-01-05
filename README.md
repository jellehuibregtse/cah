<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">

  <h1 align="center">Cards Against Humanity Clone</h3>

  <p align="center">
    An open source Cards Against Humanity Clone which can be played online!
    <br />
    <a href="https://github.com/jellehuibregtse/cah"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/jellehuibregtse/cah">Play</a>
    ·
    <a href="https://github.com/jellehuibregtse/cah/issues">Report Bug</a>
    ·
    <a href="https://github.com/jellehuibregtse/cah/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#pipeline-status">Pipeline Status</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#setup">Setup</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

### Built With

The frontend is basically a React app that uses a component library.
* [React JS](https://reactjs.org/)
* [Node.js](https://nodejs.org/)
* [Chakra UI](https://chakra-ui.com/)
* [react-beautiful-dnd](https://github.com/atlassian/react-beautiful-dnd)

The backend uses a microservices architecture and is build with Java, Spring Boot Cloud, PostgreSQL and Maven.
* [Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)


<!-- PROJECT STATUS -->
### Pipeline Status

![Card Service CI](https://github.com/jellehuibregtse/cah/workflows/Card%20Service%20CI/badge.svg)
![Card Service CD](https://github.com/jellehuibregtse/cah/workflows/Card%20Service%20CD/badge.svg)
![Discovery Server CI](https://github.com/jellehuibregtse/cah/workflows/Discovery%20Server%20CI/badge.svg)
![Discovery Server CD](https://github.com/jellehuibregtse/cah/workflows/Discovery%20Server%20CD/badge.svg)
![Gateway CI](https://github.com/jellehuibregtse/cah/workflows/Gateway%20CI/badge.svg)
![Gateway CD](https://github.com/jellehuibregtse/cah/workflows/Gateway%20CD/badge.svg)
![Auth Service CI](https://github.com/jellehuibregtse/cah/workflows/Auth%20Service%20CI/badge.svg)
![Auth Service CD](https://github.com/jellehuibregtse/cah/workflows/Auth%20Service%20CD/badge.svg)
![Gateway CI](https://github.com/jellehuibregtse/cah/workflows/Gateway%20CI/badge.svg)

<!-- GETTING STARTED -->
## Getting Started

To setup the projects, make sure to read through the entirety of the prerequisites and installation. This way you will be up and running in no time! If you are considering contributing to the project, please check out the <a href="#contributing">Contributing</a></li> section. It might be the case that the setup process discribed below is not airtight, if you find an error or see an improvement that can be made, make sure to contribute! 

### Prerequisites

If you want to work on the frontend you will need the following:
* [Node.js](https://nodejs.org/) (and I recommend you use [yarn](https://yarnpkg.com/) as package manager).
* An IDE or text editor of your choosing (I recommend [WebStorm](https://www.jetbrains.com/webstorm/) or [VS Code](https://code.visualstudio.com/)).

If you want to work on the backend you will need the following:
* [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [PostgreSQL](https://www.postgresql.org/)
* An IDE or text editor of your choosing (I recommend [IntelliJ IDEA](https://www.jetbrains.com/idea/).


### Setup

To install the required dependencies for the React frontend we use
```
yarn
```
in your preffered terminal. Then, to start the application locally we use
```
yarn start
```

To get up and running with the Java backend, we have to setup each microservice separately. To run each microservice, simply run
```
./mvnw spring-boot:run
```
in the root directory of said microservice.

It might the case that the microservice needs a database. Make sure Postgres is running, then create a database for the service. Do make sure to create an `application-local.yml` file where you override the `application.yml` configuration. Make sure to include the following:
```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:<port>/<database-name>
    username: <username>
    password: <password>
```


<!-- CONTRIBUTING -->
## Contributing

All information is to be found in [CONTRIBUTING.md](https://github.com/jellehuibregtse/cah/blob/main/CONTRIBUTING.md).


<!-- LICENSE -->
## License

This project is licensed under the [MIT](https://opensource.org/licenses/MIT) license. <a href="https://github.com/jellehuibregtse/cah"><strong>Go to the license »</strong></a>

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/jellehuibregtse/cah?logo=github&logoColor=lightgrey&style=for-the-badge
[contributors-url]: https://github.com/jellehuibregtse/cah/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/jellehuibregtse/cah?logo=github&logoColor=lightgrey&style=for-the-badge
[forks-url]: https://github.com/jellehuibregtse/cah/network/members
[stars-shield]: https://img.shields.io/github/stars/jellehuibregtse/cah?logo=github&logoColor=lightgrey&style=for-the-badge
[stars-url]: https://github.com/jellehuibregtse/cah/stargazers
[issues-shield]: https://img.shields.io/github/issues/jellehuibregtse/cah?logo=github&logoColor=lightgrey&style=for-the-badge
[issues-url]: https://github.com/jellehuibregtse/cah/issues
[license-shield]: https://img.shields.io/github/license/jellehuibregtse/cah?logo=github&logoColor=lightgray&style=for-the-badge
[license-url]: https://github.com/jellehuibregtse/cah/blob/main/LICENSE
