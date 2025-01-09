CREATE EVENT delete_expired_sessions
ON SCHEDULE EVERY 1 HOUR
STARTS '2025-01-09 15:15:00'
DO
DELETE FROM sessions WHERE expires_at < NOW()