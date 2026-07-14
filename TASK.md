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
- [ ] Implement User Registration & Authentication (JWT-based with BCrypt)
- [ ] Configure Spring Security Rules (`SecurityConfig` classes)
- [ ] Implement JWT validation filter & exceptions handling for unauthenticated requests
- [ ] Verify security configurations using integration tests

---

## Phase 3: CV & Portfolio Domain Implementation
- [ ] **User Domain**:
  - [ ] Entities, DTOs, and MapStruct Mapper
  - [ ] Repository, UserService, and Profile endpoints
- [ ] **CV Profile Domain**:
  - [ ] Entities, DTOs, and Mappers
  - [ ] CRUD endpoints for CV Profile (personal details, summary, image upload)
- [ ] **Work Experience Domain**:
  - [ ] CRUD endpoints & associations
- [ ] **Education Domain**:
  - [ ] CRUD endpoints & associations
- [ ] **Skills & Projects Domains**:
  - [ ] CRUD endpoints & associations

---

## Phase 4: Integrations & Services
- [ ] **Supabase Storage Integration**:
  - [ ] Configure HttpClient or SDK to communicate with Supabase storage buckets
  - [ ] Implement file upload service for profile photos/portfolios
- [ ] **Caching Layer**:
  - [ ] Configure Spring Cache & Redis connection settings
  - [ ] Apply `@Cacheable` and `@CacheEvict` annotations to CV read/write operations

---

## Phase 5: Verification & Tests
- [ ] Write Unit Tests for Services (using Mockito)
- [ ] Write Integration Tests for Repositories and Controllers (using Testcontainers PostgreSQL)
- [ ] Set up Actuator health checks and customized JSON metrics representation

---

## Phase 6: Build & Production Deployment
- [ ] Run native compilation verification using GraalVM `./gradlew nativeCompile`
- [ ] Build production image using `Dockerfile`
- [ ] Deploy to Railway and perform live diagnostics

