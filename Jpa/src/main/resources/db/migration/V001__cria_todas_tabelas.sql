create table cidade (id bigint not null auto_increment, nome varchar(255), estado_id bigint, primary key (id)) engine=InnoDB;
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB;
create table estado (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB;
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB;
create table grupo_permissoes (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB;
create table pagamento (id bigint not null auto_increment, descricao varchar(255), primary key (id)) engine=InnoDB;
create table permissao (id bigint not null auto_increment, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
create table produto (id bigint not null auto_increment, ativo bit, nome varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB;
create table restaurante (id bigint not null auto_increment, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_rua varchar(255), nome varchar(255), taxa_frete decimal(19,2), cidade_id bigint, cozinha_id bigint, primary key (id)) engine=InnoDB;
create table restaurante_forma_pagamento (restaurante_id bigint not null, pagamento_id bigint not null) engine=InnoDB;
create table usuario (id bigint not null auto_increment, data_cadastro datetime, email varchar(255), nome varchar(255), senha varchar(255), primary key (id)) engine=InnoDB;
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB;
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id);
alter table grupo_permissoes add constraint FKbjj8fbcfxr7joapufexdn7fv0 foreign key (permissao_id) references permissao (id);
alter table grupo_permissoes add constraint FKd7wt9tnvrfttdcl5ofoelgi6j foreign key (grupo_id) references grupo (id);
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint FKnbrdf9c9165xgwuynayr85m0h foreign key (cidade_id) references cidade (id);
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id);
alter table restaurante_forma_pagamento add constraint FKdxvkq3o2vjmq6l40ret7ytr02 foreign key (pagamento_id) references pagamento (id);
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id);