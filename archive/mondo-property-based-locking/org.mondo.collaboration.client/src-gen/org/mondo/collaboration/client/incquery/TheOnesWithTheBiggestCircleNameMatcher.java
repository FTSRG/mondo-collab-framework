package org.mondo.collaboration.client.incquery;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.eclipse.incquery.runtime.api.IQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.tuple.Tuple;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;
import org.mondo.collaboration.client.incquery.TheOnesWithTheBiggestCircleNameMatch;
import org.mondo.collaboration.client.incquery.util.TheOnesWithTheBiggestCircleNameQuerySpecification;

/**
 * Generated pattern matcher API of the org.mondo.collaboration.client.incquery.theOnesWithTheBiggestCircleName pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TheOnesWithTheBiggestCircleNameMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern theOnesWithTheBiggestCircleName(SName) {
 * 		neg find moreFriendsThan(_Sx, S);
 * 		Student.name(S, SName);
 * 	}
 * </pre></code>
 * 
 * @see TheOnesWithTheBiggestCircleNameMatch
 * @see TheOnesWithTheBiggestCircleNameProcessor
 * @see TheOnesWithTheBiggestCircleNameQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TheOnesWithTheBiggestCircleNameMatcher extends BaseMatcher<TheOnesWithTheBiggestCircleNameMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TheOnesWithTheBiggestCircleNameMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    TheOnesWithTheBiggestCircleNameMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TheOnesWithTheBiggestCircleNameMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SNAME = 0;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(TheOnesWithTheBiggestCircleNameMatcher.class);
  
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
  public TheOnesWithTheBiggestCircleNameMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public TheOnesWithTheBiggestCircleNameMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return matches represented as a TheOnesWithTheBiggestCircleNameMatch object.
   * 
   */
  public Collection<TheOnesWithTheBiggestCircleNameMatch> getAllMatches(final String pSName) {
    return rawGetAllMatches(new Object[]{pSName});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return a match represented as a TheOnesWithTheBiggestCircleNameMatch object, or null if no match is found.
   * 
   */
  public TheOnesWithTheBiggestCircleNameMatch getOneArbitraryMatch(final String pSName) {
    return rawGetOneArbitraryMatch(new Object[]{pSName});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final String pSName) {
    return rawHasMatch(new Object[]{pSName});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final String pSName) {
    return rawCountMatches(new Object[]{pSName});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final String pSName, final IMatchProcessor<? super TheOnesWithTheBiggestCircleNameMatch> processor) {
    rawForEachMatch(new Object[]{pSName}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final String pSName, final IMatchProcessor<? super TheOnesWithTheBiggestCircleNameMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSName}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSName the fixed value of pattern parameter SName, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TheOnesWithTheBiggestCircleNameMatch newMatch(final String pSName) {
    return TheOnesWithTheBiggestCircleNameMatch.newMatch(pSName);
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
  
  @Override
  protected TheOnesWithTheBiggestCircleNameMatch tupleToMatch(final Tuple t) {
    try {
    	return TheOnesWithTheBiggestCircleNameMatch.newMatch((java.lang.String) t.get(POSITION_SNAME));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TheOnesWithTheBiggestCircleNameMatch arrayToMatch(final Object[] match) {
    try {
    	return TheOnesWithTheBiggestCircleNameMatch.newMatch((java.lang.String) match[POSITION_SNAME]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TheOnesWithTheBiggestCircleNameMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TheOnesWithTheBiggestCircleNameMatch.newMutableMatch((java.lang.String) match[POSITION_SNAME]);
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
  public static IQuerySpecification<TheOnesWithTheBiggestCircleNameMatcher> querySpecification() throws IncQueryException {
    return TheOnesWithTheBiggestCircleNameQuerySpecification.instance();
  }
}
