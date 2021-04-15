package validator;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines validators
 */
public class FileValidator {

    /**
     * Checks if filePath is a valid file path
     * @param filePath  the file path validation is being performed on
     * @return true if the filePath is valid
     */
    public static boolean isFilePath(String filePath) {

        if(StringUtils.isEmpty(filePath))
            return false;

        String regex = "^([a-zA-Z]\\:)(\\\\[a-zA-Z_\\-\\s0-9\\.]+)+\\.(json)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filePath);

        if(BooleanUtils.isFalse(matcher.matches()))
            return false;

        return true;
    }

}
