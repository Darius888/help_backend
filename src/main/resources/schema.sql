DROP TABLE IF EXISTS helpo_user CASCADE;
DROP TABLE IF EXISTS profile_photo CASCADE;
DROP TABLE IF EXISTS average_score CASCADE;
DROP TABLE IF EXISTS helpo_job CASCADE;
DROP TABLE IF EXISTS job_photo CASCADE;
DROP TABLE IF EXISTS user_review CASCADE;


CREATE TABLE helpo_user
(
helpo_user_id SERIAL PRIMARY KEY NOT NULL,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
email varchar(100) NOT NULL,
username varchar(100) NOT NULL,
password varchar(200) NOT NULL
);

CREATE TABLE profile_photo
(
profile_photo_id SERIAL PRIMARY KEY NOT NULL,
absolute_path varchar(100) NOT NULL,
image_name varchar(100) NOT NULL,
po_helpo_user_id integer REFERENCES helpo_user(helpo_user_id)
);

CREATE TABLE average_score
(
score_id SERIAL PRIMARY KEY NOT NULL,
user_score float NOT NULL,
score_owner_id integer REFERENCES helpo_user(helpo_user_id),
score_receiver_id integer REFERENCES helpo_user(helpo_user_id)
);

CREATE TABLE helpo_job
(
helpo_job_id SERIAL PRIMARY KEY NOT NULL,
job_title varchar(100) NOT NULL,
job_post_date varchar(100)  NOT NULL,
job_type varchar(100) NOT NULL,
job_description varchar(500) NOT NULL,
job_reward varchar(200) NOT NULL,
job_status varchar(100) NOT NULL,
job_owner_id integer REFERENCES helpo_user(helpo_user_id),
job_favored_status varchar(100)
);

CREATE TABLE job_photo
(
job_photo_id SERIAL PRIMARY KEY NOT NULL,
absolute_path varchar(100) NOT NULL,
image_name varchar(100) NOT NULL,
related_job_id integer REFERENCES helpo_job(helpo_job_id)
);


CREATE TABLE user_review
(
user_review_id SERIAL PRIMARY KEY NOT NULL,
user_review varchar(500) NOT NULL,
review_owner_id integer REFERENCES helpo_user(helpo_user_id),
review_receiver_id integer REFERENCES helpo_user(helpo_user_id)
);






