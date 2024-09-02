INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 1, 'IMAX', 'Large format screen with high-resolution visuals and enhanced sound', NULL
WHERE NOT EXISTS (SELECT 1 FROM auditoriums WHERE id = 1);

INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 2, '4DX', 'Immersive experience with motion seats and environmental effects', NULL
WHERE NOT EXISTS (SELECT 1 FROM auditoriums WHERE id = 2);

INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 3, '3D', 'Three-dimensional projection for a depth-filled viewing experience', NULL
WHERE NOT EXISTS (SELECT 1 FROM auditoriums WHERE id = 3);

SELECT setval('auditoriums_id_seq', (SELECT COALESCE(MAX(id) + 1, 1) FROM auditoriums), false);