/*Habilitar los eventos*/
SET GLOBAL event_scheduler = ON;

/*Ver los eventos*/
SHOW EVENTS;

-- EVENTOS EN SABGA

CREATE EVENT actualizarMultas
ON SCHEDULE EVERY 1 DAY
DO
CALL actualizarMulta();

CREATE EVENT cancelarReservas
ON SCHEDULE EVERY 1 DAY
DO
CALL cancelarReserva();