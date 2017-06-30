# Hexagonal (Port and Adapter) Example #

A spring-boot based example of hexagonal design (also known as the ports and adapters design).

Through the use of ports, contracts between the various modules can be set up, allowing for the modules to be easily replaced with other implementations. The only condition; that the module conforms to the contract specified.

Thus by having a hexagonal design, the current database adapter module; which is a simple in-memory implementation can be swapped out for a JPA repository or a flat file or something else and as long as it conforms to the contract (aka port) no other module needs to know or care.

## Getting Started ##

```
    git clone https://github.com/gshaw-pivotal/spring-hexagonal-example.git
```

## Resources on Hexagonal / Ports and Adapters ##

The following are some resources that explain the hexagonal design / pattern

- [Hexagonal Architecture](http://alistair.cockburn.us/Hexagonal+architecture)
- [Ports-And-Adapters / Hexagonal Architecture](http://www.dossier-andreas.net/software_architecture/ports_and_adapters.html)

## To Do ##

This codebase is currently a work in progress.

- Add a rest-api-adapter module (Done)
- Add a domain module (Done)
- Add a database adapter module (Done)
- Perhaps add third party integration adapter module(s)