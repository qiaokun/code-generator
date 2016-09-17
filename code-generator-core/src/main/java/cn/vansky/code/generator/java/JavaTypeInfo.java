/*
 * Copyright (C) 2015 CK, Inc. All Rights Reserved.
 */

package cn.vansky.code.generator.java;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * JAVA类型信息
 * Author: CK.
 * Date: 2015/6/6.
 */
public class JavaTypeInfo implements Comparable<JavaTypeInfo> {

    /** 类名 */
    private String baseShortName;

    /** 类全名 */
    private String baseQualifiedName;

    /** 需要import 标志 true:需要,false不需要 */
    private boolean explicitlyImported;

    /** 包名 */
    private String packageName;

    /** 是否原始类型 */
    private boolean primitive;

    /** 数组标志 */
    private boolean isArray;

    private boolean wildcardType;

    /** The bounded wildcard. */
    private boolean boundedWildcard;

    /** The extends bounded wildcard. */
    private boolean extendsBoundedWildcard;

    /**  */
    protected JavaTypeInfo fullyQualifiedJavaType;

    /** 原始类型封装 */
    private PrimitiveJavaType primitiveJavaType;

    /** 字段信息 */
    private List<JavaTypeInfo> typeArguments;

    public JavaTypeInfo(String fullTypeSpecification) {
        typeArguments = new ArrayList<JavaTypeInfo>();
        parse(fullTypeSpecification);
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        if (wildcardType) {
            sb.append('?');
            if (boundedWildcard) {
                if (extendsBoundedWildcard) {
                    sb.append(JavaKeywords.EXTENDS);
                } else {
                    sb.append(JavaKeywords.SUPER);
                }

                sb.append(baseShortName);
            }
        } else {
            sb.append(baseShortName);
        }

        if (typeArguments.size() > 0) {
            boolean first = true;
            sb.append('<');
            for (JavaTypeInfo fqjt : typeArguments) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(fqjt.getShortName());

            }
            sb.append('>');
        }

        return sb.toString();
    }

    public String getFullyQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (wildcardType) {
            sb.append('?');
            if (boundedWildcard) {
                if (extendsBoundedWildcard) {
                    sb.append(JavaKeywords.EXTENDS);
                } else {
                    sb.append(JavaKeywords.SUPER);
                }

                sb.append(baseQualifiedName);
            }
        } else {
            sb.append(baseQualifiedName);
        }

        if (typeArguments.size() > 0) {
            boolean first = true;
            sb.append('<');
            for (JavaTypeInfo fqjt : typeArguments) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", "); 
                }
                sb.append(fqjt.getFullyQualifiedName());

            }
            sb.append('>');
        }

        return sb.toString();
    }

    public void addTypeArgument(JavaTypeInfo type) {
        typeArguments.add(type);
    }

    /**
     * 解析
     *
     * @param fullTypeSpecification
     *            the full type specification
     */
    private void parse(String fullTypeSpecification) {
        String spec = fullTypeSpecification.trim();

        if (spec.startsWith("?")) { 
            wildcardType = true;
            spec = spec.substring(1).trim();
            if (spec.startsWith("extends ")) { 
                boundedWildcard = true;
                extendsBoundedWildcard = true;
                spec = spec.substring(8);  // "extends ".length()
            } else if (spec.startsWith("super ")) { 
                boundedWildcard = true;
                extendsBoundedWildcard = false;
                spec = spec.substring(6);  // "super ".length()
            } else {
                boundedWildcard = false;
            }
            parse(spec);
        } else {
            int index = fullTypeSpecification.indexOf('<');
            if (index == -1) {
                simpleParse(fullTypeSpecification);
            } else {
                simpleParse(fullTypeSpecification.substring(0, index));
                int endIndex = fullTypeSpecification.lastIndexOf('>');
                if (endIndex == -1) {
                    throw new RuntimeException(); 
                }
                genericParse(fullTypeSpecification.substring(index, endIndex + 1));
            }

            // this is far from a perfect test for detecting arrays, but is close
            // enough for most cases.  It will not detect an improperly specified
            // array type like byte], but it will detect byte[] and byte[   ]
            // which are both valid
            isArray = fullTypeSpecification.endsWith("]");
        }
    }

    /**
     * Generic parse.
     *
     * @param genericSpecification
     *            the generic specification
     */
    private void genericParse(String genericSpecification) {
        int lastIndex = genericSpecification.lastIndexOf('>');
        if (lastIndex == -1) {
            // shouldn't happen - should be caught already, but just in case...
            throw new RuntimeException(); 
        }
        String argumentString = genericSpecification.substring(1, lastIndex);
        // need to find "," outside of a <> bounds
        StringTokenizer st = new StringTokenizer(argumentString, ",<>", true); 
        int openCount = 0;
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if ("<".equals(token)) { 
                sb.append(token);
                openCount++;
            } else if (">".equals(token)) { 
                sb.append(token);
                openCount--;
            } else if (",".equals(token)) { 
                if (openCount == 0) {
                    typeArguments.add(new JavaTypeInfo(sb.toString()));
                    sb.setLength(0);
                } else {
                    sb.append(token);
                }
            } else {
                sb.append(token);
            }
        }

        if (openCount != 0) {
            throw new RuntimeException(); 
        }

        String finalType = sb.toString();
        if (StringUtils.isNotBlank(finalType)) {
            typeArguments.add(new JavaTypeInfo(finalType));
        }
    }

    /**
     * 获取<之前的包名
     *
     * @param typeSpecification
     *            the type specification
     */
    private void simpleParse(String typeSpecification) {
        baseQualifiedName = typeSpecification.trim();
        if (baseQualifiedName.contains(".")) { 
            packageName = getPackage(baseQualifiedName);
            baseShortName = baseQualifiedName.substring(packageName.length() + 1);
            int index = baseShortName.lastIndexOf('.');
            if (index != -1) {
                baseShortName = baseShortName.substring(index + 1);
            }

            if (JavaKeywords.JAVA_LANG.equals(packageName)) {
                explicitlyImported = false;
            } else {
                explicitlyImported = true;
            }
        } else {
            baseShortName = baseQualifiedName;
            explicitlyImported = false;
            packageName = "";

            primitiveJavaType = PrimitiveJavaType.get(baseQualifiedName);
            if (null != primitiveJavaType) {
                primitive = true;
            }
        }
    }

    private static String getPackage(String baseQualifiedName) {
        int index = baseQualifiedName.lastIndexOf('.');
        return baseQualifiedName.substring(0, index);
    }

    /**
     * 获取导出列表
     * @return
     */
    public List<String> getImportList() {
        List<String> answer = new ArrayList<String>();
        if (isExplicitlyImported()) {
            int index = baseShortName.indexOf('.');
            if (index == -1) {
                answer.add(baseQualifiedName);
            } else {
                // an inner class is specified, only import the top
                // level class
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append('.');
                sb.append(baseShortName.substring(0, index));
                answer.add(sb.toString());
            }
        }

        for (JavaTypeInfo fqjt : typeArguments) {
            answer.addAll(fqjt.getImportList());
        }

        return answer;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isExplicitlyImported() {
        return explicitlyImported;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof JavaTypeInfo)) {
            return false;
        }

        JavaTypeInfo other = (JavaTypeInfo) obj;

        return getFullyQualifiedName().equals(other.getFullyQualifiedName());
    }

    public int hashCode() {
        return getFullyQualifiedName().hashCode();
    }

    public String toString() {
        return getFullyQualifiedName();
    }

    public int compareTo(JavaTypeInfo o) {
        return getFullyQualifiedName().compareTo(o.getFullyQualifiedName());
    }
}
