package org.apache.struts2.jcr.wrapper;

import org.apache.log4j.Logger;

import javax.jcr.*;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PropertyUtils {
  private static Logger logger = Logger.getLogger(PropertyUtils.class);

  private static Map<String, Integer> objectTypeMap = new HashMap<String, Integer>();

  static {
    objectTypeMap.put(Long.class.getName(), PropertyType.LONG);
    objectTypeMap.put(Integer.class.getName(), PropertyType.LONG);
    objectTypeMap.put(Byte.class.getName(), PropertyType.LONG);

    objectTypeMap.put(Double.class.getName(), PropertyType.DOUBLE);
    objectTypeMap.put(Float.class.getName(), PropertyType.DOUBLE);

    objectTypeMap.put(Boolean.class.getName(), PropertyType.BOOLEAN);
    objectTypeMap.put(String.class.getName(), PropertyType.STRING);

    objectTypeMap.put(Date.class.getName(), PropertyType.DATE);
    objectTypeMap.put(Calendar.class.getName(), PropertyType.DATE);
    objectTypeMap.put(java.sql.Date.class.getName(), PropertyType.DATE);
    objectTypeMap.put(java.sql.Timestamp.class.getName(), PropertyType.DATE);

    objectTypeMap.put(InputStream.class.getName(), PropertyType.BINARY);
  }
  public static  Object generalizeObjectType(Object value, int type) {
    if (type == PropertyType.LONG) {
      if (value instanceof Integer) {
        return ((Integer) value).longValue();
      }
      if (value instanceof Byte) {
        return ((Byte) value).longValue();
      }
    }
    if (type == PropertyType.DATE && value instanceof Calendar) {
      return ((Calendar) value).getTime();
    }
    return value;
  }

  public static  int getObjectType(Object value) {
    Integer type = objectTypeMap.get(value.getClass().getName());
    if (type == null) {
      if(value instanceof Node){
        return PropertyType.REFERENCE;
      }
      if(value instanceof InputStream){
        return PropertyType.BINARY;
      }
      if(value instanceof Calendar){
        return PropertyType.DATE;
      }
      if(value instanceof Boolean){
        return PropertyType.BOOLEAN;
      }
      if(value instanceof Long){
        return PropertyType.LONG;
      }
      if(value instanceof String){
        return PropertyType.STRING;
      }
      else throw new RuntimeException("Unknown object type");
    }
    return type;
  }

  public static Object getObjectByPropertyValue(Value value) throws RepositoryException {
    if(value==null){
      return null;
    }
    switch (value.getType()) {
      case PropertyType.BOOLEAN: {
        return value.getBoolean();
      }
      case PropertyType.DATE: {
        return value.getDate().getTime();
      }
      case PropertyType.LONG: {
        return value.getLong();
      }
      case PropertyType.DOUBLE: {
        return value.getDouble();
      }
      case PropertyType.NAME:
      case PropertyType.PATH:
      case PropertyType.STRING: {
        return value.getString();
      }
      case PropertyType.BINARY: {
        return value.getStream();
      }
    }
    return null;

  }
  public static Object getObjectByProperty(Property property,WrapperFactory wrapperFactory) throws RepositoryException {
    if(property==null){
      return null;
    }
    Object objectByPropertyValue = getObjectByPropertyValue(property.getValue());
    if(objectByPropertyValue!=null){
      return objectByPropertyValue;
    }
    if(property.getType()==PropertyType.REFERENCE){
      return wrapperFactory.createWrapped(property.getNode());
    }
    return null;
  }

  public static Object setCorrespondingProperty(Node node, String key, Object value) {
    int type = getObjectType(value);
    return setCorrespondingProperty(node,key,value,type);
  }
  public static Property setCorrespondingProperty(Node node, String key, Object value, int type) {
    try {
      value = generalizeObjectType(value, type);
      switch (type) {
        case PropertyType.DATE: {
          Calendar instance = Calendar.getInstance();
          instance.setTime((Date) value);
          return node.setProperty(key, instance);
        }
        case PropertyType.LONG: {
          return node.setProperty(key, (Long) value);
        }
        case PropertyType.DOUBLE: {
          return node.setProperty(key, (Double) value);
        }
        case PropertyType.BOOLEAN: {
          return node.setProperty(key, (Boolean) value);
        }
        case PropertyType.STRING: {
          return node.setProperty(key, value.toString());
        }
        case PropertyType.BINARY: {
          return node.setProperty(key, (InputStream) value);
        }
        case PropertyType.REFERENCE: {
          return node.setProperty(key, ((NodeWrapper) value));
        }
        default: {
          throw new RuntimeException("Unknown Object Type");
        }
      }
    } catch (RepositoryException e) {
      logger.error(e);
    }
    return null;
  }

}
