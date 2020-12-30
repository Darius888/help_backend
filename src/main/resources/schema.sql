DROP TABLE IF EXISTS help_seeker CASCADE;
DROP TABLE IF EXISTS helper CASCADE;
DROP TABLE IF EXISTS profile_photo CASCADE;
DROP TABLE IF EXISTS average_score CASCADE;
DROP TABLE IF EXISTS job CASCADE;
DROP TABLE IF EXISTS job_photo CASCADE;
DROP TABLE IF EXISTS user_review CASCADE;


CREATE TABLE help_seeker
(
help_seeker_id SERIAL PRIMARY KEY NOT NULL,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
email varchar(100) NOT NULL,
username varchar(100) NOT NULL,
password varchar(200) NOT NULL
);

CREATE TABLE helper
(
helper_id SERIAL PRIMARY KEY NOT NULL,
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
user_type varchar(100) NOT NULL,
image_name varchar(100) NOT NULL,
po_help_seeker_id integer REFERENCES help_seeker (help_seeker_id),
po_helper_id integer REFERENCES helper (helper_id)
);

CREATE TABLE average_score
(
average_score_id SERIAL PRIMARY KEY NOT NULL,
user_type varchar(100) NOT NULL,
user_score float NOT NULL,
average_score_help_seeker_id integer REFERENCES help_seeker(help_seeker_id),
average_score_helper_id integer REFERENCES helper(helper_id)
);

CREATE TABLE job
(
job_id SERIAL PRIMARY KEY NOT NULL,
job_title varchar(100) NOT NULL,
job_post_date date NOT NULL,
job_type varchar(100) NOT NULL,
job_description varchar(500) NOT NULL,
job_reward varchar(200) NOT NULL,
job_status varchar(100) NOT NULL,
job_owner_help_seeker_id integer REFERENCES help_seeker(help_seeker_id),
job_favored_by_helper_id integer REFERENCES helper(helper_id),
job_favored_status varchar(100)
);

CREATE TABLE job_photo
(
job_photo_id SERIAL PRIMARY KEY NOT NULL,
absolute_path varchar(100) NOT NULL,
image_name varchar(100) NOT NULL,
related_job_id integer REFERENCES job(job_id)
);


CREATE TABLE user_review
(
user_review_id SERIAL PRIMARY KEY NOT NULL,
user_review_type varchar(100) NOT NULL,
user_review varchar(500) NOT NULL,
review_owner_help_seeker_id integer REFERENCES help_seeker(help_seeker_id),
review_owner_helper_id integer REFERENCES helper(helper_id)
);






