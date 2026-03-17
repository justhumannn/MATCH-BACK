package post.post.domain.application;

// 내부 시스템 1: 보안 검사
class SecurityChecker {
    void check(String file) { System.out.println(file + " 보안 검사 중..."); }
}

// 내부 시스템 2: 파일 압축
class FileCompressor {
    void compress(String file) { System.out.println(file + " 압축 진행 중..."); }
}

// 내부 시스템 3: 클라우드 전송
class CloudUploader {
    void upload(String file) { System.out.println(file + " 서버 전송 완료!"); }
}

class FileFacade {
    SecurityChecker checker = new SecurityChecker();
    FileCompressor compressor = new FileCompressor();
    CloudUploader uploader = new CloudUploader();
    public void fileUpload(String myFile){
        checker.check(myFile);
        compressor.compress(myFile);
        uploader.upload(myFile);
    }
}

public class Main {
    public static void main(String[] args) {
        String myFile = "design-pattern.pdf";

        FileFacade fileFacade = new FileFacade();
        fileFacade.fileUpload(myFile);
    }
}

// todo : 퍼사드 패턴을 활용하여 리팩토링하세요.