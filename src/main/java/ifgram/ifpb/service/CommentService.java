package ifgram.ifpb.service;

import ifgram.ifpb.dto.CommentRequest;
import ifgram.ifpb.dto.CommentResponse;
import ifgram.ifpb.model.Comment;
import ifgram.ifpb.model.Post;
import ifgram.ifpb.model.User;
import ifgram.ifpb.repository.CommentRepository;
import ifgram.ifpb.repository.PostRepository;
import ifgram.ifpb.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentResponse criar(CommentRequest request) {

        if (request.texto() == null || request.texto().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O comentário não pode estar vazio"
            );
        }

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Publicação não encontrada"
                ));

        User autor = userRepository.findById(request.autorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        Comment comment = new Comment(
                request.texto(),
                post,
                autor
        );

        return CommentResponse.from(
                commentRepository.save(comment)
        );
    }

    public List<CommentResponse> listarPorPost(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Publicação não encontrada"
            );
        }

        return commentRepository
                .findByPostIdOrderByCriadoEmAsc(postId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    @Transactional
    public void deletar(Long id) {

        if (!commentRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comentário não encontrado"
            );
        }

        commentRepository.deleteById(id);
    }
}
