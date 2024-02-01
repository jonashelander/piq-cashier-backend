package com.example.PIQResponseMock.helpers;

import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.models.User;
import lombok.Data;

@Data
public class Converter {
    public static UserDTO convertUserToDTO(User user) {

        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getSessionId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getSex(),
                user.getCountry(),
                user.getCity(),
                user.getState(),
                user.getStreet(),
                user.getZip(),
                user.getPhone(),
                user.getEmail(),
                user.getBalance(),
                user.getBalanceCy(),
                user.isActivated(),
                user.getUserCat()
        );

        return userDTO;
    }
}
