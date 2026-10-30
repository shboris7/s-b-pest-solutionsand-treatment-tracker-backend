# SB Pest Solutions and Treatment Tracker (Backend)

- **Student:** Sharangabo Boris
- **Student ID:** 27009
- **Project:** SB Pest Solutions and Treatment Tracker
- **Course:** INSY 8322 Web Technology and Internet, AUCA
- **Lecturer:** Patrick DUSHIMIMANA

## About the project
REST API that digitalizes the scheduling of a pest control business in Rwanda.
A client requests a service job. A technician, who drives one service van and is
certified for several treatment types, performs it. The job moves from REQUESTED
to DISPATCHED and ends TREATED or CALLED_OFF. Once a job is TREATED, the
technician logs the treatment products used.

Built with Spring Boot, Spring Data JPA and PostgreSQL. Entities are used directly
as request and response bodies (no DTOs).

## Tech stack
- Java 17
- Spring Boot 3.x (Spring Web, Spring Data JPA)
- PostgreSQL (JDBC driver)
- Maven (wrapper included)

## Steps to run the API
1. Install Java 17 and PostgreSQL, and start PostgreSQL.
2. Clone this repository and open the project folder (the one containing `pom.xml`).
3. In pgAdmin (or psql), create an empty database named `pest_tracker`.
4. Create the file `src/main/resources/application-local.properties` with one line:
   `spring.datasource.password=@Delabosa2003`
   This file is listed in `.gitignore`, so the password is never committed.
   The username (`postgres`), URL and port are set in `application.properties`.
5. Start the API: `./mvnw spring-boot:run` (Windows PowerShell: `.\mvnw spring-boot:run`).
6. The API runs on `http://localhost:8083`. Hibernate creates the tables
   automatically (`ddl-auto=update`) and the SQL is printed in the console.

## Seed data
Enter the data through the `save` endpoints in this order, because later records
reference earlier ones by id:
1. 4 treatment types (Termite Control, Rodent Control, Fumigation, Mosquito Spraying)
2. 4 vans (numbers 1 to 4)
3. 5 clients (two share the last name "Sharangabo")
4. 3 technicians, each with a van and treatment types (Van 4 stays free,
   nobody holds Mosquito Spraying)
5. 10 service jobs spread over October and November 2026, using all four statuses

Note: the seed service jobs were entered before the D1 rules were added. D1 now
rejects past dates and always starts a new job as REQUESTED, so on an empty
database the past-dated and non-REQUESTED jobs cannot be recreated through the
save endpoint alone.

## Testing
Import `s-b-pest-solutionsand-treatment-tracker-midterm.postman_collection.json`
(in the repository root) into Postman. It has one request per requirement, named
by its ID. Base URL: `http://localhost:8083`.

## Endpoints by requirement
| ID | Endpoint |
| --- | --- |
| A2 | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` where `{path}` is `treatment-types`, `vans`, `technicians`, `clients` or `service-jobs` |
| B1 | `GET /api/clients/by-last-name?lastName=` |
| B2 | `GET /api/service-jobs/by-status?status=` |
| B3 | `GET /api/service-jobs/between?start=&end=` |
| C1 | `GET /api/technicians/by-treatment-type?name=` |
| C2 | `GET /api/vans/free` |
| C3 | `GET /api/treatment-types/unused` |
| C4 | `GET /api/clients/of-technician/{technicianId}` |
| D1 | `POST /api/service-jobs/save` |
| D2 | `PATCH /api/service-jobs/{id}/status?status=` |
| D3 | `PUT /api/technicians/{technicianId}/treatment-types/{treatmentTypeId}` |
| D4 | `PATCH /api/technicians/{technicianId}/van?vanNumber=` |
| E1 | `POST /api/treatment-logs/save?serviceJobId=` |
| E2 | `GET /api/treatment-logs/client/{phone}` |
| E3 | `DELETE /api/treatment-logs/delete/{id}` and `DELETE /api/service-jobs/delete/{id}` |
| F1 | `GET /api/service-jobs/stats/by-status` |
| F2 | `PATCH /api/service-jobs/cancel-day?technicianId=&date=` |
| Bonus | `GET /api/dashboard/summary`, `GET /api/service-jobs/page?page=&size=&sort=` |

## Project structure
Package `sbpestsolution.rw`: `domain` (entities and the `JobStatus` enum),
`repository`, `service` and `controller`.

## Use of AI assistance
I used AI assistance for faster completion and to understand some code lines
and the project as a whole. Most of the codes were typed manually for deepening the understanding and learning.