public class DataValidator {
    public static void validateUserId(String userId) {
        String[] arrOfId = userId.split("-");
        if (arrOfId.length != 5 ||
                arrOfId[0].length() != 8 ||
                arrOfId[1].length() != 4 ||
                arrOfId[2].length() != 4 ||
                arrOfId[3].length() != 4 ||
                arrOfId[4].length() != 12) {
            throw new RuntimeException("Id format is wrong"); // should be 8-4-4-4-12
        }
    }

    public static void validateUserIdLength(String userId) {
        if (userId.length() != 36) {
            throw new RuntimeException("User id must be 36 characters");
        }
    }

    public static void validateFileLine(String[] arrOfStr) {
        if (arrOfStr.length != 4) {
            throw new RuntimeException("File line must have 4 elements");
        }
    }

    public static void validateDate(int year, int month, int day) {
        validateMonth(month);
        validateDay(day);
    }

    public static void validateMonth(int month) {
        if (month > 12) {
            throw new RuntimeException("Month cannot be greater than 12");
        }
    }

    public static void validateDay(int day) {
        if (day > 31) {
            throw new RuntimeException("Day cannot be greater than 31");
        }
    }

}
