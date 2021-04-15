package validator;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Defines validators
 */
public class UrlValidator {

    /**
     * Checks if url is a valid URL
     * @param url the url validation is being performed on
     * @return true if the url is valid
     */
    public static boolean isValid(String url) {

        if(StringUtils.isEmpty(url))
            return false;

        try {
            new URL(url).toURI();
            return true;
        }
        //A URISyntaxException will be thrown if the URL is not formatted strictly
        // according to RFC and cannot be converted to a URI.
        catch (URISyntaxException exception) {
            return false;
        }
        //A MalformedURLException will be thrown if no protocol is specified,
        // or an unknown protocol is found, or spec is null.
        catch (MalformedURLException exception) {
            return false;
        }
    }

    /**
     * Checks if an url is not a valid URL
     * @param url the url validation is being performed on
     * @return true if the url is not valid
     */
    public static boolean isNotValid(String url) {

        if(StringUtils.isEmpty(url))
            return true;

        try {
            new URL(url).toURI();
            return false;
        }
        //A URISyntaxException will be thrown if the URL is not formatted strictly
        // according to RFC and cannot be converted to a URI.
        catch (URISyntaxException exception) {
            return true;
        }
        //A MalformedURLException will be thrown if no protocol is specified,
        // or an unknown protocol is found, or spec is null.
        catch (MalformedURLException exception) {
            return true;
        }
    }
}
