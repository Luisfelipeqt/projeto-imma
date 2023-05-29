package br.com.projectcedro.backend.projectcedro.api.common.routes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiRoutes {

    public static final String API = "/api";
    public static final String PACIENTES = "/pacientes";
    public static final String CONSULTAS = "/consultas";
    public static final String MEDICOS = "/medicos";

    public static final String ID = "/{id}";

    public static final String VERSAO = "/v1";

    public static final String AUTH = "/auth";
    public static final String AUTH_LOGIN = API + AUTH + "/login";
    public static final String AUTH_LOGOUT = API + AUTH + "/logout";
    public static final String AUTH_REFRESH = API + AUTH + "/refresh";
    public static final String AUTH_DADOS_USER = API + AUTH + "/me";





    public static final String BUSCAR_PACIENTES = API + PACIENTES + VERSAO;
    public static final String BUSCAR_PACIENTES_ID = API + PACIENTES + VERSAO + ID;
    public static final String CADASTRAR_PACIENTES = BUSCAR_PACIENTES;
    public static final String ATUALIZAR_PACIENTES = BUSCAR_PACIENTES_ID;
    public static final String DELETAR_PACIENTES = BUSCAR_PACIENTES_ID;


    public static final String BUSCAR_MEDICOS = API + MEDICOS + VERSAO;
    public static final String BUSCAR_MEDICOS_ID = API + MEDICOS + VERSAO + ID;
    public static final String CADASTRAR_MEDICOS = BUSCAR_MEDICOS;
    public static final String ATUALIZAR_MEDICOS = BUSCAR_MEDICOS_ID;
    public static final String DELETAR_MEDICOS = BUSCAR_MEDICOS_ID;




    public static final String BUSCAR_CONSULTAS = API + CONSULTAS + VERSAO;
    public static final String BUSCAR_CONSULTAS_ID = API + CONSULTAS + VERSAO + ID;
    public static final String CADASTRAR_CONSULTAS = BUSCAR_CONSULTAS;
    public static final String ATUALIZAR_CONSULTAS = BUSCAR_CONSULTAS_ID;
    public static final String DELETAR_CONSULTAS = BUSCAR_CONSULTAS_ID;



}
