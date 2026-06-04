CREATE DATABASE IF NOT EXISTS bolera_plenazo;
USE bolera_plenazo;

# PAREJAS
CREATE TABLE parejas (
    id_pareja INT AUTO_INCREMENT,
    nombre_pareja VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id_pareja)
);

INSERT INTO parejas (id_pareja, nombre_pareja) VALUES
(1, 'Los Rompebolos'),
(2, 'Plenazo Team'),
(3, 'Strike Masters'),
(4, 'Los Turbobolos'),
(5, 'Los SuperBolos'),
(6, 'King Pins'),
(7, 'Split Happens'),
(8, 'Bolera Legend'),
(9, 'Gutter Balls'),
(10, 'Los demoledores');

# JUGADORES
CREATE TABLE jugadores (
    id_jugador INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(15),
    id_pareja INT,
    PRIMARY KEY (id_jugador),
    FOREIGN KEY (id_pareja) REFERENCES parejas(id_pareja) ON DELETE SET NULL
);

INSERT INTO jugadores (nombre, apellidos, correo, telefono, id_pareja) VALUES
('Pedro', 'García López', 'pedro@email.com', '600111222', 1),
('Carlos', 'Martínez Soler', 'carlos@email.com', '600333444', 1),
('Ana', 'Gómez Ruiz', 'ana@email.com', '600555666', 2),
('Sofía', 'Fernández Sanz', 'sofia@email.com', '600777888', 2),
('David', 'Jiménez Toro', 'david@email.com', '600999000', 3),
('Elena', 'Navarro Peña', 'elena@email.com', '600123456', 3),
('Juan', 'Rodríguez Ortiz', 'juan@email.com', '600654321', 4),
('Laura', 'Vázquez Gil', 'laura@email.com', '600987654', 4),
('Miguel', 'Sánchez Ortiz', 'miguel@email.com', '600222333', 5),
('Lucía', 'Garrido Cánovas', 'lucia@email.com', '600444555', 5),
('Antonio', 'Marín Solís', 'antonio@email.com', '611000111', 6),
('Manuel', 'Pérez Castro', 'manuel@email.com', '611222333', 6),
('Javier', 'Luna Torres', 'javier@email.com', '611444555', 7),
('Isabel', 'Guerra Roldán', 'isabel@email.com', '611666777', 7),
('Diego', 'Cano Benítez', 'diego@email.com', '622000111', 8),
('Marta', 'Vila Campos', 'marta@email.com', '622222333', 8),
('Alejandro', 'Blanco Cruz', 'alejandro@email.com', '622444555', 9),
('Cristina', 'Ibáñez Moya', 'cristina@email.com', '622666777', 9),
('Daniel', 'Herrero Bravo', 'daniel@email.com', '633000111', 10),
('Sonia', 'Pastor Vega', 'sonia@email.com', '633222333', 10);

# PISTAS
CREATE TABLE pistas (
    id_pista INT AUTO_INCREMENT,
    numero_pista INT NOT NULL UNIQUE,
    estado VARCHAR(20) DEFAULT 'Disponible',
    PRIMARY KEY (id_pista)
);

INSERT INTO pistas (id_pista, numero_pista, estado) VALUES
(1, 1, 'Disponible'),
(2, 2, 'Disponible'),
(3, 3, 'Disponible'),
(4, 4, 'Mantenimiento');

# PARTIDOS
CREATE TABLE partidos (
    id_partido INT AUTO_INCREMENT,
    id_pareja_local INT NOT NULL,
    id_pareja_visitante INT NOT NULL,
    jornada INT NOT NULL,
    PRIMARY KEY (id_partido),
    FOREIGN KEY (id_pareja_local) REFERENCES parejas(id_pareja),
    FOREIGN KEY (id_pareja_visitante) REFERENCES parejas(id_pareja)
);

INSERT INTO partidos (id_partido, id_pareja_local, id_pareja_visitante, jornada) VALUES
(1, 1, 2, 1), -- Rompebolos vs Plenazo Team
(2, 3, 4, 1), -- Strike Masters vs Turbobolos
(3, 5, 6, 1); -- SuperBolos vs King Pins

