# Example Neo4J

    This Java Project has the propose to demonstrate how to implement and use the Graph Database Neo4j.
    To run this project you will need to have:
        - Java 8
        - Neo4J (Configured, Up and Running)

    To run this project, you can build the source in your favorite IDE and select the "Main.java" to run create a new Neo4J DataBase.
    When you run the main, the runner will create a complete new Graph Database will auto generated information, and you can change the
    volume of information (number of nodes and relations) just changind the static parameters in the main class.

    If you need to configure the password of connections and some other parameter, please check the classe "Neo4JProperties.java".

# Graph Info
    This exemplo of Graph Database has a root node called FORECAST wich has a relation (edge) with COMPANY node(s).
    Below this COMPANY we have relation (edge) with PROFESSIONAL node(s) depth, and this professional have relation (edge)
    with CUSTOMER node(s).
    The last depth (4 depth levels) is the relation (edge) from CUSTOMER to PRODUCT, this relation is different from others, this relation is very similiar to
    many-to-many relation of relational database, and the others relations (edges) are one-to-many.
    Below the example of database structure:

    (FORECAST)-[]->(COMPANY)-[]->(PROFESSIONAL)-[]->(CUSTOMER)-[]->(PRODUCT)



# Test Scenario

    I have got tested this project in a windows enviroment with some specific Neo4J configurations:

    - Device:
          Intel i5 3210m (2 cores with 4 logical processors)
          8gb ram
          120gb SSD
          Windows 10 Pro
    - Java Version: 1.8.0_161 (Oracle)
    - Neo4j:
        version: neo4j-community-3.3.0
        conf/neo4j.conf:
                  dbms.pagecache.memory=2g
                  dbms.memory.heap.initial_size=3g
                  dbms.memory.heap.max_size=3g
                  dbms.threads.worker_count=4
                  dbms.memory.pagecache.size=1g
    - Result(s):
    - Test 6:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 700;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1.712
                    edge(s): 701.011
                time:
                    total time elapsed: 320835 ms (00:05:20.835)
        - Test 6:
                    parameters:
                        NUMBER_OF_COMPANYS = 1;
                        NUMBER_OF_PROFESSIONALS = 10;
                        NUMBER_OF_CUSTOMERS = 1000;
                        NUMBER_OF_PRODUCTS = 600;
                        USE_THREAD_POOL = true;
                    generate:
                        node(s): 1.612
                        edge(s): 601.011
                    time:
                        total time elapsed: 320316 ms (00:05:20.316)
        - Test 5:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 500;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1.512
                    edge(s): 501.011
                time:
                    total time elapsed: 264267 ms (00:04:24.267)
        - Test 4:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 400;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1412
                    edge(s): 401011
                time:
                    total time elapsed: 216015 ms (00:03:36.015)
        - Test 3:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 300;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1312
                    edge(s): 301011
                time:
                    total time elapsed: 139149 ms (00:02:19.149)
        - Test 2:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 200;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1212
                    edge(s): 201011
                time:
                    total time elapsed: 105988 ms (00:01:45.988)
        - Test 1:
                parameters:
                    NUMBER_OF_COMPANYS = 1;
                    NUMBER_OF_PROFESSIONALS = 10;
                    NUMBER_OF_CUSTOMERS = 1000;
                    NUMBER_OF_PRODUCTS = 100;
                    USE_THREAD_POOL = true;
                generate:
                    node(s): 1112
                    edge(s): 101011
                time:
                    total time elapsed: 58297 ms (00:00:58.297)