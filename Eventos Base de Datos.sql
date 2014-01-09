/*Habilitar los eventos*/
SET GLOBAL event_scheduler = ON;

-- PARA MOSTRAR LOS DÍAS Y MESES EN ESPAÑOL
SET lc_time_names = 'es_CO';

/*Ver los eventos*/
SHOW EVENTS;

-- EVENTOS EN SABGA

CREATE EVENT actualizarMultas
ON SCHEDULE EVERY 1 DAY STARTS '2014-01-09 00:00:00'
DO
CALL actualizarMulta();

CREATE EVENT cancelarReservas
ON SCHEDULE EVERY 1 DAY STARTS '2014-01-09 00:00:00'
DO
CALL cancelarReserva();


CREATE EVENT cambiarEstadoCorreos
ON SCHEDULE EVERY 1 DAY STARTS '2014-01-09 00:00:00'
DO
CALL setEstadoCorreos(1);