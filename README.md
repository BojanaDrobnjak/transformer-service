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
    ./mvnw clean install
    ```

3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. The API will be available at `http://localhost:8080/api/transform`.


## API Usage

### Endpoint
`POST /api/transform`

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
        "groupId": 2,
        "transformerId": 2,
        "parameters": {
            "regex": "regex pattern",
            "replacement": "replacement string"
        }
    },
    {
        "groupId": 3,
        "transformerId": 3
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

## Testing

The project includes unit tests for the transformer service. To run the tests, execute the following command:

```bash
./mvnw test
```