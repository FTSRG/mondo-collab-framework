package org.mondo.collaboration.client.incquery;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.eclipse.incquery.runtime.api.IQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.tuple.Tuple;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;
import org.mondo.collaboration.client.incquery.TeachersOfSchoolNamesMatch;
import org.mondo.collaboration.client.incquery.util.TeachersOfSchoolNamesQuerySpecification;

/**
 * Generated pattern matcher API of the org.mondo.collaboration.client.incquery.teachersOfSchoolNames pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TeachersOfSchoolNamesMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern teachersOfSchoolNames(TName, SName) {
 * 	School.teachers(Sch, T);
 * 	School.name(Sch, SName);
 * 	Teacher.name(T, TName);
 * }
 * </pre></code>
 * 
 * @see TeachersOfSchoolNamesMatch
 * @see TeachersOfSchoolNamesProcessor
 * @see TeachersOfSchoolNamesQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TeachersOfSchoolNamesMatcher extends BaseMatcher<TeachersOfSchoolNamesMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TeachersOfSchoolNamesMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    TeachersOfSchoolNamesMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TeachersOfSchoolNamesMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TNAME = 0;
  
  private final static int POSITION_SNAME = 1;
  
  private final static org.apache.log4j.Logger LOGGER = IncQueryLoggingUtil.getLogger(TeachersOfSchoolNamesMatcher.class);
  
  /**
   * Initializes the pattern matcher over a given EMF model root (recommended: Resource or ResourceSet).
   * If a pattern matcher is already constructed with the same root, only a light-weight reference is returned.
   * The scope of pattern matching will be the given EMF model root and below (see FAQ for more precise definition).
   * The match set will be incrementally refreshed upon updates from this scope.
   * <p>The matcher will be created within the managed {@link IncQueryEngine} belonging to the EMF model root, so
   * multiple matchers will reuse the same engine and benefit from increased performance and reduced memory footprint.
   * @param emfRoot the root of the EMF containment hierarchy where the pattern matcher will operate. Recommended: Resource or ResourceSet.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead, e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}
   * 
   */
  @Deprecated
  public TeachersOfSchoolNamesMatcher(final Notifier emfRoot) throws IncQueryException {
    this(IncQueryEngine.on(emfRoot));
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead
   * 
   */
  @Deprecated
  public TeachersOfSchoolNamesMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return matches represented as a TeachersOfSchoolNamesMatch object.
   * 
   */
  public Collection<TeachersOfSchoolNamesMatch> getAllMatches(final String pTName, final String pSName) {
    return rawGetAllMatches(new Object[]{pTName, pSName});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return a match represented as a TeachersOfSchoolNamesMatch object, or null if no match is found.
   * 
   */
  public TeachersOfSchoolNamesMatch getOneArbitraryMatch(final String pTName, final String pSName) {
    return rawGetOneArbitraryMatch(new Object[]{pTName, pSName});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final String pTName, final String pSName) {
    return rawHasMatch(new Object[]{pTName, pSName});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final String pTName, final String pSName) {
    return rawCountMatches(new Object[]{pTName, pSName});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final String pTName, final String pSName, final IMatchProcessor<? super TeachersOfSchoolNamesMatch> processor) {
    rawForEachMatch(new Object[]{pTName, pSName}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final String pTName, final String pSName, final IMatchProcessor<? super TeachersOfSchoolNamesMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTName, pSName}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTName the fixed value of pattern parameter TName, or null if not bound.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TeachersOfSchoolNamesMatch newMatch(final String pTName, final String pSName) {
    return TeachersOfSchoolNamesMatch.newMatch(pTName, pSName);
  }
  
  /**
   * Retrieve the set of values that occur in matches for TName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<String> rawAccumulateAllValuesOfTName(final Object[] parameters) {
    Set<String> results = new HashSet<String>();
    rawAccumulateAllValues(POSITION_TNAME, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for TName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfTName() {
    return rawAccumulateAllValuesOfTName(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for TName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfTName(final TeachersOfSchoolNamesMatch partialMatch) {
    return rawAccumulateAllValuesOfTName(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for TName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfTName(final String pSName) {
    return rawAccumulateAllValuesOfTName(new Object[]{
    null, 
    pSName
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for SName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<String> rawAccumulateAllValuesOfSName(final Object[] parameters) {
    Set<String> results = new HashSet<String>();
    rawAccumulateAllValues(POSITION_SNAME, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for SName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfSName() {
    return rawAccumulateAllValuesOfSName(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for SName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfSName(final TeachersOfSchoolNamesMatch partialMatch) {
    return rawAccumulateAllValuesOfSName(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for SName.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<String> getAllValuesOfSName(final String pTName) {
    return rawAccumulateAllValuesOfSName(new Object[]{
    pTName, 
    null
    });
  }
  
  @Override
  protected TeachersOfSchoolNamesMatch tupleToMatch(final Tuple t) {
    try {
    	return TeachersOfSchoolNamesMatch.newMatch((java.lang.String) t.get(POSITION_TNAME), (java.lang.String) t.get(POSITION_SNAME));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TeachersOfSchoolNamesMatch arrayToMatch(final Object[] match) {
    try {
    	return TeachersOfSchoolNamesMatch.newMatch((java.lang.String) match[POSITION_TNAME], (java.lang.String) match[POSITION_SNAME]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TeachersOfSchoolNamesMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TeachersOfSchoolNamesMatch.newMutableMatch((java.lang.String) match[POSITION_TNAME], (java.lang.String) match[POSITION_SNAME]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<TeachersOfSchoolNamesMatcher> querySpecification() throws IncQueryException {
    return TeachersOfSchoolNamesQuerySpecification.instance();
  }
}
