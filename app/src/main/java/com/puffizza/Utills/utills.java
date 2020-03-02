package com.puffizza.Utills;

import java.util.regex.Pattern;

public class utills {

    public static final String MyPREFERENCES = "Puffizza" ;
    public static final String ID = "Id" ;
    public static final String F_Name = "F_nameKey";
    public static final String L_Name = "L_nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );
}
