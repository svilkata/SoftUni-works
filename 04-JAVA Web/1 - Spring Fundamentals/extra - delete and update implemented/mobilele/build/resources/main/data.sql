INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password)
VALUES (1, "svilen.velikov@gmail.com", "Svilen", "Velikov", null, 1,
        "7a6d9c969b326b135a2ca5ed284e3534ce22e34f68006f2fe0713c3f6bbcf4bef4b2e7226b19bf03");

INSERT INTO brands (id, name)
VALUES (1, "Ford"),
(2, "Toyota");

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, "Fiesta", 'CAR', 1976, null, 1, "https://cdn.motor1.com/images/mgl/28rKM/s1/ford-fiesta-st-2022.jpg"),
    (2, "Escort", 'CAR', 1968, 2000, 1, "https://upload.wikimedia.org/wikipedia/commons/3/39/Ford_Escort_RS2000_MkI.jpg"),
    (3, "Yaris", 'CAR', 1999, null, 2, "https://i.ytimg.com/vi/Y-AylALKnEs/maxresdefault.jpg");


