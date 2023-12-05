public class DataValidator {
    public static void validateUserId(String userId) {
        String[] arrOfId = userId.split("-");
        if (arrOfId.length != 5 ||
                arrOfId[0].length() != 8 ||
                arrOfId[1].length() != 4 ||
                arrOfId[2].length() != 4 ||
                arrOfId[3].length() != 4 ||
                arrOfId[4].length() != 12) {
            throw new ValidationException("Id format is wrong, user id must be 36 characters");
        }
    }

    public static void validateUserIdLength(String userId) {
        if (userId.length() != 36) {
            throw new ValidationException("User id must be 36 characters");
        }
    }

    public static void validateFileLine(String[] arrOfStr) {
        if (arrOfStr.length != 4) {
            throw new ValidationException("File line must have 4 elements");
        }
    }

    public static void validateDate(int year, int month, int day) {
        if (year < 0 || month < 0 || day < 0) {
            throw new ValidationException("Date cannot be negative");
        }
    }

    // date hakkında daha çok error --12-12-23-
}
