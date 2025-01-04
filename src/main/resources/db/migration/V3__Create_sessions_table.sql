CREATE TABLE sessions(
    id varchar(36) PRIMARY KEY,
    user_id INT NOT NULL ,
    expires_at DATETIME NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
)