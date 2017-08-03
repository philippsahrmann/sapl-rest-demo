# sapl-rest-demo
A simple REST service built with Spring MVC that uses SAPL policies from the file system and provides PDP functionality.

## Change Combining Algorithm
POST `http://localhost:8080/combining-algorithm/deny-overrides`

## Reload Policies
POST/GET `http://localhost:8080/reload`

## PDP Request
POST `http://localhost:8080/decision`
