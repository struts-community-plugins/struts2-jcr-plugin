package org.apache.struts2.jcr.wrapper;

import javax.jcr.RepositoryException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import java.util.Calendar;

/**
 * Version wrapper
 */
public class VersionWrapperImpl extends NodeWrapperImpl implements Version {
  private Version delegee;

  public VersionWrapperImpl(Version delegee, WrapperFactory wrapperFactory) {
    super(delegee, wrapperFactory);
    this.delegee = delegee;
  }

  public VersionHistory getContainingHistory() throws RepositoryException {
    return wrapperFactory.createWrapped(delegee.getContainingHistory());
  }

  public Calendar getCreated() throws RepositoryException {
    return delegee.getCreated();
  }

  public Version[] getSuccessors() throws RepositoryException {
    return delegee.getSuccessors();
  }

  public Version[] getPredecessors() throws RepositoryException {
    return delegee.getPredecessors();
  }
}
