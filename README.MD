### Car Inventory Management & Sales 
Created a REST API project for Car Inventory Management & Sales.
The system exposed the following endpoints to fulfill the use case requirements.

|                                        | Endpoint                                             |   
|----------------------------------------|------------------------------------------------------|
| Buyer: retrieve list of available cars | GET    /api/car/listCarsByCarAvailabilityStatus/OPEN |
| Buyer: buy cars                        | POST   /api/order                                    |
| Admin: add new car                     | POST   /api/car                                      |
| Admin: remove car                      | DELETE /api/car{id}                                  |

### Local Development
- Install Java JDK 1.8 or above
- Run the command under the project root folder `mvn spring-boot run`
