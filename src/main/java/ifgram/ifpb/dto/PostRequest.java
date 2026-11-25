package ifgram.ifpb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static ifgram.ifpb.dto.CommentRequest.texto;

public record PostRequest() {
    @NotBlank(message = "A URL da imagem é obrigatória")
    static String imagemUrl;

    @Size(max = 300, message = "A legenda deve ter no máximo 300 caracteres")
    static String legenda;

    @NotNull(message = "O ID do autor é obrigatório")
    static Long autorId;

    public Long autorId() {
        return Long.valueOf(texto);
    }

    public String imagemUrl() {
        return null;
    }

    public String legenda() {
        return null;
    }
}