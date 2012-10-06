package fr.meddah.gol;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Ranges;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.DiscreteDomains.*;
import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Sets.*;

public final class Tools {

	public static <T> Set<T> flatMap(Collection<?> collection, Collection<T> function) {
		return newHashSet(concat(collect(collection, function)));
	}

	@SuppressWarnings("unchecked")
	public static <T, U extends Comparable<U>> Collection<T> allCombinationsOf(Class<T> toClass, ContiguousSet<U> first, ContiguousSet<U> second) {
		Collection<List<U>> constructorArguments = cartesianProduct(first, second);
		return project(constructorArguments, toClass, on(List.class).get(0), on(List.class).get(1));
	}

	public static ContiguousSet<Long> range(long lowerBound, long upperBound) {
		return Ranges.closed(lowerBound, upperBound).asSet(longs());
	}
}