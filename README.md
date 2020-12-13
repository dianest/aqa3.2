#### **How to run tests**

1. Launch docker container with the database. It will import schema from _sql-schema/schema.sql_
2. Start SUT located in _artifacts/app-deadline.jar_. It will populate demo data into the database
3. Run tests. After tests will finish, demo data will be cleaned from the database.
4. Shut down SUT.
5. To run tests again repeat steps 2-4.