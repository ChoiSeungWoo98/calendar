package com.choi.calender.application.dto;


import com.choi.calender.domain.api.TargetBean;
import com.choi.calender.util.AES256;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@NoArgsConstructor
public class TodoTargetDto {
    protected int no;
    protected String targetNo;
    protected String diaryNo;
    protected String successYn;

    public TodoTargetDto(
        int no,
        String targetNo,
        String diaryNo,
        String successYn
    ) {
        this.no = no;
        this.targetNo = targetNo;
        this.diaryNo = diaryNo;
        this.successYn = successYn;
    }

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.targetNo)
            || StringUtils.isBlank(this.diaryNo)
            || StringUtils.isBlank(this.successYn)
        ;
    }

}
