ALTER TABLE topics ADD status BOOL NOT NULL;
UPDATE topics SET status = 1;