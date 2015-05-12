package org.mondo.collaboration.client.incquery.util;

import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.mondo.collaboration.client.incquery.CourseWithPrimeWeightMatch;
import school.Course;

/**
 * A match processor tailored for the org.mondo.collaboration.client.incquery.courseWithPrimeWeight pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class CourseWithPrimeWeightProcessor implements IMatchProcessor<CourseWithPrimeWeightMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pC the value of pattern parameter C in the currently processed match
   * 
   */
  public abstract void process(final Course pC);
  
  @Override
  public void process(final CourseWithPrimeWeightMatch match) {
    process(match.getC());
  }
}
