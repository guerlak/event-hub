# EventHub API

EventHub is a backend application built with Spring Boot to manage events. It provides endpoints to create, list, and retrieve details of events.

## 🚀 Technologies

- **Java 21**
- **Spring Boot 4.0.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Docker & Docker Compose**
- **Maven**

## 📋 Features

- [x] Create a new event
- [x] List all events
- [x] Retrieve details of a specific event by ID

## 🛠️ How to Run

### Prerequisites

- JDK 21
- Docker and Docker Compose
- Maven (optional, you can use `./mvnw`)

### Step-by-Step

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/eventhub.git
    cd eventhub
    ```

2.  **Start the database:**
    The project includes a `docker-compose.yml` file to quickly set up a PostgreSQL instance.
    ```bash
    docker-compose up -d
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start at `http://localhost:8080`.

## 📡 API Endpoints

### Events

#### Create an Event
`POST /events`

**Request Body:**
```json
{
  "title": "Spring Boot Workshop",
  "details": "A comprehensive guide to Spring Boot 4",
  "maximumAttendees": 100
}
```

#### Get Event Details
`GET /events/{id}`

**Response:**
```json
{
  "id": "uuid",
  "title": "Spring Boot Workshop",
  "details": "A comprehensive guide to Spring Boot 4",
  "maximumAttendees": 100,
  "attendeesAmount": 0
}
```

#### List All Events
`GET /events`

**Response:**
```json
{
  "events": [
    {
      "id": "uuid",
      "title": "Spring Boot Workshop",
      "details": "A comprehensive guide to Spring Boot 4",
      "maximumAttendees": 100,
      "attendeesAmount": 0
    }
  ]
}
```

## 🏗️ Project Structure

- `br.com.guerlak.eventhub.controller`: REST controllers.
- `br.com.guerlak.eventhub.domain`: Entity models.
- `br.com.guerlak.eventhub.dto`: Data Transfer Objects for API requests and responses.
- `br.com.guerlak.eventhub.repositories`: Spring Data JPA repositories.
- `br.com.guerlak.eventhub.services`: Business logic.
- `br.com.guerlak.eventhub.infra.exceptions`: Global exception handling.

## 📄 License

MIT
