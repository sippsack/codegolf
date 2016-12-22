package de.dasniko.codegolf;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@EnableScan
public interface ResultEntryRepository extends PagingAndSortingRepository<ResultEntry, String> {
}
