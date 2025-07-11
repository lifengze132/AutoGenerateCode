package com.plugindev.autogeneratecode;

public class SqlTypeToJavaTypeUtils {

    public static String convertSqlTypeToJavaType(String sqlType) {
        if (sqlType == null || sqlType.isEmpty()) {
            return "Object";
        }

        switch (sqlType.toLowerCase()) {
            case "varchar":
            case "char":
            case "text":
            case "longtext":
                return "String";

            case "int":
            case "integer":
                return "Integer";

            case "bigint":
                return "Long";

            case "decimal":
            case "numeric":
                return "BigDecimal";

            case "date":
            case "datetime":
            case "timestamp":
                return "Date";

            case "boolean":
            case "bit":
                return "Boolean";

            case "double":
            case "float":
                return "Double";

            case "tinyint":
                return "Byte";

            case "blob":
            case "varbinary":
                return "byte[]";

            default:
                return "Object";
        }
    }
}
