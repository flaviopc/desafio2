package com.dockbank.bank.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class MessageException {
    private Integer status;
    private LocalDateTime datahora;
    private String mensagem;
    private List<Problema> problemas;

    @Getter
    @Builder
    public static class Problema {
        private String campo;
        private String mensagem;
    }
}
