package com.choi.calender.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class Common {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public String getToDate(String format) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat((format == null || format.equals("") ? "yyyyMMdd" : format));

        return sdf.format(d);
    }

    public String getUuid(boolean upperFlag) {
        String uuid = UUID.randomUUID().toString();
        return (upperFlag)
                ? uuid.toUpperCase()
                : uuid.toLowerCase();
    }

    public String getRandomString(boolean upperFlag, int size) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            boolean isNumber = Math.floor(Math.random() * 26) == 0;

            int weight = (isNumber) ? 10 : 26;
            int startIndex = (isNumber) ? 48 : 97;
            builder.append((char) Math.floor((Math.random() * weight) + startIndex));
        }

        String randomString = builder.toString();
        return (upperFlag)
                ? randomString.toUpperCase()
                : randomString.toLowerCase();
    }

    public String getRandomFileName(int length, String extension) {
        if(StringUtils.isBlank(extension)) return getRandomString(false, length);
        return getRandomString(false, length) + "." + extension;
    }

    public String getMultipartFileExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        return FilenameUtils.getExtension(originalFileName);
    }

    public String getFileExtension(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    /**
     * @deprecated
     * @see File fileConvert(MultipartFile mf, String newName)
     */
    public File convertMultipartFileToFile(MultipartFile mf) throws IOException {
        File file = new File(
            System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + mf.getOriginalFilename()
        );

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mf.getBytes());
        fos.close();

        return file;
    }

    public File convertMultipartFileToFile(MultipartFile mf, String newName) throws IOException {
        File file = new File(
            System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + newName
        );

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mf.getBytes());
        fos.close();

        return file;
    }

    public String getBase64ImgFile(String key, String fileName, String fileExt) {
        String base64Image = "";
        String imgPath = System.getProperty("user.dir") + File.separator + "file" + File.separator + key + File.separator + fileName + "." +fileExt;

        try {
            File file = new File(imgPath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            fileInputStream.close();

            base64Image = Base64.encodeBase64String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64Image;
    }

    public String saveMultipartFile(MultipartFile file, String path) throws IOException {
        String ext = getMultipartFileExtension(file);
        String uniqueFileName = getRandomFileName(32, ext);
        file.transferTo(new File(path, uniqueFileName));
        return uniqueFileName;
    }

    public String checkAndCreateForder(String backPath) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + backPath;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("폴더 생성 성공 (path : "  + path + ")");
        }

        return path;
    }

    public String getIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String trimMobileNumber(String mobileNo) {
        if (mobileNo != null) {
            if (mobileNo.length() <= 11) {
                mobileNo = "0" + mobileNo;
            }

            // 일단 기존 - 전부 제거
            return mobileNo
                .replaceAll(Pattern.quote("-"), "")
                .replaceAll(Pattern.quote("."), "")
                .substring(0, 11);
        }

        return null;
    }

    public boolean unZip(File zipFile, File unZipDirectory) {
        // 파일 정상적으로 압축이 해제가 되어는가.
        boolean isChk = false;

        // zip 파일
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry zipentry = null;

        try {
            // 파일 스트림
            fis = new FileInputStream(zipFile);

            // Zip 파일 스트림
            zis = new ZipInputStream(fis, Charset.defaultCharset());

            // 압축되어 있는 ZIP 파일의 목록 조회
            while ((zipentry = zis.getNextEntry()) != null) {
                String fileName = zipentry.getName();
                String lowerCaseFileName = fileName.toLowerCase();
                // MAC에서 생성된 파일인 경우
                if (lowerCaseFileName.contains("macosx") || lowerCaseFileName.contains(".ds_store")) {
                    continue;
                }

                File file = new File(unZipDirectory, fileName);

                // entiry가 폴더면 폴더 생성
                if (zipentry.isDirectory()) {
                    file.mkdirs();
                } else {
                    try {
                        createFile(file, zis);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
            isChk = true;
        } catch (Exception e) {
            isChk = false;
        } finally {
            if (zis != null) {
                try {
                    zis.close();
                } catch (IOException e) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        return isChk;
    }

    /**
     * @param directoryPath - 생성할 폴더 경로와 이름
     */
    private boolean makeFolder(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }

        File directory = new File(directoryPath);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!directory.exists()) {
            try {
                directory.mkdir(); // 폴더 생성합니다.
            } catch (Exception e) {
                e.getStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 파일 만들기 메소드
     *
     * @param file 파일
     * @param zis  Zip스트림
     */
    private void createFile(File file, ZipInputStream zis) throws Throwable {

        // 디렉토리 확인
        File parentDir = new File(file.getParent());
        // 디렉토리가 없으면 생성하자
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        FileOutputStream fos = null;
        // 파일 스트림 선언
        try {

            fos = new FileOutputStream(file);
            byte[] buffer = new byte[256];
            int size = 0;
            // Zip스트림으로부터 byte뽑아내기
            while ((size = zis.read(buffer)) > 0) {
                // byte로 파일 만들기
                fos.write(buffer, 0, size);
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }

            }

        }

    }

    /**
     * directory 내부 파일을 모두 조회하는 재귀함수
     * @author parkjg20
     */
    public void getFilesToList(File file, List<File> fileList) {
        // 디렉토리가 아닌 경우
        if (!file.isDirectory()) {
            fileList.add(file);
            return;
        }

        File[] files = file.listFiles();
        assert files != null;
        Arrays.stream(files).forEach(innerFile -> getFilesToList(innerFile, fileList));
    }

    public boolean isDebugModeCheck() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean()
                .getInputArguments().toString().contains("-agentlib:jdwp");
    }
}
