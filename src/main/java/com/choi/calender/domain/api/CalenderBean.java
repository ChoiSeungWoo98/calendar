package com.choi.calender.domain.api;

import com.choi.calender.application.dto.calender.CalenderDto;
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

@Getter
@NoArgsConstructor
public class CalenderBean {
    protected String test1;
    protected String test2;
    protected String test3;
    protected String test4;
    protected String test5;
    protected String test6;

    public CalenderBean(
            String test1,
            String test2,
            String test3,
            String test4,
            String test5,
            String test6
    ) {
        this.test1 = test1;
        this.test2 = test2;
        this.test3 = test3;
        this.test4 = test4;
        this.test5 = test5;
        this.test6 = test6;
    }

    public CalenderBean convertBeanDto(CalenderDto calenderDto) {
        try {
            return new CalenderBean(
                    AES256.encrypt(calenderDto.getTest1()),
                    AES256.encrypt(calenderDto.getTest2()),
                    calenderDto.getTest3(),
                    calenderDto.getTest4(),
                    calenderDto.getTest5(),
                    calenderDto.getTest6()
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