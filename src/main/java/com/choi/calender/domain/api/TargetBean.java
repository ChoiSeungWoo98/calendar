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
    protected int no;
    protected String title;
    protected String type;
    protected String year;
    protected String month;
    protected String day;
    protected String time;
    protected String repeatYn;
    protected String successYn;
    protected String deleteYn;

    public TargetBean(
            int no,
            String title,
            String type,
            String year,
            String month,
            String day,
            String time,
            String repeatYn,
            String successYn,
            String deleteYn
    ) {
        this.no = no;
        this.title = title;
        this.type = type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.repeatYn = repeatYn;
        this.successYn = successYn;
        this.deleteYn = deleteYn;
    }

    public TargetBean convertBeanToDto(TargetDto targetDto) {
        try {
            return new TargetBean(
                targetDto.getNo(),
                AES256.encrypt(targetDto.getTitle()),
                targetDto.getType(),
                targetDto.getYear(),
                targetDto.getMonth(),
                targetDto.getDay(),
                targetDto.getTime(),
                targetDto.getRepeatYn(),
                targetDto.getSuccessYn(),
                targetDto.getDeleteYn()
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