package com.kotiswar.travel.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

//    @Autowired
//    private JwtTokenHelper jwtTokenHelper;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
//        this.authenticate(request.getUsername(), request.getPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
//        String token = this.jwtTokenHelper.generateToken(userDetails);
//
//        JwtAuthResponse response = new JwtAuthResponse();
//        response.setToken(token);
//        response.setUser(this.mapper.map((User) userDetails, UserDto.class));
//        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//
//        try {
//
//            this.authenticationManager.authenticate(authenticationToken);
//
//        } catch (BadCredentialsException e) {
//            System.out.println("Invalid Detials !!");
//            throw new ApiException("Invalid username or password !!");
//        }
//
//    }
//
//    // register new user api
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
//        UserDto registeredUser = this.userService.registerNewUser(userDto);
//        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
//    }
//
//    // get loggedin user data
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private ModelMapper mapper;
//
//    @GetMapping("/current-user/")
//    public ResponseEntity<UserDto> getUser(Principal principal) {
//        User user = this.userRepo.findByEmail(principal.getName()).get();
//        return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
//    }

}

