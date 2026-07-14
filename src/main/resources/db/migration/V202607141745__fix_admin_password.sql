-- ==============================================================================
-- Flyway Database Migration
-- Version: V202607141745
-- Description: Fix Default Admin Password to BCrypt hash of 'admin123'
-- ==============================================================================

UPDATE users 
SET password = '$2a$10$pw32/YE0FamkqnIOqIPifuDln.USI97erU3hkA0YFTOZlyCTAdppe' 
WHERE email = 'admin@mail.com';
