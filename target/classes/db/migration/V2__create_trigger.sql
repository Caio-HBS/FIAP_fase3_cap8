CREATE OR REPLACE TRIGGER TRG_GERA_ALERTA_LEITURA
AFTER INSERT ON T_LEITURA
FOR EACH ROW
DECLARE
    v_equipamento_id T_EQUIPAMENTO.equipamento_id%TYPE;
    v_consumo_medio  T_EQUIPAMENTO.consumo_medio_kw%TYPE;
    v_nivel_alerta   T_ALERTA.nivel%TYPE;
BEGIN
    -- Buscar o equipamento relacionado ao sensor
    SELECT equipamento_id INTO v_equipamento_id
    FROM T_SENSOR
    WHERE sensor_id = :NEW.sensor_id;

    -- Buscar o consumo médio do equipamento
    SELECT consumo_medio_kw INTO v_consumo_medio
    FROM T_EQUIPAMENTO
    WHERE equipamento_id = v_equipamento_id;

    -- Determinar o nível do alerta
    IF :NEW.consumo_kw < v_consumo_medio * 0.8 THEN
        v_nivel_alerta := 'BAIXO';
    ELSIF :NEW.consumo_kw <= v_consumo_medio * 1.2 THEN
        v_nivel_alerta := 'MEDIO';
    ELSE
        v_nivel_alerta := 'ALTO';
    END IF;

    -- Inserir alerta
    INSERT INTO T_ALERTA (
        alerta_id,
        equipamento_id,
        data_hora,
        nivel
    ) VALUES (
        SEQ_ALERTAS.NEXTVAL,
        v_equipamento_id,
        :NEW.data_hora,
        v_nivel_alerta
    );
END;
/
