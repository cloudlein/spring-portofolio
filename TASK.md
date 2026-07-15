# CV & Portfolio Project Tasks

Track the progress of your application development phases below.

---

## Phase 1: Core Configuration & Foundation
- [x] Initial configuration (`.env`, `.env.example`, `.gitignore`)
- [x] Profiles setup (`application.yaml`, `application-dev.yaml`, `application-prod.yaml`)
- [x] Flyway initialization and Schema design (`V202607131635__init_schema.sql`)
- [x] Dockerfile template setup

---

## Phase 2: Security & Authentication
- [x] Implement User Registration & Authentication (JWT-based with BCrypt via API)
- [x] Configure Spring Security Rules (`SecurityConfig` classes to support API auth and Cookie MVC pages)
- [x] Implement JWT validation filter (extracting token from cookie) & exception handling
- [ ] Verify security configurations using integration tests

---

## Phase 3: CV & Portfolio Domain Implementation
- [ ] **User Domain**:
  - [x] Entities, DTOs, and MapStruct Mapper
  - [x] Repository, UserService, and Profile views/controllers
- [ ] **CV Profile Domain**:
  - [/] Entities, DTOs, and Mappers (Entity `CvProfile` implemented, DTOs & Mappers pending)
  - [ ] MVC Controllers and Thymeleaf Views for CV Profile (personal details, summary, image upload via cookie-authenticated forms)
- [ ] **Work Experience Domain**:
  - [/] Entities (implemented in `WorkExperience`), MVC Controllers and Views for Work Experience CRUD & associations
- [ ] **Education Domain**:
  - [/] Entities (implemented in `Education`), MVC Controllers and Views for Education CRUD & associations
- [ ] **Skills & Projects Domains**:
  - [/] Entities (implemented in `Skill` & `Project`), MVC Controllers and Views for Skills & Projects CRUD & associations

---

## Phase 4: Integrations & Services
- [ ] **Supabase Storage Integration**:
  - [ ] Configure HttpClient or SDK to communicate with Supabase storage buckets
  - [ ] Implement file upload service for profile photos/portfolios
- [ ] **Caching Layer**:
  - [/] Configure Spring Cache & Redis connection settings (Configuration properties added, caching not yet enabled or applied)
  - [ ] Apply `@Cacheable` and `@CacheEvict` annotations to CV read/write operations

---

## Phase 5: Verification & Tests
- [ ] Write Unit Tests for Services (using Mockito)
- [/] Write Integration Tests for Repositories and MVC Controllers (using Testcontainers PostgreSQL) (Basic Testcontainers configuration and repository test set up, controller tests pending)
- [ ] Set up Actuator health checks and customized JSON metrics representation

---

## Phase 6: Build & Production Deployment
- [ ] Run native compilation verification using GraalVM `./gradlew nativeCompile`
- [ ] Build production image using `Dockerfile`
- [ ] Deploy to Railway and perform live diagnostics

