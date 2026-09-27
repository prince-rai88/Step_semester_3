package session2.class_problems.p3_file_validator;

public class FileValidator {
    public String validateFileExtension(String filename) {
        if (filename == null) return "Rejected — invalid file type";
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }
        String ext = filename.substring(lastDotIndex + 1);
        if (ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }
}
