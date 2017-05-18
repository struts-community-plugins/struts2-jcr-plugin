package org.apache.struts2.jcr.wrapper;


import javax.jcr.ItemNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.query.Row;

public class RowWrapperImpl implements Row {
  private Row delegee;

  public RowWrapperImpl(Row row) {
    delegee = row;
  }

  public javax.jcr.Value[] getValues() throws RepositoryException {
    return  delegee.getValues();
  }

  public javax.jcr.Value getValue(String propertyName) throws ItemNotFoundException, RepositoryException {
    return delegee.getValue(propertyName);
  }

}
