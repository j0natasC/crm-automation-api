package com.jonatas.rbacapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErroResposta {
    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private Map<String, String> campos;
}
