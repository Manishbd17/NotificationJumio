CREATE TABLE users (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255),
       email VARCHAR(255),
       phone VARCHAR(50),
       device_token VARCHAR(255),
       created_at TIMESTAMP,
       updated_at TIMESTAMP
);

CREATE TABLE notifications (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       user_id BIGINT,
       title VARCHAR(255),
       content TEXT,
       channel_type VARCHAR(50),
       priority VARCHAR(50),
       status VARCHAR(50),
       scheduled_time TIMESTAMP,
       sent_at TIMESTAMP,
       retry_count INT,
       next_retry_time TIMESTAMP,
       failure_reason TEXT,
       created_at TIMESTAMP,
       updated_at TIMESTAMP,

       CONSTRAINT fk_user
       FOREIGN KEY (user_id)
       REFERENCES users(id)
);

CREATE TABLE notification_preferences (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      user_id BIGINT,
      channel_type VARCHAR(50),
      enabled BOOLEAN,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,

      CONSTRAINT fk_pref_user
      FOREIGN KEY (user_id)
      REFERENCES users(id)
);

CREATE TABLE recurring_notifications (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     notification_id BIGINT,
     cron_expression VARCHAR(255),
     next_execution_time TIMESTAMP,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,

     CONSTRAINT fk_notification
     FOREIGN KEY (notification_id)
     REFERENCES notifications(id)
);