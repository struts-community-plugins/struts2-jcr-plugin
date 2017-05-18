package org.apache.struts2.jcr.wrapper;

import javax.jcr.query.RowIterator;
import javax.jcr.query.Row;

public class RowIteratorWrapperImpl implements RowIterator {
  private RowIterator delegee;
  private WrapperFactory wrapperFactory;

  public RowIteratorWrapperImpl(RowIterator rowIterator, WrapperFactory wrapperFactory) {
    this.delegee = rowIterator;
    this.wrapperFactory = wrapperFactory;
  }

  public boolean hasNext() {
    return delegee.hasNext();
  }

  public Object next() {
    return wrapperFactory.createWrapped(delegee.nextRow());
  }
  public Row nextRow() {
    return wrapperFactory.createWrapped(delegee.nextRow());
  }

  public void remove() {
    delegee.remove();
  }

  public void skip(long skipNum) {
    delegee.skip(skipNum);
  }

  public long getSize() {
    return delegee.getSize();
  }

  public long getPosition() {
    return delegee.getPosition();
  }
}
