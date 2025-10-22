package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.model.Authorize;
import com.example.PIQResponseMock.model.Cancel;
import com.example.PIQResponseMock.model.Transfer;
import com.example.PIQResponseMock.repositories.AuthorizeRepository;
import com.example.PIQResponseMock.repositories.CancelRepository;
import com.example.PIQResponseMock.repositories.TransferRepository;
import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignInResponseDTO;
import com.example.PIQResponseMock.dto.SignUpResponseDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.helpers.Convert;
import com.example.PIQResponseMock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    UserRepository userRepository;
    AuthorizeRepository authorizeRepository;
    TransferRepository transferRepository;
    CancelRepository cancelRepository;

    @Autowired
    public AuthService(UserRepository userRepository, AuthorizeRepository authorizeRepository, TransferRepository transferRepository, CancelRepository cancelRepository) {
        this.userRepository = userRepository;
        this.authorizeRepository = authorizeRepository;
        this.transferRepository = transferRepository;
        this.cancelRepository = cancelRepository;
    }

    public UserDTO signUp(SignUpResponseDTO signUpResponseDTO) {
        //This will be the verifyuser response
        User user = new User(signUpResponseDTO.getUserId(), signUpResponseDTO.getFirstName(), signUpResponseDTO.getLastName(), signUpResponseDTO.getDob(), signUpResponseDTO.getSex(), signUpResponseDTO.getCountry(), signUpResponseDTO.getCity(), signUpResponseDTO.getState(), signUpResponseDTO.getStreet(), signUpResponseDTO.getZip(), signUpResponseDTO.getMobile(), signUpResponseDTO.getEmail(), signUpResponseDTO.getPassword(), true, "New member", "errCode", "errMsg", "en_GB", "authCode");

        //Make sure the userId is unique
        //To be added

        //Create responses(authorize, transfer, cancel)
        Authorize authorize = new Authorize(signUpResponseDTO.getUserId(), true, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "001", "Error message");
        authorize.setUser(user);
        user.setAuthorize(authorize);

        Transfer transfer = new Transfer(user.getId(), "UserId", true, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "001", "errMsg");
        transfer.setUser(user);
        user.setTransfer(transfer);

        Cancel cancel = new Cancel(user.getId(), "UserId", true, "001", "errMsg");
        cancel.setUser(user);
        user.setCancel(cancel);

        userRepository.save(user);
        return Convert.convertUserToDTO(user);
    }

    public ResponseEntity<UserDTO> signIn(SignInResponseDTO signInResponseDTO) {
        User user = userRepository.findUserByEmail(signInResponseDTO.getEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!user.getPassword().equals(signInResponseDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);
        UserDTO userDTO = Convert.convertUserToDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    public void signOut(String userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setSessionId(null);
            userRepository.save(user);
        });
    }

    public ResponseEntity<?> authUser(AuthDTO authDTO) {
        return userRepository.findById(authDTO.getId()).map(user -> {
            if (user.getToken() == null || !user.getToken().equals(authDTO.getToken())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            UserDTO userDTO = Convert.convertUserToDTO(user);
            return ResponseEntity.ok(userDTO);
        }).orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    public double checkBalance(String userId, String txAmount) {
        return userRepository.findById(userId).map(user -> {
            double convertedAmount = convertTxAmount(txAmount);
            return user.getBalance() + convertedAmount;
        }).orElse(0.00);
    }

    public double convertTxAmount(String txAmount) {
        double convertedAmount;
        try {
            convertedAmount = Double.parseDouble(txAmount);
        } catch (NumberFormatException e) {
            convertedAmount = 0.00;
        }
        return convertedAmount;
    }

    public boolean checkIfActivated(String userId) {
        return userRepository.findById(userId).map(User::isActivated).orElse(false);
    }

    public ResponseEntity<Boolean> blockUser(String userId) {
        return userRepository.findById(userId).map(user -> {
            user.setActivated(false);
            userRepository.save(user);
            return ResponseEntity.ok(user.isActivated());
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Boolean> unBlockUser(String userId) {
        return userRepository.findById(userId).map(user -> {
            user.setActivated(true);
            userRepository.save(user);
            return ResponseEntity.ok(user.isActivated());
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<UserDTO> updateUser(String id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            //User/verifyUser
            user.setUserId(userDTO.getUserId());
            user.setSessionId(userDTO.getSessionId());
            user.setSuccess(userDTO.isSuccess());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setDob(userDTO.getDob());
            user.setSex(userDTO.getSex());
            user.setCountry(userDTO.getCountry());
            user.setCity(userDTO.getCity());
            user.setState(userDTO.getState());
            user.setStreet(userDTO.getStreet());
            user.setZip(userDTO.getZip());
            user.setMobile(userDTO.getMobile());
            user.setEmail(userDTO.getEmail());
            user.setBalance(userDTO.getBalance());
            user.setBalanceCy(userDTO.getBalanceCy());
            user.setActivated(userDTO.isActivated());
            user.setKycStatus(userDTO.getKycStatus());
            user.setErrCode(userDTO.getErrCode());
            user.setErrMsg(userDTO.getErrMsg());
            user.setLocale(userDTO.getLocale());

            //Authorize
            user.getAuthorize().setUserId(userDTO.getAuthorize().getUserId());
            user.getAuthorize().setSuccess(userDTO.getAuthorize().isSuccess());
            user.getAuthorize().setMerchantTxId(userDTO.getAuthorize().getMerchantTxId());
            user.getAuthorize().setAuthCode(userDTO.getAuthorize().getAuthCode());
            user.getAuthorize().setErrCode(userDTO.getAuthorize().getErrCode());
            user.getAuthorize().setErrMsg(userDTO.getAuthorize().getErrMsg());

            //Transfer
            user.getTransfer().setUserId(userDTO.getTransfer().getUserId());
            user.getTransfer().setSuccess(userDTO.getTransfer().isSuccess());
            user.getTransfer().setTxId(userDTO.getTransfer().getTxId());
            user.getTransfer().setMerchantTxId(userDTO.getTransfer().getMerchantTxId());
            user.getTransfer().setErrCode(userDTO.getTransfer().getErrCode());
            user.getTransfer().setErrMsg(userDTO.getTransfer().getErrMsg());

            //Cancel
            user.getCancel().setUserId(userDTO.getCancel().getUserId());
            user.getCancel().setSuccess(userDTO.getCancel().isSuccess());
            user.getCancel().setErrCode(userDTO.getCancel().getErrCode());
            user.getCancel().setErrMsg(userDTO.getCancel().getErrMsg());

            userRepository.save(user);
            return ResponseEntity.ok(Convert.convertUserToDTO(user));
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserDTO> getUserById(String id) {
        return userRepository.findById(id).map(user -> ResponseEntity.ok(Convert.convertUserToDTO(user))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
