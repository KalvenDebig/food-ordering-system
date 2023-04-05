# Dependency Graph
```bash
mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdge=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"
```
# Create Kafka Model by Using Avro Schema
```bash
cd /infrastructure/kafka/kafka-model 
mvn clean install
```

# MicroService

## Structure of the Project

* Microservices: Powered by Spring boot
  * Independent development and deployment by different teams
  * Easy to scale for a specific service
  * Better fault isolation
  * Enables to use different technology and languages for different services

* Clean & Hexagonal Architectures: Isolate the domain logic from outside dependencies.

* Domain Driven Design: Bounded context, Entities, Aggregates, Value Objects, Domain services, Application services, and Domain Events

* Kafka: Event store for Event-driven services. Enable loosely coupled services that communicates through events.

* SAGA: Distributed long-running transactions across services. Used for Long Lived Transactions(LLT).

* Outbox: Help use of local ACID transactions to let consistent (eventual) distributed transactions. It will complete SAGA in a safe and consistent way.

* Command Query Responsibility Segregation (CQRS): Separate read and write operations. Better performance on read part using right technology for reading, and preventing conflicts with update commands. Scale each part separately. Leads to eventual consistency.

* Kubernetes(K8s) & Docker: Kubernetes is a container orchestration system that automates deployment, scaling and management of cloud native applications. It allows to run docker containers while reducing operational complexities.

* Goole Cloud & Google Kubernetes Engine(GKE): Google's solution for Cloud computing. GKE is a managed, production ready environment for running containerized applications.

---

## Clean Architecture

* Separation of concerns by dividing the software into different layers
* Use Dependency Inversion and Polymorphism to create a dependency rule to make the domain layer most independent and stable layer
* Source code dependencies can only point inward, towards to domain layer
* Independent of Frameworks, UI, Databases, and any external agency
* Testable domain layer without requiring and external element
* Define Entities and Use Cases for domain logic
* Same high level principle wih hexagonal and onion architectures: Isolate the domain


## Hexagonal Architecture

* Hexagonal Architecture also known as Posts & Adapters
  * Separate insides(domain layer) and outside (infrastructure layers)
* Divides the software as insides and outsides, and start with inside, domain layer
* The principle of the hexagonal architecture is to isolate the domain from any dependency, such as UI, Data layer or even a framework like Spring
* It is a response to the desire to create thoroughly testable applications with isolated business logic from the infrastructure and data source
  * Domain layer should be the most independent and stable component of a system
* Allow an application to equally be driven by users, programs, automated test or batch scripts, and to be developed and tested in isolation from its eventual run-time devices and databases

* Adapters are the interchangeable components
* Primay/Driving Adapter. Implement the input ports to execute the use cases
* use the core logic that is in domain layer, they use the input ports to call the implementations that are defined in domain layer

* Secondary / Driven Adapter. Implement the outputs and called by business logic to complete external tasks
* are implemented in the external modules like database or message queue

* Especially useful for long-lasting applications that need to keep up with changing requirements
* Improvement to traditional Layered Architecture. The dependencies are now plugins to the business logic. All dependency arrows point to business logic making the domain independent. Reverse the relation using ports and adapters
  * Presentation Layer -> Business Layer -> Data Layer
* Delay the implementation details of dependencies
* Easier to test the business logic by mocking the dependencies even before deciding the implementation details
* Replace an adapter implementation easily, without touching the business logic
* Easier to maintain as changing a part of software will not affect other parts
* Independent development and deployment of different parts


## Business Logic in Clean Architecture

* **Entities**: Objects that embodies a small set of **critical business rules**
* **Use Cases**: Describes **application-specific business rules**. Contains the rules that specify how and when the Critiacl Business Rules within the Entities are invoked. Orchestrates the flow of data to and from the entities, and direct those entities to use their Critical Business Rules to achieve the goals of the use case

---

## Introduction to Domain-Driven Design

* Domain-Driven Design offers solutions to common problem when building enterprise software
* Strategic DDD vs Tactical DDD
* Strategic DDD: Introduces boundaries for domain model. Single Bounded context per each domain
  * What is a Domain? Operational area of your application eg: Online food ordering
  * Bounded Context: Central pattern in DDD. Boundary within a Domain
  * Ubiquitous Language: Common language used by domain experts and developers
* Tactical DDD: Implementation patterns
  * Entities: Domain objects with a unique identity.Embodies set of critical business rules
  * Aggregates: Group of Entity objects which always need to be in consistent state
  * Aggregate Root (AR): Entry point Entity for an aggregate. All business operations should go through root. An Aggregate should be referenced from outside through its root only. AR should have pure, side-effect free functions
  * Value Objects: Immutable objects without identity. Only value matters. Brings context to the value
    * Brings context, allow business operations inside the object and allow validations in constructor
* Domain Events: Decouple different domains. Describe things that happen and change the state of a domain. Makes the system extendable. Domain event listeners runs in a different transaction than the event publisher. In Domain driven system, domain events are an excellent way of achieving eventual consistency. Any system or module that needs to update itself when something happens in another module or system can subscribe to the domain events coming from that system
  * Eventual Consistency
    * All reads of the system will eventually return the latest value, provided that no new updates are made
* Domain Services: Business logic that cannot fit in the aggregate. Used when multiple aggregates required in business 
  logic Can interact with other domain services
* Application Services: Allows the isolated domain to communicate with outside. Orchestrate transaction, security, looking up proper aggregates and saving state changes of the domain to the database. Does not contain any business logic.
  * Domain event listeners are special kind of Application services that is triggered by domain events. Each domain event listeners can have a separate domain service to handle business logic

* For most software projects, the primary focus should be on the domain and domain logic
* Complex domain designs should be based on a model
* Domain driven design is both a way of thinking and a set of priorities, aimed at accelerating software projects that have to deal with complicated domains.
* Layered Architecture is used in most systems today, under various layering schemes. Many styles of development can also benefit from layering. However, domain-driven design requires only one particular layer to exist.
* The domain model is a set of concepts. The domain layer is the manifestation of that model and all directly related design elements. The design and implementation of business logic consistent the domain layer. In a MODEL-DRIVEN DESIGN, the software constructs of the domain layer mirror the model concepts


## Kafka Architecture

* Kafka brokers: Servers run in a cluster
* Topics: Logical data unit that holds multiple partition
* Partitions: Smallest storage unit that holds subset of records
* Producers: Writes to end of a specific partition
* Consumers: Reads from a partition using an offset
* Replication: Resilient and fault-tolerant
* Scaling: Partition strategy
* Immutable append-only event logs


## Zookeeper & Schema Registry

* Zookeeper: Manage cluster, store metadata
* Schema registry: Stores versioned history of all schemas by id
* Producer send schema to schema registry and get schema id
* Consumer retrieve schema by id  
 



