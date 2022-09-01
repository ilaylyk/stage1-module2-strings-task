package com.epam.mjc;

import java.util.Arrays;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> modifiers = Arrays.asList("public", "private", "protected");
        StringSplitter stringSplitter = new StringSplitter();
        List<String> stringsSignature = stringSplitter.splitByDelimiters(signatureString, Arrays.asList(",", "\\s", "\\(", "\\)"));
        String accessModifier = "";

        int ifModifier = 0;
        if (modifiers.contains(stringsSignature.get(ifModifier)))
            accessModifier = stringsSignature.get(ifModifier++);

        MethodSignature methodSignature = new MethodSignature(stringsSignature.get(1 + ifModifier));
        methodSignature.setReturnType(stringsSignature.get(ifModifier));

        if (ifModifier > 0) methodSignature.setAccessModifier(accessModifier);
        List<MethodSignature.Argument> arguments = methodSignature.getArguments();

        for (int i = 2 + ifModifier; i < stringsSignature.size(); ) {
            arguments.add(new MethodSignature.Argument(
                    stringsSignature.get(i++),
                    stringsSignature.get(i++)));
        }
        return methodSignature;
    }
}
