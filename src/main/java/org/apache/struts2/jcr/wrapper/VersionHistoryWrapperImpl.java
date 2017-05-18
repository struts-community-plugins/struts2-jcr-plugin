package org.apache.struts2.jcr.wrapper;

import javax.jcr.version.VersionHistory;
import javax.jcr.version.Version;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionException;
import javax.jcr.RepositoryException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.AccessDeniedException;
import javax.jcr.UnsupportedRepositoryOperationException;

/**
 * Wrapper class used to wrap VersionHistory to make it return wrapped Version, which will return wrapped 
 * javax.jcr.Node which are both java.util.Map and javax.jcr.Node
 */
public class VersionHistoryWrapperImpl extends NodeWrapperImpl implements VersionHistory {
  private VersionHistory versionHistoryDelegee;

  public VersionHistoryWrapperImpl(VersionHistory versionHistory,WrapperFactory wrapperFactory) {
    super(versionHistory, wrapperFactory);
    this.versionHistoryDelegee = versionHistory;
  }

  public String getVersionableUUID() throws RepositoryException {
    return versionHistoryDelegee.getVersionableUUID();
  }

  public Version getRootVersion() throws RepositoryException {
    return wrapperFactory.createWrapped(versionHistoryDelegee.getRootVersion());
  }

  public VersionIterator getAllVersions() throws RepositoryException {
    return wrapperFactory.createWrapped(versionHistoryDelegee.getAllVersions());
  }

  public Version getVersion(String s) throws VersionException, RepositoryException {
    return wrapperFactory.createWrapped(versionHistoryDelegee.getVersion(s));
  }

  public Version getVersionByLabel(String s) throws RepositoryException {
    return wrapperFactory.createWrapped(versionHistoryDelegee.getVersionByLabel(s));
  }

  public void addVersionLabel(String s, String s1, boolean b) throws VersionException, RepositoryException {
    versionHistoryDelegee.addVersionLabel(s,s1,b);
  }

  public void removeVersionLabel(String s) throws VersionException, RepositoryException {
     versionHistoryDelegee.removeVersionLabel(s);
  }

  public boolean hasVersionLabel(String s) throws RepositoryException {
    return versionHistoryDelegee.hasVersionLabel(s);
  }

  public boolean hasVersionLabel(Version version, String s) throws VersionException, RepositoryException {
    return versionHistoryDelegee.hasVersionLabel(version,s);
  }

  public String[] getVersionLabels() throws RepositoryException {
    return versionHistoryDelegee.getVersionLabels();
  }

  public String[] getVersionLabels(Version version) throws VersionException, RepositoryException {
    while(version instanceof VersionWrapperImpl){
      version = (Version) ((VersionWrapperImpl) version).getDelegee();
    }
    return versionHistoryDelegee.getVersionLabels(version);
  }

  public void removeVersion(String s) throws ReferentialIntegrityException, AccessDeniedException, UnsupportedRepositoryOperationException, VersionException, RepositoryException {
    versionHistoryDelegee.removeVersion(s);
  }
}
