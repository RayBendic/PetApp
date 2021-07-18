package aspect.util;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;


@Component
public class MessagePlaceholderResolver {

    //match with ${...} placeholder
    private static final Pattern PARAMETER_REGEX = Pattern.compile("\\$\\{.*?}");

    public String resolve(String placeHolderString, Map<String, Object> parameters) {

        return PARAMETER_REGEX
                .matcher(placeHolderString)
                .replaceAll(matchResult -> replace(matchResult, parameters));
    }

    private String replace(MatchResult matchResult, Map<String, Object> parameters) {

        String parameterName = removeTrailingSymbols(matchResult);

        return parameters.containsKey(parameterName) ?
                parameters.get(parameterName).toString() :
                "[No value found for ".concat(parameterName).concat("]");

    }

    private String removeTrailingSymbols(MatchResult matchResult) {
        return matchResult.group()
                .replace("${", "")
                .replace("}", "");
    }
}
