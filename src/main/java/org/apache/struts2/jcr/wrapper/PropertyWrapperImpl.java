package org.apache.struts2.jcr.wrapper;

import javax.jcr.*;
import javax.jcr.nodetype.PropertyDefinition;
import java.io.InputStream;
import java.util.Calendar;

/**
 *
 * Wrapper class used to wrap Property
 * Main reason to wrap Property is to override getNode() and getParent() methods to made them return NodeWrapper instead of Node 
 *  */
public class PropertyWrapperImpl implements Property {
  private Property delegee;
  private WrapperFactory wrapperFactory;

  public PropertyWrapperImpl(Property delegee,WrapperFactory wrapperFactory) {
    this.delegee = delegee;
    this.wrapperFactory = wrapperFactory;
  }

  public void setValue(Value value) throws RepositoryException {
    delegee.setValue(value);
  }

  public void setValue(Value[] values) throws RepositoryException {
    delegee.setValue(values);
  }

  public void setValue(String s) throws RepositoryException {
    delegee.setValue(s);
  }

  public void setValue(String[] strings) throws RepositoryException {
    delegee.setValue(strings);
  }

  public void setValue(InputStream inputStream) throws RepositoryException {
    delegee.setValue(inputStream);
  }

  public void setValue(long l) throws  RepositoryException {
    delegee.setValue(l);
  }

  public void setValue(double v) throws  RepositoryException {
    delegee.setValue(v);
  }

  public void setValue(Calendar calendar) throws  RepositoryException {
    delegee.setValue(calendar);
  }

  public void setValue(boolean b) throws RepositoryException {
    delegee.setValue(b);
  }

  public void setValue(Node node) throws RepositoryException {
    delegee.setValue(node);
  }

  public Value getValue() throws RepositoryException {
    return delegee.getValue();
  }

  public Value[] getValues() throws RepositoryException {
    return delegee.getValues();
  }

  public String getString() throws RepositoryException {
    return delegee.getString();
  }

  public InputStream getStream() throws RepositoryException {
    return delegee.getStream();
  }

  public long getLong() throws RepositoryException {
    return delegee.getLong();
  }

  public double getDouble() throws RepositoryException {
    return delegee.getDouble();
  }

  public Calendar getDate() throws RepositoryException {
    return delegee.getDate();
  }

  public boolean getBoolean() throws RepositoryException {
    return delegee.getBoolean();
  }

  public Node getNode() throws  RepositoryException {
    return wrapperFactory.createWrapped(delegee.getNode());
  }

  public long getLength() throws RepositoryException {
    return delegee.getLength();
  }

  public long[] getLengths() throws RepositoryException {
    return delegee.getLengths();
  }

  public PropertyDefinition getDefinition() throws RepositoryException {
    return delegee.getDefinition();
  }

  public int getType() throws RepositoryException {
    return delegee.getType();
  }

  public String getPath() throws RepositoryException {
    return delegee.getPath();
  }

  public String getName() throws RepositoryException {
    return delegee.getName();
  }

  public Item getAncestor(int i) throws RepositoryException {
    return delegee.getAncestor(i);
  }

  public Node getParent() throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getParent());
  }

  public int getDepth() throws RepositoryException {
    return delegee.getDepth();
  }

  public Session getSession() throws RepositoryException {
    return delegee.getSession();
  }

  public boolean isNode() {
    return delegee.isNode();
  }

  public boolean isNew() {
    return delegee.isNew();
  }

  public boolean isModified() {
    return delegee.isModified();
  }

  public boolean isSame(Item item) throws RepositoryException {
    return delegee.isSame(item);
  }

  public void accept(ItemVisitor itemVisitor) throws RepositoryException {
    delegee.accept(itemVisitor);
  }

  public void save()throws  RepositoryException {
    delegee.save();
  }

  public void refresh(boolean b) throws RepositoryException {
    delegee.refresh(b);
  }

  public void remove() throws  RepositoryException {
    delegee.remove();
  }
}
