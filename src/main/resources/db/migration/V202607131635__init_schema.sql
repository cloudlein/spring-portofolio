-- ==============================================================================
-- Flyway Database Migration
-- Version: V202607131635
-- Description: Init Schema for Portfolio & CV Management System
-- ==============================================================================

-- 1. Users table (Authentication & Roles)
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'ADMIN',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. CV Profiles (Personal Details associated with a user)
CREATE TABLE IF NOT EXISTS cv_profiles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    title VARCHAR(150), -- e.g. "Fullstack Software Engineer"
    summary TEXT,
    email VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    profile_pic_url VARCHAR(512),
    github_url VARCHAR(512),
    linkedin_url VARCHAR(512),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cv_profiles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 3. Work Experiences
CREATE TABLE IF NOT EXISTS work_experiences (
    id BIGSERIAL PRIMARY KEY,
    cv_profile_id BIGINT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    position VARCHAR(150) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE, -- NULL if currently working here
    is_current_job BOOLEAN DEFAULT FALSE,
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_work_experiences_cv FOREIGN KEY (cv_profile_id) REFERENCES cv_profiles(id) ON DELETE CASCADE
);

-- 4. Educations
CREATE TABLE IF NOT EXISTS educations (
    id BIGSERIAL PRIMARY KEY,
    cv_profile_id BIGINT NOT NULL,
    institution VARCHAR(255) NOT NULL,
    degree VARCHAR(150) NOT NULL, -- e.g. "Bachelor of Science"
    major VARCHAR(150), -- e.g. "Computer Science"
    start_date DATE NOT NULL,
    end_date DATE,
    gpa NUMERIC(3, 2), -- e.g. 3.75
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_educations_cv FOREIGN KEY (cv_profile_id) REFERENCES cv_profiles(id) ON DELETE CASCADE
);

-- 5. Skills
CREATE TABLE IF NOT EXISTS skills (
    id BIGSERIAL PRIMARY KEY,
    cv_profile_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    level VARCHAR(50), -- e.g. "Beginner", "Intermediate", "Advanced"
    category VARCHAR(100), -- e.g. "Programming Languages", "DevOps", "Soft Skills"
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_skills_cv FOREIGN KEY (cv_profile_id) REFERENCES cv_profiles(id) ON DELETE CASCADE
);

-- 6. Projects
CREATE TABLE IF NOT EXISTS projects (
    id BIGSERIAL PRIMARY KEY,
    cv_profile_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    technologies_used VARCHAR(255), -- Comma-separated or structured text
    project_url VARCHAR(512),
    repo_url VARCHAR(512),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_projects_cv FOREIGN KEY (cv_profile_id) REFERENCES cv_profiles(id) ON DELETE CASCADE
);

-- Indices for performance optimization
CREATE INDEX IF NOT EXISTS idx_cv_profiles_user ON cv_profiles(user_id);
CREATE INDEX IF NOT EXISTS idx_work_experiences_cv ON work_experiences(cv_profile_id);
CREATE INDEX IF NOT EXISTS idx_educations_cv ON educations(cv_profile_id);
CREATE INDEX IF NOT EXISTS idx_skills_cv ON skills(cv_profile_id);
CREATE INDEX IF NOT EXISTS idx_projects_cv ON projects(cv_profile_id);
