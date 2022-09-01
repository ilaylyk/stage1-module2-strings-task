package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        return Pattern.compile(delimiters.toString())
                .splitAsStream(source).collect(Collectors.toList())
                .stream().filter(i->!i.equals("")).collect(Collectors.toList());
    }
}
