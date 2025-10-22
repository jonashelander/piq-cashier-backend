package com.example.PIQResponseMock.helpers;

import com.example.PIQResponseMock.dto.AuthorizeDTO;
import com.example.PIQResponseMock.dto.CancelDTO;
import com.example.PIQResponseMock.dto.TransferDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Convert {
    public static UserDTO convertUserToDTO(User user) {

        AuthorizeDTO authorizeDTO = null;
        if (user.getAuthorize() != null) {
            authorizeDTO = new AuthorizeDTO(
                    user.getAuthorize().getUserId(),
                    user.getAuthorize().isSuccess(),
                    user.getAuthorize().getMerchantTxId(),
                    user.getAuthorize().getAuthCode(),
                    user.getAuthorize().getErrCode(),
                    user.getAuthorize().getErrMsg()
            );
        }

        TransferDTO transferDTO = null;
        if (user.getTransfer() != null) {
            transferDTO = new TransferDTO(
                    user.getTransfer().getUserId(),
                    user.getTransfer().isSuccess(),
                    user.getTransfer().getTxId(),
                    user.getTransfer().getMerchantTxId(),
                    user.getTransfer().getErrCode(),
                    user.getTransfer().getErrMsg()
            );
        }

        CancelDTO cancelDTO = null;
        if (user.getCancel() != null) {
            cancelDTO = new CancelDTO(
                    user.getCancel().getUserId(),
                    user.getCancel().isSuccess(),
                    user.getCancel().getErrCode(),
                    user.getCancel().getErrMsg()
            );
        }

        return new UserDTO(
                user.getId(),
                user.getUserId(),
                user.getSessionId(),
                user.getToken(),
                user.isSuccess(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getSex(),
                user.getCountry(),
                user.getCity(),
                user.getState(),
                user.getStreet(),
                user.getZip(),
                user.getMobile(),
                user.getEmail(),
                twoDecimals(user.getBalance()),
                user.getBalanceCy(),
                user.isActivated(),
                user.getUserCat(),
                user.getKycStatus(),
                user.getErrCode(),
                user.getErrMsg(),
                user.getLocale(),
                authorizeDTO,
                transferDTO,
                cancelDTO

        );
    }

    public static double twoDecimals(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
