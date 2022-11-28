-- insert 3 pessoas

INSERT INTO
    pessoa (nome, cpf, data_nascimento)
VALUES (
        'Silvio Santos',
        '23568474125',
        '1941-08-30'
    );

INSERT INTO
    pessoa (nome, cpf, data_nascimento)
VALUES (
        'Fausto Silva',
        '77845514125',
        '1965-12-03'
    );

INSERT INTO
    pessoa (nome, cpf, data_nascimento)
VALUES (
        'Galvão Bueno',
        '07845131122',
        '1975-02-23'
    );

-- insert 3 contas

INSERT INTO
    conta (
        id_pessoa,
        saldo,
        flag_ativo,
        limite_saque_diario,
        tipo_conta,
        status
    )
VALUES (1, 550, 1, 100, 0, 'ATIVO');

INSERT INTO
    conta (
        id_pessoa,
        saldo,
        flag_ativo,
        limite_saque_diario,
        tipo_conta,
        status
    )
VALUES (2, 950, 1, 100, 0, 'ATIVO');

INSERT INTO
    conta (
        id_pessoa,
        saldo,
        flag_ativo,
        limite_saque_diario,
        tipo_conta,
        status
    )
VALUES (3, 250, 1, 100, 0, 'ATIVO');

-- insert 4 transações da conta 1

INSERT INTO
    transacao (
        id_conta,
        tipo_transacao,
        valor,
        data_transacao
    )
VALUES (
        1,
        'DEPOSITO',
        50,
        '2022-04-10T10:30:43'
    );

INSERT INTO
    transacao (
        id_conta,
        tipo_transacao,
        valor,
        data_transacao
    )
VALUES (
        1,
        'DEPOSITO',
        150,
        '2022-05-10T10:25:03'
    );

INSERT INTO
    transacao (
        id_conta,
        tipo_transacao,
        valor,
        data_transacao
    )
VALUES (
        1,
        'SAQUE',
        50,
        '2022-06-10T10:46:22'
    );

INSERT INTO
    transacao (
        id_conta,
        tipo_transacao,
        valor,
        data_transacao
    )
VALUES (
        1,
        'SAQUE',
        50,
        '2022-07-09T10:10:56'
    );