# Redis Cache Session Persistence Demo

A demonstration application showcasing distributed session persistence using **Open Liberty**, **JCache (JSR-107)**, and **Redis** as the backing store. This application demonstrates how HTTP sessions can survive server restarts and be shared across multiple server instances.

## 🎯 Purpose

This application demonstrates:
- **Session Persistence**: HTTP sessions that survive server restarts
- **Distributed Sessions**: Sessions that can be shared across multiple server instances
- **JCache Integration**: Using JSR-107 JCache API with Redis
- **Open Liberty Configuration**: Proper setup of sessionCache feature

## 🏗️ Architecture

The application uses the following technology stack:

- **[Open Liberty](https://openliberty.io/)** - Lightweight Java application server
- **[JCache (JSR-107)](https://jcp.org/en/jsr/detail?id=107)** - Java caching API standard
- **[Redisson](https://redisson.org/)** - Redis Java client with JCache implementation
- **[Redis](https://redis.io/)** - In-memory data store for session persistence
- **[Maven](https://maven.apache.org/)** - Build and dependency management
- **Java 21** - Runtime environment

## 🚀 Features

### Session Management
- **Create/Read/Update/Delete** session attributes
- **Session invalidation** and recreation
- **Real-time session information** display (ID, creation time, last accessed)
- **Session persistence** across server restarts

### Interactive Testing Interface
- Web-based interface for session manipulation
- Add custom key-value pairs to sessions
- Remove individual session attributes
- Increment counters and add timestamps
- View all session metadata

### Redis Integration
- **Automatic serialization** using Jackson JSON codec
- **Configurable connection pooling** for optimal performance
- **Timeout and retry** configurations
- **Database selection** support

## 🛠️ Prerequisites

1. **Java 21** or higher
2. **Maven 3.6+**
3. **Redis Server** running on `localhost:6379`

## 🏃‍♂️ Quick Start

1. **Build and run the application:**
   ```bash
   mvn clean liberty:run
   ```

4. **Access the application:**
   - Open your browser to: http://localhost:9080/redistester   
   - Click "Test Session Persistence" to interact with sessions

## 🧪 Testing Session Persistence

1. **Add session data:**
   - Use the web interface to add session attributes
   - Note your session ID

2. **Test persistence:**
   ```bash
   # Stop the server (Ctrl+C in the terminal)
   # Restart the server
   mvn liberty:run
   ```

3. **Verify persistence:**
   - Return to the same URL
   - Your session data should still be present

