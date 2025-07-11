package com.plugindev.autogeneratecode;

// 列信息类
public class ColumnInfo {
    private String name;
    private String placeHolderName;
    private String type;
    private int size;
    private boolean nullable;
    private String remarks;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public boolean isNullable() { return nullable; }
    public void setNullable(boolean nullable) { this.nullable = nullable; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getPlaceHolderName() {
        return placeHolderName;
    }

    public void setPlaceHolderName(String placeHolderName) {
        this.placeHolderName = "#{"+placeHolderName+"}";
    }

    // 获取Java类型
    public String getJavaType() {
        switch(type.toLowerCase()) {
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
                return "BigDecimal";
            case "date":
            case "datetime":
            case "timestamp":
                return "Date";
            case "boolean":
            case "bit":
                return "Boolean";
            default:
                return "Object";
        }
    }

    // 获取Java属性名(下划线转驼峰)
    public String getJavaName() {
        String[] parts = name.split("_");
        StringBuilder javaName = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                javaName.append(parts[i].substring(0, 1).toUpperCase())
                        .append(parts[i].substring(1).toLowerCase());
            }
        }
        return javaName.toString();
    }


}