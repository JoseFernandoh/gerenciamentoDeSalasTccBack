package br.com.gerenciamentodesalastcc.controller;


import br.com.gerenciamentodesalastcc.configuracao.TokenService;
import br.com.gerenciamentodesalastcc.dto.Login;
import br.com.gerenciamentodesalastcc.modelo.Usuario;
import br.com.gerenciamentodesalastcc.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("segov")
@CrossOrigin(origins = "*")
public class UsuarioControler {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioControler(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(login.username(),
                            login.password());

            Authentication authenticate = this.authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);

            var usuario =  (Usuario) authenticate.getPrincipal();

            return new ResponseEntity<>(tokenService.gerarToken(usuario),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
