package com.choi.calender.application.dto;


import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.util.AES256;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDto {
    protected int no;
    protected String diaryDate;
    protected String dayOfWeek;
    protected String weather;
    protected String temp;
    protected List<String> emotions;
    protected String content;

    public DiaryDto(
            int no,
            String diaryDate,
            String dayOfWeek,
            String weather,
            String temp,
            List<String> emotions,
            String content
    ) {
        this.no = no;
        this.diaryDate = diaryDate;
        this.dayOfWeek = dayOfWeek;
        this.weather = weather;
        this.temp = temp;
        this.emotions = emotions;
        this.content = content;
    }

    public DiaryDto convertBeanToDto(DiaryBean diaryBean) {
        try {
            List<String> emotions = new ArrayList<>();
            if(StringUtils.isNotBlank(diaryBean.getEmotion())) {
                emotions = List.of(diaryBean.getEmotion().split(","));
            }

            return new DiaryDto(
                    diaryBean.getNo(),
                    diaryBean.getDiaryDate(),
                    diaryBean.getDayOfWeek(),
                    diaryBean.getWeather(),
                    diaryBean.getTemp(),
                    emotions,
                    StringUtils.isBlank(diaryBean.getContent()) ? null : AES256.decrypt(diaryBean.getContent())
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

    public boolean isDataEmptyCheck() {
        return
            StringUtils.isBlank(this.diaryDate)
            || StringUtils.isBlank(this.dayOfWeek)
            || StringUtils.isBlank(this.weather)
            || StringUtils.isBlank(this.temp)
            || ObjectUtils.isEmpty(this.emotions)
            || StringUtils.isBlank(this.content)
        ;
    }

    public boolean isUpdateDataEmptyCheck() {
        return
            this.no == 0
            || ObjectUtils.isEmpty(this.emotions)
            || StringUtils.isBlank(this.content)
        ;
    }
}
