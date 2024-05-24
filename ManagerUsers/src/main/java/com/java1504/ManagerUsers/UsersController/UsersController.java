package com.java1504.ManagerUsers.UsersController;


import com.java1504.ManagerUsers.UserServices.UserServices;
import com.java1504.ManagerUsers.InfoBase.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/add")
    public ResponseEntity<?> addUsers(@Valid @RequestBody Users users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDetails> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ErrorDetails(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        try {
            Users addedUser = userServices.addUsers(users);
            return new ResponseEntity<>(addedUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public List<Users> getAll(){
        return userServices.getALL();
    }

    @DeleteMapping("/{id}")
        public boolean deleteUser( @PathVariable int id){
            return userServices.deleteUser(id);
    }

    @PutMapping("/edit/{id}")
    public Users editInfo(@PathVariable int id,@RequestBody Users users){
        return userServices.editInfo(id,users);
    }

}
