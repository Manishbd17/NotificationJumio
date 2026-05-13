Excalidraw Design Link : https://excalidraw.com/#json=_scQ-KBhKhe1IMa7U8p3n,vgQgMYGCPi94FvFXuATt6w

Notification System is a RESTful API built using Java and Spring Boot that supports sending notifications through multiple channels such as Email, SMS and Push Notifications. The system supports real-time and scheduled notifications along with priority-based processing, retry mechanisms and bulk notification delivery. The application is designed with clear separation of concerns between notification generation, scheduling, retry handling and channel delivery to ensure extensibility and maintainability.

The application uses RabbitMQ for asynchronous notification processing and H2 Database for storing notification history, scheduling information and user preferences. Retry handling is implemented using exponential backoff logic for transient failures. The project also follows extensible design principles using interfaces and factory patterns so that new channels such as WhatsApp or Slack can be integrated with minimal changes.

Features -  Send notifications using Email, SMS and Push channels Schedule notifications for future delivery, Retry failed notifications using exponential backoff, Priority-based notification, processing Bulk notification, support Notification status ,tracking Extensible channel architecture.


Unit tests for retry logic, priority handling and channel selection

API Endpoints

1. Register User 

POST http://localhost:8080/api/v1/users 

Sample Request : 

{
  "name": "John Root",
  "email": "john@gmail.com",
  "phone": "9876543210"
}

2. Send Notification 

POST http://localhost:8080/api/v1/notifications/send

Sample Request:

{
    "userId": 1,
    "channelType": "EMAIL",
    "notificationPriority": "HIGH",
    "title": "Payment Successful",
    "content": "Your payment was successful"
}

3. Schedule Notification

POST http://localhost:8080/api/v1/notifications/schedule

Sample Request:

{
    "userId": 1,
    "channelType": "SMS",
    "notificationPriority": "MEDIUM",
    "content": "Reminder Notification",
    "scheduledTime": "2026-05-11T10:30:00"
}


4. Bulk Notifications

POST http://localhost:8080/api/v1/notifications/bulk

5. Track Notification 

GET http://localhost:8080/api/v1/notifications/id/{id} 


Technologies Used

Java 17, Spring Boot, Rabbit MQ , H2 Database, Maven, JUnit,Docker, Mockito.


While using an IDE, we can right-click the main class and choose "Run" or use the IDE's run button.

Once started , the application will run on port 8080 . We can verify that through postman or any API tester using this URL : http://localhost:8080

Docker Command to connect to RabbitMQ instance  : 

docker run -d \ 
--hostname rabbitmq \
--name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
rabbitmq:3-management

POSTMAN Collection URL to test the APIs : https://jumioassignment.postman.co/workspace/JumioAssignment~e955d62e-a66a-48a2-8589-39e8fd5ce6f6/collection/33479886-1115e3a7-21db-4c94-af5c-22f19d8540f9?action=share&source=copy-link&creator=33479886

