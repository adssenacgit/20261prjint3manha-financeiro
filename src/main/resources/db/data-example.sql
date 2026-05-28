INSERT INTO empresa (empresa_nome, empresa_cnpj) VALUES ('Empresa Exemplo', '00.000.000/0001-00');

INSERT INTO usuario (usuario_nome, usuario_email, usuario_senha, empresa_id)
VALUES ('Usuário Exemplo', 'usuario@exemplo.com', '123456', 1);

INSERT INTO categoria (categoria_nome, categoria_tipo, usuario_id)
VALUES ('Salário', 'entrada', 1), ('Aluguel', 'saida', 1);

INSERT INTO conta (conta_descricao, conta_valor, conta_tipo, conta_status, conta_data_vencimento, categoria_id, usuario_id)
VALUES ('Aluguel de maio', 1500.00, 'pagar', 'pendente', '2026-05-10', 2, 1);

INSERT INTO transacao (transacao_descricao, transacao_valor, transacao_data, categoria_id, usuario_id, conta_id)
VALUES ('Recebimento de salário', 5000.00, '2026-05-05', 1, 1, NULL);
