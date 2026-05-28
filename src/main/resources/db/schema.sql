CREATE TABLE IF NOT EXISTS `empresa` (
  `empresa_id` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_nome` varchar(200) NOT NULL,
  `empresa_cnpj` varchar(20) NOT NULL,
  `empresa_status` tinyint(1) DEFAULT '1',
  `empresa_criada_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`empresa_id`),
  UNIQUE KEY `empresa_cnpj` (`empresa_cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_nome` varchar(200) NOT NULL,
  `usuario_email` varchar(200) NOT NULL,
  `usuario_senha` varchar(255) NOT NULL,
  `usuario_status` tinyint(1) DEFAULT '1',
  `usuario_foto` varchar(255) DEFAULT NULL,
  `usuario_data_criacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `empresa_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `usuario_email` (`usuario_email`),
  KEY `empresa_id` (`empresa_id`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`empresa_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `categoria` (
  `categoria_id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria_nome` varchar(200) NOT NULL,
  `categoria_tipo` enum('entrada','saida') NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`categoria_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `categoria_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `conta` (
  `conta_id` int(11) NOT NULL AUTO_INCREMENT,
  `conta_descricao` varchar(255) NOT NULL,
  `conta_valor` decimal(12,2) NOT NULL,
  `conta_valor_pago` decimal(12,2) DEFAULT NULL,
  `conta_tipo` enum('pagar','receber') NOT NULL,
  `conta_status` enum('pendente','efetivado') DEFAULT 'pendente',
  `conta_data_vencimento` date NOT NULL,
  `conta_data_pagamento` date DEFAULT NULL,
  `conta_criada_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoria_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`conta_id`),
  KEY `categoria_id` (`categoria_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `conta_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`categoria_id`) ON DELETE SET NULL,
  CONSTRAINT `conta_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `transacao` (
  `transacao_id` int(11) NOT NULL AUTO_INCREMENT,
  `transacao_descricao` varchar(300) DEFAULT NULL,
  `transacao_valor` decimal(12,2) NOT NULL,
  `transacao_data` date NOT NULL,
  `transacao_criada_em` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoria_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  `conta_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`transacao_id`),
  KEY `categoria_id` (`categoria_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `conta_id` (`conta_id`),
  CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`categoria_id`) ON DELETE SET NULL,
  CONSTRAINT `transacao_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE,
  CONSTRAINT `transacao_ibfk_3` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`conta_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
