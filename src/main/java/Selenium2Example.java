

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Selenium2Example  {
    public static void main(String[] args) {
        System.out.println(scapeString("Nike SB Zoom Stefan Janoski \"Medium Mint\""));
    }

    public static String scapeString(final String string) {
        Matcher matcher = Pattern.compile("[^'\"]+|['\"]").matcher(string);
        List<String> matches = new ArrayList<>();
        while ( matcher.find() ) {
            matches.add(matcher.group(0));
        }

        return "concat(" + matches.stream().map(c -> mapMatch(c))
                .collect(Collectors.joining(",")) + ")";
    }

    private static String mapMatch(String string) {
        if (string.equals("'"))  {
            return "\"\'\""; // output "'"
        }

        if (string.equals("\"")) {
            return "'\"'"; // output '"'
        }
        return "'" + string + "'";
    }
}