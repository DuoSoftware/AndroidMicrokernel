package com.duoworld.framework.mobilemicrokernel.helpers;

/**
 * Created by Supun on 9/15/2016.
 */
public class MKStringHelper {

    public static String Append(String... args){
        return AppendByDelimeter(null, args);
    }

    public static String AppendByDelimeter(String endDelimiter, String... args){
        StringBuilder sb = new StringBuilder();

        boolean isFirst = true;
        for (int i=0;i<args.length;i++) {
            if (isFirst) {
                isFirst = false;
            } else {
                if (endDelimiter !=null)
                    sb.append(endDelimiter);
            }
            sb.append(args[i]);
        }

        return sb.toString();
    }
}
