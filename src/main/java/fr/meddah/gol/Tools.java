package fr.meddah.gol;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Ranges;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.DiscreteDomains.*;

import static com.google.common.collect.Lists.*;

import static com.google.common.collect.Sets.*;
import static org.apache.commons.collections15.CollectionUtils.*;

public final class Tools {

	public static <T> Collection<T> intersect(Iterable<T> first, Iterable<T> second) {
		return intersection(newArrayList(first), newArrayList(second));
	}

	@SuppressWarnings("unchecked")
	public static <T, U> Collection<T> allInstances(Class<T> forClass, Set<U> firstArguments, Set<U> secondArguments) {
		Collection<List<U>> constructorArguments = cartesianProduct(firstArguments, secondArguments);
		return project(constructorArguments, forClass, on(List.class).get(0), on(List.class).get(1));
	}

	public static ContiguousSet<Long> range(long lowerBound, long upperBound) {
		return Ranges.closed(lowerBound, upperBound).asSet(longs());
	}
}