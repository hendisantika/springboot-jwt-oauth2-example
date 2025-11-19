# Spring Boot JWT OAuth2 Example

A multi-module Spring Boot application demonstrating JWT (JSON Web Token) based OAuth2 authentication and authorization.

## Project Structure

This is a Maven multi-module project with the following modules:

```
springboot-jwt-oauth2-example/
├── auth-server/           # OAuth2 Authorization Server (Port 8080)
├── resource-server/       # Protected Resource Server (Port 8088)
├── aggregation-server/    # API Aggregation Server (Port 8888)
└── jwt-public-config/     # Shared JWT configuration library
```

### Module Descriptions

#### 1. auth-server (Port 8080)

The OAuth2 Authorization Server responsible for:

- User authentication
- Issuing JWT access tokens
- Managing OAuth2 clients
- Uses an in-memory user store (username: `user`, password: `testpass`)

**Key Components:**

- `OAuth2Config.java`: OAuth2 authorization server configuration
- `SecurityConfig.java`: Spring Security configuration
- Uses JWT token store with RSA key pair (jwt-test.jks)

#### 2. resource-server (Port 8088)

A protected resource server that:

- Validates JWT tokens issued by the auth-server
- Provides secured REST APIs
- Implements scope-based access control

**Available Endpoints:**

- `GET /helloworld` - Public endpoint
- `GET /api/me` - Returns current user's authentication details (requires OAuth2 scope: read)
- `GET /admin/**` - Admin endpoints (requires ROLE_ADMIN)

#### 3. aggregation-server (Port 8888)

An OAuth2 client that aggregates data from resource servers:

- Acts as an OAuth2 client
- Re-uses OAuth2 tokens to call resource servers
- Demonstrates token relay pattern

**Available Endpoints:**

- `GET /api/me` - Proxies request to resource-server

#### 4. jwt-public-config

A shared library module containing:

- Public JWT configuration
- Common JWT token converter beans
- Used by both resource servers for token validation

## Technology Stack

- **Spring Boot**: 3.5.7
- **Java**: 21
- **Spring Security**: 6.x
- **Spring Security OAuth2**: 2.5.2.RELEASE (Legacy - see Known Issues)
- **Spring Security JWT**: 1.1.1.RELEASE
- **Maven**: Multi-module project

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Building the Project

Build all modules:

```bash
mvn clean install
```

Build individual module:

```bash
cd <module-name>
mvn clean install
```

## Running the Application

The modules should be started in the following order:

### 1. Start the Authorization Server

```bash
cd auth-server
mvn spring-boot:run
```

The auth-server will start on `http://localhost:8080`

### 2. Start the Resource Server

```bash
cd resource-server
mvn spring-boot:run
```

The resource-server will start on `http://localhost:8088`

### 3. Start the Aggregation Server (Optional)

```bash
cd aggregation-server
mvn spring-boot:run
```

The aggregation-server will start on `http://localhost:8888`

## OAuth2 Clients Configuration

The following OAuth2 clients are pre-configured in the authorization server:

| Client ID                          | Secret     | Grant Types                                                               | Scopes             | Redirect URI                                 |
|------------------------------------|------------|---------------------------------------------------------------------------|--------------------|----------------------------------------------|
| tonr                               | secret     | authorization_code, implicit                                              | read, write        | -                                            |
| tonr-with-redirect                 | secret     | authorization_code, implicit                                              | read, write        | http://localhost:8080/tonr2/sparklr/redirect |
| my-client-with-registered-redirect | -          | authorization_code, client_credentials                                    | read, trust        | http://anywhere?key=value                    |
| my-trusted-client                  | -          | password, authorization_code, refresh_token, implicit                     | read, write, trust | -                                            |
| my-trusted-client-with-secret      | somesecret | password, authorization_code, refresh_token, implicit, client_credentials | read, write, trust | -                                            |
| my-less-trusted-client             | -          | authorization_code, implicit                                              | read, write, trust | -                                            |
| my-less-trusted-autoapprove-client | -          | implicit                                                                  | read, write, trust | -                                            |

## Testing the API

### Get an OAuth2 Token

```bash
curl -X POST \
  http://localhost:8080/oauth/token \
  -u my-trusted-client-with-secret:somesecret \
  -d 'grant_type=password&username=user&password=testpass'
```

### Access Protected Resource

```bash
curl -H "Authorization: Bearer <ACCESS_TOKEN>" \
  http://localhost:8088/api/me
```

### Access Public Resource

```bash
curl http://localhost:8088/helloworld
```

## Security Configuration

### Users

- **Username**: user
- **Password**: testpass
- **Role**: ROLE_USER

### JWT Configuration

- Uses RSA key pair stored in `jwt-test.jks`
- Keystore password: testpass
- Key alias: jwt-test

## Recent Changes

### Migration to Spring Boot 3

The project has been updated to work with Spring Boot 3.5.7, which required several changes:

1. **Jakarta EE Migration**: Changed from `javax.*` to `jakarta.*` packages
2. **Spring Security 6**: Updated security configuration to use the new lambda-based DSL
3. **Removed WebSecurityConfigurerAdapter**: Migrated to component-based security configuration
4. **Swagger Disabled**: Old Swagger dependencies (swagger-springmvc) are incompatible with Spring Boot 3 and have been
   disabled

### Fixed Build Issues

- Added missing dependency versions for OAuth2 libraries
- Fixed repository URL to use HTTPS
- Disabled Spring Boot Maven Plugin repackaging for jwt-public-config library module
- Updated Spring Security configuration syntax for Spring Security 6

## Known Issues and Limitations

⚠️ **Important**: This project uses the deprecated Spring Security OAuth 2.x framework (
`spring-security-oauth2:2.5.2.RELEASE`) which is **not officially supported** with Spring Boot 3 and Spring Security 6.

### Current Status

- ✅ **Compiles successfully** with Spring Boot 3.5.7
- ⚠️ **Runtime compatibility** may have issues due to deprecated OAuth2 stack
- ❌ **Swagger UI** is disabled (old swagger-springmvc not compatible with Jakarta EE)

### Recommended Migration Path

For production use with Spring Boot 3, consider migrating to:

- **[Spring Authorization Server](https://spring.io/projects/spring-authorization-server)** - Official replacement for
  Spring Security OAuth 2.x
- **[SpringDoc OpenAPI](https://springdoc.org/)** - Modern OpenAPI 3 documentation (replaces Swagger)

### Other Notes

- Some OAuth2 scope-based access rules (`#oauth2.hasScope('read')`) were converted to authority-based rules (
  `hasAuthority('SCOPE_read')`) for Spring Security 6 compatibility
- The application uses `NoOpPasswordEncoder` for simplicity - **not recommended for production**

## Project History

- **Created**: December 28, 2018
- **Original Author**: hendisantika
- **Last Updated**: November 19, 2025
- **Current Version**: 0.0.1-SNAPSHOT

## Contact

- **Email**: hendisantika@gmail.com
- **Telegram**: @hendisantika34

## License

This is a demo/example project for educational purposes.
