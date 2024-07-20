package com.fiap.parquimetro.infra.exception;

import jakarta.el.MethodNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@AllArgsConstructor
@RestControllerAdvice
public class ParquimetroExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity tratarErro404(EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("recurso.nao-encontrado"))));
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity tratarErro404(NoResourceFoundException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("recurso.nao-encontrado"))));
    }

    @ExceptionHandler({MethodNotFoundException.class,})
    public ResponseEntity tratarErro404(MethodNotFoundException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("recurso.nao-encontrado"))));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(erro ->
                new DadosErroValidacao(erro, getMessage("methodArgumentNotValidMessage"))).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("dataIntegrityViolationMessage"))));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupportedExceptionException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("httpRequestMethodNotSupportedException"))));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("constraintViolationMessage"))));
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return ResponseEntity.badRequest().body((new DadosErro(
                ex, getMessage("constraintViolationMessage"))));
    }

    private record DadosErroValidacao(String campo, String mensagemUsuario, String mensagemDesenvolvedor) {
        public DadosErroValidacao(FieldError erro, String mensagemUsuario) {
            this(erro.getField(), mensagemUsuario, erro.toString());
        }
    }

    private record DadosErro(String mensagemUsuario, String mensagemDesenvolvedor) {
        public DadosErro(Exception erro, String mensagemUsuario) {
            //ExceptionUtils - pega a exception para exibir mensagem
            this(mensagemUsuario, ExceptionUtils.getRootCauseMessage(erro));
        }

    }

    private String getMessage(String mensagem) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource.getMessage(mensagem, null, LocaleContextHolder.getLocale());
    }
}
