package com.choi.calender.domain.api;

import com.choi.calender.application.calender.dto.CalenderDto;
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
public class CalenderBean {
    protected String date;
    protected String dayOfWeek;
    protected String weather;
    protected String emotion;
    protected List<String> emotions;
    protected String content;

    public CalenderBean(
            String date,
            String dayOfWeek,
            String weather,
            String emotion,
            List<String> emotions,
            String content
    ) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.weather = weather;
        this.emotion = emotion;
        this.emotions = emotions;
        this.content = content;
    }

    public CalenderBean convertBeanDto(CalenderDto calenderDto) {
        try {
            String convertEmotion = "";
            int size = calenderDto.getEmotions().size();
            for(int i = 0; i < calenderDto.getEmotions().size(); i++) {
                convertEmotion += calenderDto.getEmotions().get(i);
                if(i != size -1) {
                    convertEmotion += ", ";
                }
            }
            return new CalenderBean(
                calenderDto.getDate(),
                calenderDto.getDayOfWeek(),
                calenderDto.getWeather(),
                convertEmotion,
                null,
                AES256.encrypt(calenderDto.getContent())
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