INSERT INTO users (email, password, role, created_at, updated_at)
VALUES (
    'admin@mail.com', 
    '$2a$10$T8Z4B53p/2J0/055D0VvkeS4dIqL3f89YnFw24WpE.KxYx8/d7uDe', 
    'ADMIN', 
    CURRENT_TIMESTAMP, 
    CURRENT_TIMESTAMP
)
ON CONFLICT (email) DO NOTHING;
