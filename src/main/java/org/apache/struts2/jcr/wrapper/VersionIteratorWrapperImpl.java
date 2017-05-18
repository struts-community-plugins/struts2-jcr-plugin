package org.apache.struts2.jcr.wrapper;

import javax.jcr.version.VersionIterator;
import javax.jcr.version.Version;

/**
 * Wrapper class used to wrap VersionIterator to make it return wrapped Version
 */
public class VersionIteratorWrapperImpl implements VersionIterator {
  private VersionIterator delegee;
  private WrapperFactory wrapperFactory;

  public VersionIteratorWrapperImpl(VersionIterator delegee,WrapperFactory wrapperFactory) {
    this.delegee = delegee;
    this.wrapperFactory = wrapperFactory;
  }

  public Version nextVersion() {
    return wrapperFactory.createWrapped(delegee.nextVersion());
  }

  public void skip(long l) {
    delegee.skip(l);
  }

  public long getSize() {
    return delegee.getSize();
  }

  public long getPosition() {
    return delegee.getPosition();
  }

  public boolean hasNext() {
    return delegee.hasNext();
  }

  public Object next() {
    return wrapperFactory.createWrapped(delegee.nextVersion());
  }

  public void remove() {
    delegee.remove();
  }
}
