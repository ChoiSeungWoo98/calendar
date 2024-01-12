package com.choi.calender.domain.api;

import com.choi.calender.application.diary.dto.DiaryDto;
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
public class DiaryBean {
    protected int no;
    protected String diaryDate;
    protected String dayOfWeek;
    protected String weather;
    protected String temp;
    protected String emotion;
    protected List<String> emotions;
    protected String content;

    public DiaryBean(
            int no,
            String diaryDate,
            String dayOfWeek,
            String weather,
            String temp,
            String emotion,
            List<String> emotions,
            String content
    ) {
        this.no = no;
        this.diaryDate = diaryDate;
        this.dayOfWeek = dayOfWeek;
        this.weather = weather;
        this.temp = temp;
        this.emotion = emotion;
        this.emotions = emotions;
        this.content = content;
    }

    public DiaryBean convertDtoToBean(DiaryDto diaryDto) {
        try {
            String convertEmotion = "";
            int size = diaryDto.getEmotions().size();
            for(int i = 0; i < diaryDto.getEmotions().size(); i++) {
                convertEmotion += diaryDto.getEmotions().get(i);
                if(i != size -1) {
                    convertEmotion += ", ";
                }
            }
            return new DiaryBean(
                diaryDto.getNo(),
                diaryDto.getDiaryDate(),
                diaryDto.getDayOfWeek(),
                diaryDto.getWeather(),
                diaryDto.getTemp(),
                convertEmotion,
                null,
                AES256.encrypt(diaryDto.getContent())
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