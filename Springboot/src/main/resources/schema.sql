
-- 1. USUARIO SESION (Login)
CREATE TABLE IF NOT EXISTS usuario_sesion (
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              email VARCHAR(150) UNIQUE NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
    );

-- 2. USUARIO PERFIL (Datos app)
CREATE TABLE IF NOT EXISTS usuario_perfil (
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              id_auth INT UNIQUE NOT NULL,
                                              descripcion VARCHAR(255),
    foto_perfil VARCHAR(255),
    estilo ENUM('CASUAL','FORMAL','DEPORTIVO','URBANO','ELEGANTE'),
    CONSTRAINT fk_usuario_perfil_auth
    FOREIGN KEY (id_auth) REFERENCES usuario_sesion(id)
    ON DELETE CASCADE
    );

-- 3. ROPA (He aplicado aquí el cambio de tamaño de foto a 1000)
CREATE TABLE IF NOT EXISTS ropa (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    id_usuario INT NOT NULL,
                                    nombre_prenda VARCHAR(150) NOT NULL,
    foto VARCHAR(1000),
    talla VARCHAR(50),
    estilo VARCHAR(50),
    estado VARCHAR(30) DEFAULT 'disponible',
    CONSTRAINT fk_ropa_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario_perfil(id)
    ON DELETE CASCADE
    );

-- 4. INTERCAMBIO
CREATE TABLE IF NOT EXISTS intercambio (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           id_usuario_ofertante INT NOT NULL,
                                           id_usuario_solicitante INT NOT NULL,
                                           id_ropa INT NOT NULL,
                                           estado VARCHAR(30) DEFAULT 'solicitado',
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    fecha_acuerdo TIMESTAMP,
    FOREIGN KEY (id_usuario_ofertante) REFERENCES usuario_perfil(id),
    FOREIGN KEY (id_usuario_solicitante) REFERENCES usuario_perfil(id),
    FOREIGN KEY (id_ropa) REFERENCES ropa(id)
    );

-- 5. VALORACION
CREATE TABLE IF NOT EXISTS valoracion (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          id_intercambio INT NOT NULL,
                                          id_usuario INT NOT NULL,
                                          puntuacion INT NOT NULL,
                                          comentario VARCHAR(255),
    FOREIGN KEY (id_intercambio) REFERENCES intercambio(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario_perfil(id) ON DELETE CASCADE
    );

-- 6. PRESTAMO
CREATE TABLE IF NOT EXISTS prestamo (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        id_usuario_prestador INT NOT NULL,
                                        id_usuario_receptor INT NOT NULL,
                                        id_ropa INT NOT NULL,
                                        fecha_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    fecha_final_acordada DATE,
    fecha_devolucion_real DATE,
    importe DECIMAL(10,2),
    estado VARCHAR(30) DEFAULT 'solicitado',
    FOREIGN KEY (id_usuario_prestador) REFERENCES usuario_perfil(id),
    FOREIGN KEY (id_usuario_receptor) REFERENCES usuario_perfil(id),
    FOREIGN KEY (id_ropa) REFERENCES ropa(id)
    );

-- 7. CHAT
CREATE TABLE IF NOT EXISTS chat (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    tema VARCHAR(255),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
    );

-- 8. CHAT PARTICIPANTES
CREATE TABLE IF NOT EXISTS chat_participantes (
                                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                                  id_chat INT NOT NULL,
                                                  id_usuario INT NOT NULL,
                                                  rol VARCHAR(50),
    FOREIGN KEY (id_chat) REFERENCES chat(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario_perfil(id) ON DELETE CASCADE
    );

-- 9. MENSAJE
CREATE TABLE IF NOT EXISTS mensaje (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       id_chat INT NOT NULL,
                                       id_emisor INT NOT NULL,
                                       contenido TEXT NOT NULL,
                                       enviado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    leido BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_chat) REFERENCES chat(id) ON DELETE CASCADE,
    FOREIGN KEY (id_emisor) REFERENCES usuario_perfil(id)
    );

-- 10. NOTIFICACION
CREATE TABLE IF NOT EXISTS notificacion (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            id_usuario_destino INT NOT NULL,
                                            tipo VARCHAR(100),
    mensaje TEXT,
    leido BOOLEAN DEFAULT FALSE,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (id_usuario_destino) REFERENCES usuario_perfil(id) ON DELETE CASCADE
    );