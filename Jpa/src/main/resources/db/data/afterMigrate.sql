set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from grupo;
delete from grupo_permissoes;
delete from pagamento;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table grupo auto_increment = 1;
alter table pagamento auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

INSERT INTO cozinha (id, nome) VALUES (NULL, "Norte-Americana");
INSERT INTO cozinha (id, nome) VALUES (NULL, "Manauara");

INSERT INTO estado (id, nome) VALUES (NULL, "Amazonas");
INSERT INTO estado (id, nome) VALUES (NULL, "Pará");

INSERT INTO grupo (id, nome) VALUES (NULL, "administrados");
INSERT INTO grupo (id, nome) VALUES (NULL, "vendedor");

INSERT INTO permissao (id, descricao) VALUES (NULL, "usuário ativo para qualquer coisa no sistema");
INSERT INTO permissao (id, descricao) VALUES (NULL, "usuário não ativo");

INSERT INTO grupo_permissoes (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupo_permissoes (grupo_id, permissao_id) VALUES (2, 1);
INSERT INTO grupo_permissoes (grupo_id, permissao_id) VALUES (2, 2);


INSERT INTO cidade (id, nome, estado_id) VALUES (NULL, "Manacapuru", 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (NULL, "Novo Airão", 2);

INSERT INTO restaurante (cidade_id, cozinha_id, nome, taxa_frete, endereco_cep, endereco_complemento, endereco_bairro, endereco_rua) VALUES (1, 2, "Bobs", 2.99, "69400025", "condominio", "Centro", "Av. Constantino Nery");
INSERT INTO restaurante (cidade_id, cozinha_id, nome, taxa_frete, endereco_cep, endereco_complemento, endereco_bairro, endereco_rua) VALUES (2 ,1, "Mcdonalds", 2, "69400425", "casa", "Cidade de Deus", "Av. Nossa Senhora da Conceição");

INSERT INTO pagamento (descricao) VALUES ("Crédito");
INSERT INTO pagamento (descricao) VALUES ("Débito");
INSERT INTO pagamento (descricao) VALUES ("Dinheiro");

INSERT INTO restaurante_forma_pagamento (restaurante_id, pagamento_id) VALUES (1, 1), (1, 2), (2, 1);