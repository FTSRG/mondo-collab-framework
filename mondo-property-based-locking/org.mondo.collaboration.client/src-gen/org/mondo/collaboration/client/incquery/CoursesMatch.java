package org.mondo.collaboration.client.incquery;

import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.mondo.collaboration.client.incquery.util.CoursesQuerySpecification;
import school.Course;

/**
 * Pattern-specific match representation of the org.mondo.collaboration.client.incquery.courses pattern,
 * to be used in conjunction with {@link CoursesMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see CoursesMatcher
 * @see CoursesProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class CoursesMatch extends BasePatternMatch {
  private Course fC;
  
  private static List<String> parameterNames = makeImmutableList("C");
  
  private CoursesMatch(final Course pC) {
    this.fC = pC;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("C".equals(parameterName)) return this.fC;
    return null;
  }
  
  public Course getC() {
    return this.fC;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("C".equals(parameterName) ) {
    	this.fC = (school.Course) newValue;
    	return true;
    }
    return false;
  }
  
  public void setC(final Course pC) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fC = pC;
  }
  
  @Override
  public String patternName() {
    return "org.mondo.collaboration.client.incquery.courses";
  }
  
  @Override
  public List<String> parameterNames() {
    return CoursesMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fC};
  }
  
  @Override
  public CoursesMatch toImmutable() {
    return isMutable() ? newMatch(fC) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"C\"=" + prettyPrintValue(fC)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fC == null) ? 0 : fC.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof CoursesMatch)) { // this should be infrequent
    	if (obj == null) {
    		return false;
    	}
    	if (!(obj instanceof IPatternMatch)) {
    		return false;
    	}
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    CoursesMatch other = (CoursesMatch) obj;
    if (fC == null) {if (other.fC != null) return false;}
    else if (!fC.equals(other.fC)) return false;
    return true;
  }
  
  @Override
  public CoursesQuerySpecification specification() {
    try {
    	return CoursesQuerySpecification.instance();
    } catch (IncQueryException ex) {
     	// This cannot happen, as the match object can only be instantiated if the query specification exists
     	throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static CoursesMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pC the fixed value of pattern parameter C, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static CoursesMatch newMutableMatch(final Course pC) {
    return new Mutable(pC);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pC the fixed value of pattern parameter C, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static CoursesMatch newMatch(final Course pC) {
    return new Immutable(pC);
  }
  
  private static final class Mutable extends CoursesMatch {
    Mutable(final Course pC) {
      super(pC);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends CoursesMatch {
    Immutable(final Course pC) {
      super(pC);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
