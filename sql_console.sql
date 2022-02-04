SELECT *
FROM ticket
WHERE flight_id = 8;

SELECT *
FROM flight
WHERE id = 8;

EXPLAIN ANALYZE
SELECT *
FROM test2
LIMIT 20
    OFFSET 80000;

EXPLAIN ANALYZE
SELECT *
FROM test2
WHERE id > 80000
LIMIT 20;

SELECT *
FROM flight
LEFT JOIN ticket t
    ON flight.id = t.flight_id
WHERE flight.id = 3;