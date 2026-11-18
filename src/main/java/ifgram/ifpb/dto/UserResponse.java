package ifgram.ifpb.dto;

import ifgram.ifpb.model.User;

    public record UserResponse(Long id, String nome, String email) {
<<<<<<< HEAD
        public static UserResponse from(User){
        User user;
        return new UserResponse(user.getId(), user.getNome(), user.getEmail());
=======
        public static UserResponse from(User user){
            return new UserResponse(user.getId(), user.getNome(), user.getEmail());
>>>>>>> 1f5fd9f4ba3dc79fd0f453f5cbf5487cefc4eaa3
        }
    }