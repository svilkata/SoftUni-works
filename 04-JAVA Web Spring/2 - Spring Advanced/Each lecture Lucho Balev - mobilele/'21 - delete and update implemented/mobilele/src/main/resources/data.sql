-- INSERT INTO users (id, email, first_name, last_name, image_url, is_active, password, username)
-- VALUES (1, "svilen.velikov@gmail.com", "Svilen", "Velikov", null, 1,
--         "fd9d0d64752fe063ec7bc519a01cdde03b746326812a56909ac941011d33f243cc8fa5ff728fa00f");


INSERT INTO brands (id, name)
VALUES (1, "Ford"),
(2, "Toyota");

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, "Fiesta", 'CAR', 1976, null, 1, "https://cdn.motor1.com/images/mgl/28rKM/s1/ford-fiesta-st-2022.jpg"),
    (2, "Escort", 'CAR', 1968, 2000, 1, "https://upload.wikimedia.org/wikipedia/commons/3/39/Ford_Escort_RS2000_MkI.jpg"),
    (3, "Yaris", 'CAR', 1999, null, 2, "https://i.ytimg.com/vi/Y-AylALKnEs/maxresdefault.jpg");

INSERT INTO user_roles (id, user_role)
VALUES (1, "ADMIN"),
       (2, "MODERATOR"),
       (3, "USER");

