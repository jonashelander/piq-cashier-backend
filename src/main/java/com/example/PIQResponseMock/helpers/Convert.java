package com.example.PIQResponseMock.helpers;

import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Convert {
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
                twoDecimals(user.getBalance()),
                user.getBalanceCy(),
                user.isActivated(),
                user.getUserCat()
        );

        return userDTO;
    }

    public static double twoDecimals(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
