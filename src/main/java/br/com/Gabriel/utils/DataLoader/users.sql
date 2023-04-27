INSERT INTO users (id, ra, username, password, firstname, lastname, email) VALUES 
(1, '12345', 'LuKreitor', 'password123', 'Lucas', 'Martins', 'johndoe@example.com'),
(2, '23456', 'Laura', 'password456', 'Laura', 'Doe', 'janedoe@example.com'),
(3, '34567', 'Bia', 'password789', 'Bia', 'Smith', 'bobsmith@example.com'),
(4, '45678', 'Gabriel', 'passwordabc', 'Gabriel', 'Jones', 'sallyjones@example.com'),
(5, '56789', 'Sumida', 'passworddef', 'Sumida', 'Ross', 'mikeross@example.com');


UPDATE users SET deleted = false WHERE id = 2;