package com.choi.calender.domain.api;

import com.choi.calender.application.calender.dto.DiaryDto;
import com.choi.calender.application.calender.dto.TargetDto;
import com.choi.calender.util.AES256;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Getter
@NoArgsConstructor
public class TargetBean {
    protected String title;
    protected String type;
    protected String year;
    protected String time;
    protected String repeatYn;
    protected String successYn;

    public TargetBean(
            String title,
            String type,
            String year,
            String time,
            String repeatYn,
            String successYn
    ) {
        this.title = title;
        this.type = type;
        this.year = year;
        this.time = time;
        this.repeatYn = repeatYn;
        this.successYn = successYn;
    }

    public TargetBean convertBeanDto(TargetDto targetDto) {
        try {
            return new TargetBean(
                AES256.encrypt(targetDto.getTitle()),
                targetDto.getType(),
                targetDto.getYear(),
                targetDto.getTime(),
                targetDto.getRepeatYn(),
                targetDto.getSuccessYn()
            );
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}