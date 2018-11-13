What needs to be done
=====================

This application stub works with sessions stored in relational storage and runs on **multiple servers**.

We've chosen Cassandra as a new session storage. However it doesn't provide such flexible API as SQL database. 
We've decided to sacrifice data efficiency and make more computation in Java, but use Cassandra's 
scalability and high-availability potential. 
 
We need to test the new storage on applications servers one by one. To do so, we are going to deploy new code 
and start the application with a new parameter `v2`. Machines where we don't want to use new storage 
will be either kept as is or deployed with a new code and started with **either no argument or a `v1` version argument**.

There is no prediction which machine will be started with new or old version. Machine can be also started
with the new `v2` version and then switched back to the old `v1` version. 

User must know no difference even though he can end up on any server in any version.  

Sample code is part of bigger application, so please do not change:

- ```com.netsuite.cassandra``` package, because it's a fake driver
- ```com.netsuite.sql.SQLTool``` class, because it's used elsewhere in the code
- ```com.netsuite.session.SessionService``` interface, because it's used elsewhere in the code

Functionality must be preserved (return only valid, max. 1 hour old session).

Final result
============

Session created in any version must be available on all servers including the ones 
where the new code haven't been released yet.

Setup
=====

You can build a project with a ```./gradlew build``` or ```./gradlew.bat build```. 

We highly recommend Intellij IDEA Community edition (https://www.jetbrains.com/idea/download/).


How to execute
==============

Execute current version: ```java App.java```  

Final implementation must be executed with ``` java App.java v1``` or ``` java App.java v2``` where v1 indicates usage of SQL, v2 indicates usage of Cassandra

There's no need to install anything except gradle dependencies. All interfaces have fake implementations.
