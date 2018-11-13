
What needs to be done
=====================

This application stub works with sessions stored in relational storage.

We've chosen Cassandra as a new session storage. However it doesn't provide such flexible API as SQL database. 
We've decided to sacrifice data efficiency and make more computation in Java, but use Cassandra's 
scalability and high-availability potential. 
 
We need to switch storage on application servers one by one. To do so, we are going to restart the application
with a new version in parameter. Machines where we don't want to use new storage will be started either with old version
argument or no argument.

There is no prediction if a machine will be restarted with new or old version. **Machine can be also started
with new version and then switched back to an old version.**

Result
======

Functionality must remain (return only valid, max. 1 hour old session).

Final result should be an application where we can switch storages with a command line argument.

**Application runs on multiple servers so we need a graceful transition period from v1 to v2 storage.
 User can end on any server regardless of version, but must still be linked to the same session.**

Do not change:
- ```com.netsuite.cassandra``` package, because it's a fake driver
- ```com.netsuite.sql.SQLTool``` class, because it's used elsewhere
- ```com.netsuite.session.SessionService``` interface, because it's used elsewhere

We also evaluate code quality, so please spend time on a good design.

There's local git repository. Don't hesitate to commit your changes.

Setup
=====

You can build a project with a ```./gradlew build``` or ```gradlew.bat build```. 

We highly recommend Intellij IDEA Community edition (https://www.jetbrains.com/idea/download/). 
Then you can use ```gradlew.bat idea``` to generate project.


How to execute
==============

1. Package application to jar file: ```gradlew.bat jar``` 
2. Execute: ```java -jar build/libs/ns_infra_test_dist.jar```  

Final implementation could be executed as: 
* ```java -jar build/libs/ns_infra_test_dist.jar v1``` indicates usage of SQL
* ```java -jar build/libs/ns_infra_test_dist.jar v2``` indicates usage of Cassandra

There should be no need to install anything except gradle dependencies. All interfaces have fake implementations.






Test version: 20161013