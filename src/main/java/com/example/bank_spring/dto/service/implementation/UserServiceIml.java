package com.example.bank_spring.dto.service.implementation;

import com.example.bank_spring.dto.service.AuthenticationRequestDto;
import com.example.bank_spring.dto.service.RegisterRequestDto;
import com.example.bank_spring.exception.UserNotFoundException;
import com.example.bank_spring.model.User;
import com.example.bank_spring.repository.UserRepository;
import com.example.bank_spring.dto.service.UserService;
import com.example.bank_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.bank_spring.model.Role.MANAGER;
import static com.example.bank_spring.model.Role.USER;

@Service
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceIml(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void register(RegisterRequestDto requestDto) {
        User user = new User();
        user.setRole(USER);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setBirthDate(requestDto.getBirthDate());
        user.setSex(requestDto.getSex());
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<Map> login(AuthenticationRequestDto requestDto) throws UserNotFoundException {

            String email = requestDto.getEmail();
            User user = userRepository.findByEmail(email).orElseThrow(()->
                    new UserNotFoundException("User with email: "+ email +" not found"));
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                    requestDto.getPassword()));
            String token = jwtTokenProvider.createToken(email,user.getRole());
            Map<Object,Object> response= new HashMap<>();
            response.put("email",email);
            response.put("token",token);
            return ResponseEntity.ok(response);

    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email: "+ email +" not found"));
    }


    //TODO: CUSTOM EXCEPTION
    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id: "+ id +" not found"));
    }

    @Override
    public void deleteById(Long id,String token) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user =userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with id: "+ id +" not found"));

        if(user.getRole().getAuthority().equals("MANAGER")
                ||user.getRole().getAuthority().equals("CHIEF_MANAGER")) {

            userRepository.deleteById(id);
        }
    }

    @Override
    public User updateById(Long id,String token,RegisterRequestDto requestDto) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user =userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with id: "+ id +" not found"));

        if(user.getRole().getAuthority().equals("MANAGER")||
                user.getRole().getAuthority().equals("CHIEF_MANAGER")) {

            userRepository.delete(user);
           user.setPassword(requestDto.getPassword());
           user.setName(requestDto.getName());
           user.setSex(requestDto.getSex());
           user.setEmail(requestDto.getEmail());
           user.setBirthDate(requestDto.getBirthDate());
           userRepository.save(user);
           return user;
        }
        return null;
    }

    @Override
    public User showUser(String token) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        return userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email: "+ email +" not found"));
    }

    @Override
    public User showUserById(Long id, String token) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user =userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email: "+ email +" not found"));

        if(user.getRole().getAuthority().equals("MANAGER")
                ||user.getRole().getAuthority().equals("CHIEF_MANAGER")) {

            return userRepository.findById(id).orElseThrow(()->
                    new UserNotFoundException("User with id: "+ id +" not found"));
        }
        return null;
    }

    @Override
    public ResponseEntity<?> showAllUsers(String token) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user =userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email: "+ email +" not found"));

        if(user.getRole().getAuthority().equals("MANAGER")||
                user.getRole().getAuthority().equals("CHIEF_MANAGER")) {

            return ResponseEntity.ok(userRepository.findAll());
        }
        return null;
    }

    @Override
    public void createManager(String token,RegisterRequestDto requestDto) throws UserNotFoundException {
        String email = jwtTokenProvider.getEmail(jwtTokenProvider.resolveToken(token));
        User user =userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with email: "+ email +" not found"));

        if(user.getRole().getAuthority().equals("CHIEF_MANAGER")){
            User manager = new User();
            manager.setRole(MANAGER);
            manager.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            manager.setName(requestDto.getName());
            manager.setEmail(requestDto.getEmail());
            manager.setBirthDate(requestDto.getBirthDate());
            manager.setSex(requestDto.getSex());
            userRepository.save(manager);
        }

    }
}
