package br.com.Gabriel.auth;
/*
 * import lombok.RequiredArgsConstructor;
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.crypto.password.PasswordEncoder;
 * import org.springframework.stereotype.Service;
 * 
 * import com.br.photos.photosCP.Configuration.JwtService;
 * import com.br.photos.photosCP.DTO.Mappers.ERole;
 * import com.br.photos.photosCP.Model.User;
 * import com.br.photos.photosCP.Repository.UserRepository;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor
 * public class AuthenticationService {
 * private final UserRepository repository;
 * private final PasswordEncoder passwordEncoder;
 * private final JwtService jwtService;
 * private final AuthenticationManager authenticationManager;
 * 
 * public AuthenticationResponse register(RegisterRequest request) {
 * var user = User.builder()
 * .Firstname(request.getFirstname())
 * .Lastname(request.getLastname())
 * .email(request.getEmail())
 * .password(passwordEncoder.encode(request.getPassword()))
 * .role(ERole.USER)
 * .build();
 * repository.save(user);
 * var jwtToken = jwtService.generateToken(user);
 * return AuthenticationResponse.builder()
 * .token(jwtToken)
 * .build();
 * }
 * 
 * public AuthenticationResponse authenticate(AuthenticationRequest request) {
 * authenticationManager.authenticate(
 * new UsernamePasswordAuthenticationToken(
 * request.getEmail(),
 * request.getPassword()));
 * var user = repository.findByEmailIgnoreCase(request.getEmail());
 * var jwtToken = jwtService.generateToken(user);
 * return AuthenticationResponse.builder()
 * .token(jwtToken)
 * .build();
 * }
 * }
 * 
 */