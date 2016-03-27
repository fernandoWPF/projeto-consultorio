create table tbAgendamento (
    id serial not null,
    data_agendamento timestamp,
    hora_agenda varchar(5),
    observacao varchar(255),
    id_dentista bigint,
    id_paciente bigint,
    id_procedimento bigint
);
 create table tbCidade (
    id  serial not null,
    nome varchar(70) not null,
    uf varchar(2) not null
);
 create table tbConsultorio (
    id  serial not null,
    bairro varchar(100),
    cep varchar(8),
    id_cidade bigint,
    complemento varchar(50),
    data_abertura timestamp,
    endereco varchar(200) not null,
    hora_fim_almoco varchar(5),
    hora_fim_atendimento varchar(5),
    hora_fim_sabado varchar(5),
    hora_inicio_almoco varchar(5),
    hora_inicio_atendimento varchar(5),
    hora_inicio_sabado varchar(5),
    nome_fantasia varchar(150),
    numero bigint,
    razao_social varchar(150),
    telefone varchar(255),
    id_proprietario bigint
);
 create table tbDentista (
    id  serial not null,
    ano_formatura date,
    cro varchar(20) not null,
    especialidade_principal varchar(60),
    especialidade_secundaria varchar(60),
    instituicao varchar(255),
    uf_cro varchar(255),
    id_pessoa bigint
);
 create table tbPaciente (
    id  serial not null,
    data_cadastro date,
    data_retorno_prevencao date,
    escolaridade varchar(25),
    local_trabalho varchar(255),
    nome_mae varchar(255),
    nome_pai varchar(255),
    profissao varchar(255),
    renda_mensal numeric(19, 2),
    status_paciente varchar(15),
    id_pessoa bigint
);
 create table tbPessoa (
    id  serial not null,
    bairro varchar(100),
    celular varchar(19),
    cep varchar(9),
    id_cidade bigint,
    complemento varchar(50),
    cpf varchar(14),
    data_nascimento date not null,
    email varchar(50),
    endereco varchar(200),
    estado_civil varchar(15),
    idade int4 not null,
    nome varchar(255) not null,
    numero bigint,
    observacao varchar(255),
    rg varchar(12),
    sexo varchar(1) not null,
    telefone varchar(19)
);
  create table tbProcedimento (
    id  serial not null,
    descricao varchar(100),
    duracao_aprox time,
    marcador varchar(30),
    valor_procedimento numeric(19, 2)
);
  create table tbPromissoria (
    id  serial not null,
    data_emissao timestamp not null,
    data_vencto timestamp,
    num_parcela int,
    num_promissoria bigint,
    observacao varchar(255),
    qtde_pacelas int not null,
    status_promissoria varchar(15),
    valor_parcela numeric(10, 2),
    valor_saldo numeric(10, 2),
    valor_saldo_parcela numeric(10, 2),
    valor_total numeric(10, 2) not null,
    id_paciente bigint
);
 create table tbRecebimento (
    id  serial not null,
    data_lancto timestamp,
    valor_lancto numeric(10, 2),
    id_promissoria bigint
);
  create table tbUsuario (
    id  serial not null,
    email varchar(255),
    login varchar(50) not null,
    senha varchar(6) not null
);
alter table tbCidade
	add constraint PK_CIDADE primary key(id),
	add constraint UNIQUE_CIDADE unique(nome,uf);

alter table tbPessoa
	add constraint PK_PESSOA primary key(id),
	add constraint FK_CIDADE foreign key(id_cidade) references tbCidade,
	add constraint UNIQUE_CPF unique (cpf),
	add constraint UNIQUE_RG unique (rg);
	
alter table tbPaciente
	add constraint PK_PACIENTE primary key(id),
	add constraint FK_PESSOA foreign key (id_pessoa) references tbPessoa;

	
alter table tbDentista
	add constraint PK_DENTISTA primary key(id),
	add constraint FK_PESSOA foreign key (id_pessoa) references tbPessoa;

alter table tbProcedimento
	add constraint PK_PROCEDIMENTO primary key(id);
	
alter table tbAgendamento 
	add constraint PK_AGENDAMENTO primary key(id),
	add constraint FK_DENTISTA foreign key (id_dentista) references tbDentista,
	add constraint FK_PACIENTE foreign key (id_paciente) references tbPaciente,
	add constraint FK_PROCEDIMENTO foreign key (id_procedimento) references tbProcedimento;


alter table tbConsultorio
	add constraint PK_CONSULTORIO primary key(id),
	add constraint FK_PROPRIETARIO foreign key (id_proprietario) references tbDentista;
	

alter table tbPromissoria
	add constraint PK_PROMISSORIA primary key(id),
	add constraint FK_PACIENTE  foreign key (id_paciente) references tbPaciente;
	
 alter table tbRecebimento 
	add constraint PK_RECEBIMENTO primary key(id),
	add constraint FK_PROMISSORIA foreign key (id_promissoria) references tbPromissoria;
