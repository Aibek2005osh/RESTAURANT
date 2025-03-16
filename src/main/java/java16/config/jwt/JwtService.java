package java16.config.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java16.entitys.User;
import java16.exceptions.NotFoundException;
import java16.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${spring.security.jwt.secret_key}")
    private String SECRET_KEY;
    private final UserRepo userRepo;

    //generate token / create token
    public String generateToken(User user) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("email", user.getEmail());
        builder.withClaim("id", user.getId());
        builder.withClaim("role", user.getRole().name());
        builder.withIssuedAt(Instant.now());
        builder.withExpiresAt(Instant.now().plusSeconds(3700));
        return builder.sign(Algorithm.HMAC256(SECRET_KEY));
    }


    // verify token / validate token
    public User verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String email = decodedJWT.getClaim("email").asString();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found"));
    }

}