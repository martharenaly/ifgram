package ifgram.ifpb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequest() {
    @NotBlank(message = "O texto do comentário é obrigatório")
    @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres")
    public static String texto;

    @NotNull(message = "O ID do post é obrigatório")
    static Long postId;

    @NotNull(message = "O ID do autor é obrigatório")
    static Long autorId;

    public String texto() {
        return texto ;
    }

    public Long postId() {
        return null;
    }

    public Long autorId() {
        return null;
    }
}
