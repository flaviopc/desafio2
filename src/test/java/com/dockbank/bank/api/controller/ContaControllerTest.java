package com.dockbank.bank.api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaControllerTest {
    @LocalServerPort
    private int porta;

    @BeforeEach
    public void setUp() {
        RestAssured.port = porta;
        RestAssured.basePath = "/contas";
    }

    @Test
    public void deveRetornar201QuandoCadastrarUmaConta() {
        RestAssured.given()
                .body("{\"pessoa\":{\"id\":1},\"saldo\":350}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornarSaldo550QuandoConsultarOSaldo() {
        RestAssured.given()
                .pathParam("idConta", 1)
                .accept(ContentType.JSON)
                .when()
                .get("/{idConta}/saldo")
                .then()
                .statusCode(200)
                .body("saldo", Matchers.equalTo(550.0F));
    }

    @Test
    public void deveRetornar404QuandoConsultarSaldoDeContaInexistente() {
        RestAssured.given()
                .pathParam("idConta", 15)
                .accept(ContentType.JSON)
                .when()
                .get("/{idConta}/saldo")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveRetornar400EExibirMensagemQuandoOValorDoDepositoForMenorQue1() {
        RestAssured.given()
                .pathParam("idConta", 1)
                .body("{\"valor\":0.2}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{idConta}/deposito")
                .then()
                .body("mensagem", Matchers.equalTo("Um ou mais campos estão inválidos. Corrija e tente novamente."))
                .statusCode(400);
    }

    @Test
    public void deveRetornar400EExibirMensagemQuandoOValorDeSaqueForMenorQue1() {
        RestAssured.given()
                .pathParam("idConta", 1)
                .body("{\"valor\":0.7}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{idConta}/saque")
                .then()
                .body("mensagem", Matchers.equalTo("Um ou mais campos estão inválidos. Corrija e tente novamente."))

                .statusCode(400);
    }

    @Test
    public void deveRetornar4Transacoes() {
        RestAssured.given()
                .pathParam("idConta", 1)
                .accept(ContentType.JSON)
                .when()
                .get("/{idConta}/extrato")
                .then()
                .body("transacoes", Matchers.hasSize(4));
    }

    @Test
    public void deveRetornar2TransacoesNoPeriodo() {
        RestAssured.given()
                .pathParam("idConta", 1)
                .queryParam("dataInicial", "2022-04-01")
                .queryParam("dataFinal", "2022-06-01")
                .accept(ContentType.JSON)
                .when()
                .get("/{idConta}/extrato-por-periodo")
                .then()
                .body("transacoes", Matchers.hasSize(2));
    }
}
