package com.choi.calender.application.target.dto;


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
public class TargetDto {
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

    public TargetDto(
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

    public TargetDto convertBeanToDto(TargetBean targetBean) {
        try {
            return new TargetDto(
                    targetBean.getNo(),
                    AES256.decrypt(targetBean.getTitle()),
                    targetBean.getType(),
                    targetBean.getYear(),
                    targetBean.getMonth(),
                    targetBean.getDay(),
                    targetBean.getTime(),
                    targetBean.getRepeatYn(),
                    targetBean.getSuccessYn(),
                    targetBean.getDeleteYn()
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

    public boolean isYearDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
        ;
    }

    public boolean isMonthDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
            || StringUtils.isBlank(this.month)
        ;
    }

    public boolean isDayDataEmptyCheck() {
        return
            StringUtils.isBlank(this.title)
            || StringUtils.isBlank(this.type)
            || StringUtils.isBlank(this.year)
            || StringUtils.isBlank(this.month)
            || StringUtils.isBlank(this.day)
            || StringUtils.isBlank(this.time)
            || StringUtils.isBlank(this.repeatYn)
        ;
    }
}
