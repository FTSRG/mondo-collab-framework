package hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain;

import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.domain.common.Policy;
import hu.bme.mit.ftsrg.mondo.accesscontrol.policyverification.verifier.PolicyVerifier;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.incquery.patternlanguage.emf.specification.SpecificationBuilder;
import org.eclipse.incquery.patternlanguage.patternLanguage.Pattern;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.extensibility.QuerySpecificationRegistry;

public class PolicyQuery extends Policy{
	private IQuerySpecification<? extends IncQueryMatcher<? extends IPatternMatch>> specification;
	private Collection<? extends IPatternMatch> matches;
	private Collection<? extends IPatternMatch> originalMatches;
	private IncQueryMatcher<? extends IPatternMatch> matcher;
	private IncQueryMatcher<? extends IPatternMatch> originalMatcher;
	private Pattern pattern;
	
	public PolicyQuery(Policy policy){
		super(policy);
		this.specification = null;
		this.matches = null;
		this.originalMatches = null;
		this.matcher = null;
		this.originalMatcher = null;
		
		this.pattern = PolicyVerifier.getPattern(URI.createFileURI(getQuery().getPath()), getPattern());
	}

	public PolicyQuery(String name, File query, String pattern) {
		super(name, query, pattern);
		this.specification = null;
		this.matches = null;
		this.originalMatches = null;
		this.matcher = null;
		this.originalMatcher = null;
		
		this.pattern = PolicyVerifier.getPattern(URI.createFileURI(getQuery().getPath()), getPattern());
	}
	
	public PolicyQuery(String name, Permission permission, File query, String pattern){
		super(name, permission, query, pattern);
		this.specification = null;
		this.matches = null;
		this.originalMatches = null;
		this.matcher = null;
		this.originalMatcher = null;
		
		this.pattern = PolicyVerifier.getPattern(URI.createFileURI(getQuery().getPath()), getPattern());
	}
	
	public void setSpecification(IQuerySpecification<? extends IncQueryMatcher<? extends IPatternMatch>> specification){
		this.specification = specification;
	}
	
	public IQuerySpecification<? extends IncQueryMatcher<? extends IPatternMatch>> getSpecification(){
		return specification;
	}
		
	/**
	 * Ki�rt�kel egy keres�si mint�t, hogy van-e tal�lat. Csak az sz�m�t tal�latnak, ha elt�r a kor�bbi eredm�nyt�l �s a tal�latok sz�ma nagyobb mint 0.
	 * Ha nem l�tezik m�g a patternhez specifik�ci�, akkor l�trehozza �s beregisztr�lja.
	 * @param resource			Aktu�lisan szerkesztett modell
	 * @param initialization	Minta inicializ�l�sa, vagy futtat�sa
	 * @return
	 * @throws IncQueryException
	 */
	public boolean executeQueryOnPattern(Resource resource, boolean initialization) throws IncQueryException {
			
		@SuppressWarnings("deprecation")
		AdvancedIncQueryEngine engine = AdvancedIncQueryEngine.createUnmanagedEngine(resource);
		
		if(pattern != null){
			if(specification == null){
				SpecificationBuilder builder = new SpecificationBuilder();
				IQuerySpecification<? extends IncQueryMatcher<? extends IPatternMatch>> specification = builder.getOrCreateSpecification(pattern);
				if(QuerySpecificationRegistry.getQuerySpecification(getPattern()) == null){
					QuerySpecificationRegistry.registerQuerySpecification(specification);
				}
				
				setSpecification(specification);
			}

			if(initialization){
				originalMatcher = getMatcher(engine);
				originalMatches = originalMatcher.getAllMatches();
			} else {
				matcher = getMatcher(engine);
				matches = matcher.getAllMatches();
			}
					
			//D�nt�s, hogy van-e tal�lat, illetve t�rt�nt-e v�ltoz�s a kor�bbi ki�rt�kel�s �ta
			if(originalMatches != null || matches != null){
				//Ha nincs el�zm�ny
				if((originalMatches == null && matches != null) || originalMatches != null && matches == null){
					return true;
				}
				//Ha van �s volt is tal�lat, de nem azonos a tal�latok sz�ma
				if(originalMatches.size() != matches.size()){
					return true;
				}
				//Ha van �s volt is tal�lat, de azonos a tal�latok sz�ma, meg kell vizsg�lni a tal�latok azonoss�g�t
				if(originalMatches.size() == matches.size()){
					if(matches.size() == 0){
						return false;
					}

					//A kor�bbi �s aktu�lis tal�lati lista azonoss��gnak ellen�rz�se
					final ArrayList<IPatternMatch> compatibleCounter = new ArrayList<IPatternMatch>();
					matcher.forEachMatch(new IMatchProcessor<IPatternMatch>() {
						@Override
						public void process(IPatternMatch match) {							
							for (IPatternMatch prevMatch : originalMatches) {
								if(match.isCompatibleWith(prevMatch)){
									compatibleCounter.add(match);
								}
							}
						}
					});
					if(compatibleCounter.size() == matches.size()){
						return false;
					} else {
						return true;
					}
				}		
			}
		}
		return false;
	}
		
	public IncQueryMatcher<? extends IPatternMatch> getMatcher(AdvancedIncQueryEngine engine) throws IncQueryException{
		IncQueryMatcher<? extends IPatternMatch> matcher = null;
		if(specification != null && engine != null){
			matcher = engine.getMatcher(specification);
		}
		return matcher;
	}

}
