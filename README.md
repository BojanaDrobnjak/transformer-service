# Transformer Service

## Overview
The **Transformer Service** is a RESTful API built with **Spring Boot** that applies a series of transformations to string values. The API accepts requests with a collection of transformers and applies them in the specified order. This service is designed to process string operations such as regex removal, regex replacement, and conversion of Cyrillic/Greek letters to Latin.

## Features
- **Regex Removal**: Removes characters from a string that match a given regular expression.
- **Regex Replacement**: Replaces characters in a string that match a regular expression with a given replacement string.
- **Cyrillic/Greek to Latin Conversion**: Converts Cyrillic and Greek letters in a string to their Latin equivalents.

## Setup Instructions

1. **Clone the repository**:
    ```bash
    git clone git@github.com:BojanaDrobnjak/transformer-service.git
    cd transformer-service
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

4. The API will be available at `http://localhost:8080/api/transform`.


## Docker Setup

To containerize and run the application using Docker:

First, package the application using Maven:

```bash
mvn clean package
```

Then, build the Docker image and run the container:

```bash
docker build -t transformer-service .
```

Run the Docker container:
```bash
docker run -p 8080:8080 transformer-service
```

Using Docker Compose:

```bash
docker-compose up --build
```

## API Usage

### Endpoint
`GET /api/transformers/groups`

This endpoint retrieves all transformer groups, along with the transformers within each group. Each transformer group will include its group ID, group name, description, and a list of transformers.

### Response Body

The response body will be a JSON object with the following structure:

```json
[
   {
      "groupId": 1,
      "groupName": "TEXT_MANIPULATION",
      "groupDescription": "Transformers related to text manipulation",
      "transformers": [
         {
            "transformerId": 1,
            "name": "Regex Removal Transformer",
            "description": "Removes text based on a regular expression."
         },
         {
            "transformerId": 2,
            "name": "Regex Replace Transformer",
            "description": "Replaces text based on a regular expression."
         }
      ]
   },
   {
      "groupId": 2,
      "groupName": "CHARACTER_CONVERSION",
      "groupDescription": "Transformers related to character conversion",
      "transformers": [
         {
            "transformerId": 1,
            "name": "Cyrillic to Latin Transformer",
            "description": "Converts Cyrillic characters to their Latin equivalents."
         }
      ]
   }
]
```

### Endpoint
`POST /api/transformers/apply`

### Request Body

The request body should be a JSON object with the following structure:

```json
{
  "value": "string value",
  "transformers": [
    {
        "groupId": 1,
        "transformerId": 1,
        "parameters": {
            "regex": "regex pattern"
        }
    },
    {
        "groupId": 1,
        "transformerId": 2,
        "parameters": {
            "regex": "regex pattern",
            "replacement": "replacement string"
        }
    },
    {
        "groupId": 2,
        "transformerId": 1
    }
  ]
}
```

### Response Body

The response body will be a JSON object with the following structure:

```json
{
   "originalValue": "string value", 
   "transformedValue": "transformed string value"
}
```

## Swagger API Documentation

The Transformer Service exposes RESTful APIs, which are documented using **Swagger**.

After running the project, you can access the Swagger UI to view and interact with the APIs.

### URL:
`http://localhost:8080/api/swagger-ui/index.html`

The Swagger UI provides a visual interface for the API, including available endpoints, request/response formats, and status codes.

## Testing

The project includes unit tests for the transformer service. To run the tests, execute the following command:

```bash
mvn test
```