# RESERVAS PISTAS
CREATE TABLE reservas_pistas (
    id_reserva INT AUTO_INCREMENT,
    id_partido INT NOT NULL UNIQUE,
    id_pista INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    PRIMARY KEY (id_reserva),
    FOREIGN KEY (id_partido) REFERENCES partidos(id_partido),
    FOREIGN KEY (id_pista) REFERENCES pistas(id_pista),
    
    -- Evita que una misma pista se reserve dos veces en la misma fecha y hora
    CONSTRAINT uq_pista_horario UNIQUE (id_pista, fecha_hora)
);

INSERT INTO reservas_pistas (id_partido, id_pista, fecha_hora) VALUES
(1, 1, '2026-06-15 18:30:00'), -- Partido 1 en Pista 1
(2, 2, '2026-06-15 18:30:00'), -- Partido 2 en Pista 2 (Misma hora, pista diferente)
(3, 3, '2026-06-15 20:00:00'); -- Partido 3 en Pista 3 en otro horario

# RESULTADOS CLASIFICACION
CREATE TABLE resultados_clasificacion (
    id_resultado INT AUTO_INCREMENT,
    id_partido INT NOT NULL UNIQUE,
    bolos_locales INT DEFAULT 0,
    bolos_visitantes INT DEFAULT 0,
    puntos_pareja_local INT DEFAULT 0,
    puntos_pareja_visitante INT DEFAULT 0,
    PRIMARY KEY (id_resultado),
    FOREIGN KEY (id_partido) REFERENCES partidos(id_partido)
);

INSERT INTO resultados_clasificacion (id_partido, bolos_locales, bolos_visitantes, puntos_pareja_local, puntos_pareja_visitante) VALUES
(1, 460, 410, 2, 0), -- Gana local (2 puntos)
(2, 390, 390, 1, 1), -- Empate (1 punto para cada uno)
(3, 0, 0, 0, 0);     -- Partido no disputado todavía (Esperando en la app)

# USUARIOS
CREATE TABLE usuarios (
    id_usuario    INT AUTO_INCREMENT,
    id_jugador    INT NOT NULL UNIQUE,
    usuario       VARCHAR(50) NOT NULL UNIQUE,
    password_hash CHAR(64)    NOT NULL,          -- SHA-256 → 64 hex chars
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_jugador) REFERENCES jugadores(id_jugador) ON DELETE CASCADE
);

INSERT INTO usuarios (id_jugador, usuario, password_hash) VALUES
-- contraseña inicial: NombreApellido@2026  (ej. PedroGarcia@2026)
( 1, 'pedro',     SHA2('PedroGarcia@2026',     256)),
( 2, 'carlos',    SHA2('CarlosMartinez@2026',   256)),
( 3, 'ana',       SHA2('AnaGomez@2026',         256)),
( 4, 'sofia',     SHA2('SofiaFernandez@2026',   256)),
( 5, 'david',     SHA2('DavidJimenez@2026',     256)),
( 6, 'elena',     SHA2('ElenaNavarro@2026',     256)),
( 7, 'juan',      SHA2('JuanRodriguez@2026',    256)),
( 8, 'laura',     SHA2('LauraVazquez@2026',     256)),
( 9, 'miguel',    SHA2('MiguelSanchez@2026',    256)),
(10, 'lucia',     SHA2('LuciaGarrido@2026',     256)),
(11, 'antonio',   SHA2('AntonioMarin@2026',     256)),
(12, 'manuel',    SHA2('ManuelPerez@2026',      256)),
(13, 'javier',    SHA2('JavierLuna@2026',       256)),
(14, 'isabel',    SHA2('IsabelGuerra@2026',     256)),
(15, 'diego',     SHA2('DiegoCano@2026',        256)),
(16, 'marta',     SHA2('MartaVila@2026',        256)),
(17, 'alejandro', SHA2('AlejandroBlanco@2026',  256)),
(18, 'cristina',  SHA2('CristinaIbanez@2026',   256)),
(19, 'daniel',    SHA2('DanielHerrero@2026',    256)),
(20, 'sonia',     SHA2('SoniaPastor@2026',      256));