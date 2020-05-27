# J-Rolamo Code Generator

## Overview
This java library is intended to be used within [J-Rolamo](https://github.com/VittorioValent/J-Rolamo) project. j-rolamo-code-generator is in fact the core functionality of
J-Rolamo, that is to generate java classes at runtime.

## Getting Started
Within J-Rolamo this library exposes two basic REST API endpoints:
- (**GET**) Entity Info;
- (**POST**) Entity Flow.

To be eable to use these two endpoint you should set the following properties in **application.yml**:

**`jrolamo.codegenerator.package.basepackage`**`: com.foo.bar`\
**`jrolamo.codegenerator.endpoint-enabled`**`: true`

These properties should be set by default by the archetype, but if you wish to modify your package names, 
just let J-Rolamo know!

### Entity Info
This API returns the list of all entities in the domain package.

### Entity Flow
This API can be used to generate a complete Entity-to-controller flow. Specifically it generates:
- An Entity class with the fields specified in the request's body and a given name;
- A DTO class with the fields specified in the request's body named after the entity name + 'DTO';
- A Filter class named after the entity name + 'Specification';
- A Repository interface named after the entity name + 'Repository';
- A Mapper interface named after the entity name + 'Mapper';
- A Service class named after the entity name + 'Service';
- A Controller class named after the entity name + 'Controller'.

There are options even to specify if the entity should be auditable, 
the security policy of the service and of a given rest api should be auth-restricted.

## Advanced Settings
There are few advanced settings you can modify, for exemple if you change any package name. 
Here's the list with the default values:

**`jrolamo.codegenerator.package.domain`**`: domain`\
**`jrolamo.codegenerator.package.domain-generic`**`: domain.generic`\
**`jrolamo.codegenerator.package.dto`**`: dto`\
**`jrolamo.codegenerator.package.filter`**`: filter`\
**`jrolamo.codegenerator.package.repository`**`: repository`\
**`jrolamo.codegenerator.package.repository-generic`**`: repository.generic`\
**`jrolamo.codegenerator.package.mapper`**`: mapper`\
**`jrolamo.codegenerator.package.mapper-generic:`**`: mapper.generic`\
**`jrolamo.codegenerator.package.service`**` : service`\
**`jrolamo.codegenerator.package.service-generic`**`: service.generic`\
**`jrolamo.codegenerator.package.controller`**`: controller`\
**`jrolamo.codegenerator.package.controller-generic`**`: controller.generic`

Note that theese options concat themselves whith the basepackage: for exemple if your basepackege is <code>com.exemple</code>
and the domain package is <code>model</code> your final result would be <code>com.exemple.domain</code>.



## Authors
- Vittorio Valent - <v.valent@contrader.it>
