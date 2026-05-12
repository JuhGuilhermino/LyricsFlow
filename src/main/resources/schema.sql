
-- ENUMs
CREATE TYPE learning_goal   AS ENUM ('travel', 'work', 'study', 'leisure');
CREATE TYPE language_level  AS ENUM ('beginner', 'intermediate', 'advanced');

-- ============================================================
-- 1. USERS
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
    id                    SERIAL          PRIMARY KEY,
    username              VARCHAR(50)     NOT NULL UNIQUE,
    email                 VARCHAR(255)    NOT NULL UNIQUE,
    password_hash         VARCHAR(255)    NOT NULL,
    avatar                TEXT,
    goal                  learning_goal   NOT NULL DEFAULT 'study',
    level                 language_level  NOT NULL DEFAULT 'beginner',
    level_defined_by_test BOOLEAN         NOT NULL DEFAULT FALSE,
    created_at            TIMESTAMP       NOT NULL DEFAULT NOW()
);

-- ============================================================
-- 2. SONGS
-- ============================================================
CREATE TABLE IF NOT EXISTS songs (
    id         SERIAL          PRIMARY KEY,
    title      TEXT            NOT NULL,
    artist     TEXT            NOT NULL,
    album      TEXT,
    year       INT,
    youtube_id VARCHAR(50),
    goal       learning_goal   NOT NULL DEFAULT 'study',
    level      language_level  NOT NULL DEFAULT 'beginner'
);

-- ============================================================
-- 3. LYRICS_LINE
-- ============================================================
CREATE TABLE IF NOT EXISTS lyrics_line (
    id                        SERIAL   PRIMARY KEY,
    song_id                   INT      NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    line_num                  INT      NOT NULL,
    english                   TEXT     NOT NULL,
    english_mask_beginner     TEXT,
    english_mask_intermediate TEXT,
    english_mask_advanced     TEXT,
    portuguese                TEXT,
    UNIQUE (song_id, line_num)
);

CREATE INDEX IF NOT EXISTS idx_lyrics_line_song_id ON lyrics_line (song_id);

-- ============================================================
-- 4. FLASHCARD
-- ============================================================
CREATE TABLE IF NOT EXISTS flashcard (
    id                SERIAL        PRIMARY KEY,
    user_id           INT           NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    word              VARCHAR(100)  NOT NULL,
    next_review_date  DATE          NOT NULL DEFAULT CURRENT_DATE,
    interval          INT           NOT NULL DEFAULT 1,
    ease_factor       FLOAT         NOT NULL DEFAULT 2.5,
    learning_progress FLOAT         NOT NULL DEFAULT 0.0,
    created_at        TIMESTAMP     NOT NULL DEFAULT NOW(),
    UNIQUE (user_id, word)
);

CREATE INDEX IF NOT EXISTS idx_flashcard_user_id     ON flashcard (user_id);
CREATE INDEX IF NOT EXISTS idx_flashcard_review_date ON flashcard (user_id, next_review_date);

-- ============================================================
-- 5. TASK
-- ============================================================
CREATE TABLE IF NOT EXISTS task (
    id           SERIAL    PRIMARY KEY,
    user_id      INT       NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    song_id      INT       NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
    order_index  INT       NOT NULL DEFAULT 1,
    is_completed BOOLEAN   NOT NULL DEFAULT FALSE,
    is_current   BOOLEAN   NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (user_id, song_id)
);

CREATE INDEX IF NOT EXISTS idx_task_user_id ON task (user_id);
CREATE INDEX IF NOT EXISTS idx_task_song_id ON task (song_id);

-- ============================================================
-- 6. USER_PROGRESS
-- ============================================================
CREATE TABLE IF NOT EXISTS user_progress (
    id                   SERIAL    PRIMARY KEY,
    user_id              INT       NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    tasks_completed      INT       NOT NULL DEFAULT 0,
    words_learned        INT       NOT NULL DEFAULT 0,
    flash_cards_progress FLOAT     NOT NULL DEFAULT 0.0,
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW()
);

-- ============================================================
-- TRIGGERS
-- ============================================================

CREATE OR REPLACE FUNCTION update_user_progress_timestamp()
RETURNS TRIGGER LANGUAGE plpgsql AS
'BEGIN NEW.updated_at = NOW(); RETURN NEW; END;';

DROP TRIGGER IF EXISTS trg_user_progress_updated_at ON user_progress;
CREATE TRIGGER trg_user_progress_updated_at
BEFORE UPDATE ON user_progress
FOR EACH ROW EXECUTE FUNCTION update_user_progress_timestamp();

CREATE OR REPLACE FUNCTION create_user_progress()
RETURNS TRIGGER LANGUAGE plpgsql AS
'BEGIN INSERT INTO user_progress (user_id) VALUES (NEW.id); RETURN NEW; END;';

DROP TRIGGER IF EXISTS trg_create_user_progress ON users;
CREATE TRIGGER trg_create_user_progress
AFTER INSERT ON users
FOR EACH ROW EXECUTE FUNCTION create_user_progress();

CREATE OR REPLACE FUNCTION sync_flashcards_progress()
RETURNS TRIGGER LANGUAGE plpgsql AS
'BEGIN
    UPDATE user_progress
    SET flash_cards_progress = (
        SELECT COALESCE(AVG(learning_progress), 0.0)
        FROM flashcard WHERE user_id = NEW.user_id
    )
    WHERE user_id = NEW.user_id;
    RETURN NEW;
END;';

DROP TRIGGER IF EXISTS trg_sync_flashcards_progress ON flashcard;
CREATE TRIGGER trg_sync_flashcards_progress
AFTER INSERT OR UPDATE ON flashcard
FOR EACH ROW EXECUTE FUNCTION sync_flashcards_progress();

CREATE OR REPLACE FUNCTION sync_words_learned()
RETURNS TRIGGER LANGUAGE plpgsql AS
'BEGIN
    UPDATE user_progress
    SET words_learned = (
        SELECT COUNT(*) FROM flashcard WHERE user_id = NEW.user_id
    )
    WHERE user_id = NEW.user_id;
    RETURN NEW;
END;';

DROP TRIGGER IF EXISTS trg_sync_words_learned ON flashcard;
CREATE TRIGGER trg_sync_words_learned
AFTER INSERT OR DELETE ON flashcard
FOR EACH ROW EXECUTE FUNCTION sync_words_learned();

CREATE OR REPLACE FUNCTION sync_task_completed()
RETURNS TRIGGER LANGUAGE plpgsql AS
'BEGIN
    IF NEW.is_completed = TRUE AND OLD.is_completed = FALSE THEN
        UPDATE user_progress
        SET tasks_completed = tasks_completed + 1
        WHERE user_id = NEW.user_id;
    END IF;
    RETURN NEW;
END;';

DROP TRIGGER IF EXISTS trg_sync_task_completed ON task;
CREATE TRIGGER trg_sync_task_completed
AFTER UPDATE ON task
FOR EACH ROW EXECUTE FUNCTION sync_task_completed();