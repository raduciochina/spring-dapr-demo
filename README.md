# spring-dapr-demo
Spring Boot Microservices with Dapr Enabled in order to invoke each other.
First service, the content-calendar performs CRUD operations on data which is hold in a PostgreSQL database. The data is basically made up of tasks, a task
has several attributes like title, description, createdAt, deadline etc. 
The second service, the notification hub is constantly checking the data and when the deadline is approaching it sends a notification via email to an email 
address using SMTP protocol.
