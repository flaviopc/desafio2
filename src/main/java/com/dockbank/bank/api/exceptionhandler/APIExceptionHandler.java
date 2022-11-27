package com.dockbank.bank.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dockbank.bank.domain.exception.ContaNaoEncontradaException;
import com.dockbank.bank.domain.exception.SaldoInsuficienteException;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MSG_ERRO_GENERICO = "Ocorreu um erro interno inesperado no sistema. "
            + "Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";
    private static final String CAMPOS_INVALIDOS = "Um ou mais campos estão inválidos. "
            + "Corrija e tente novamente.";

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<Object> handleContaNaoEncontradaException(ContaNaoEncontradaException e,
            WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        MessageException messageException = MessageException.builder()
                .mensagem(e.getMessage())
                .datahora(LocalDateTime.now())
                .status(status.value())
                .build();
        return handleExceptionInternal(e, messageException, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<MessageException.Problema> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = objectError.getDefaultMessage();

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError)
                        name = ((FieldError) objectError).getField();

                    return MessageException.Problema.builder()
                            .campo(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());
        MessageException messageException = MessageException.builder()
                .mensagem(CAMPOS_INVALIDOS)
                .problemas(problemObjects)
                .build();
        return handleExceptionInternal(ex, messageException, headers, status, request);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteException e,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        MessageException messageException = MessageException.builder()
                .mensagem(e.getMessage())
                .datahora(LocalDateTime.now())
                .status(status.value())
                .build();
        return handleExceptionInternal(e, messageException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_ERRO_GENERICO;

        MessageException messageException = MessageException.builder()
                .mensagem(detail)
                .datahora(LocalDateTime.now())
                .status(status.value())
                .build();

        return handleExceptionInternal(ex, messageException, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if (body == null)
            body = MessageException.builder()
                    .mensagem(status.getReasonPhrase())
                    .build();
        else if (body instanceof String)
            body = MessageException.builder()
                    .mensagem((String) body)
                    .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
