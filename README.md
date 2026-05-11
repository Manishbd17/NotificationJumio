Excalidraw Design Link : https://excalidraw.com/#json=_scQ-KBhKhe1IMa7U8p3n,vgQgMYGCPi94FvFXuATt6w

Notification System is a RESTful API built using Java and Spring Boot that supports sending notifications through multiple channels such as Email, SMS and Push Notifications. The system supports real-time and scheduled notifications along with priority-based processing, retry mechanisms and bulk notification delivery. The application is designed with clear separation of concerns between notification generation, scheduling, retry handling and channel delivery to ensure extensibility and maintainability.

The application uses RabbitMQ for asynchronous notification processing and H2 Database for storing notification history, scheduling information and user preferences. Retry handling is implemented using exponential backoff logic for transient failures. The project also follows extensible design principles using interfaces and factory patterns so that new channels such as WhatsApp or Slack can be integrated with minimal changes.

Features Send notifications using Email, SMS and Push channels Schedule notifications for future delivery Retry failed notifications using exponential backoff Priority-based notification processing Bulk notification support Notification status tracking Extensible channel architecture Unit tests for retry logic, priority handling and channel selection

API Endpoints

Send Notification POST /api/v1/notifications/send Sample Request:

{ "userId": 1, "channelType": "EMAIL", "priority": "HIGH", "title": "Payment Successful", "content": "Your payment was successful" }

Schedule Notification POST /api/v1/notifications/send Sample Request:

{ "userId": 1, "channelType": "SMS", "priority": "MEDIUM", "content": "Reminder Notification", "scheduledTime": "2026-05-11T10:30:00" }

Bulk Notifications POST /api/v1/notifications/bulk

Get Notification Status GET /api/v1/notifications/{id}

Technologies Used

Java 17, Spring Boot, Apache Kafka, H2 Database, Maven, JUnit, Mockito.
