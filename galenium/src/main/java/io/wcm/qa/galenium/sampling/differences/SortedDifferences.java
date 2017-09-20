/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.qa.galenium.sampling.differences;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Differences are stored in a sorted way to get a consistent hierarchy.
 */
public class SortedDifferences extends MutableDifferences {

  private Comparator<Difference> comparator;
  private SortedSet<Difference> differences;

  /**
   * Use default comparator.
   */
  public SortedDifferences() {
    setDifferences(new TreeSet<Difference>(getComparator()));
  }

  /**
   * @return the configured comparator or {@link DifferenceNameComparator} if none is set
   */
  public Comparator<Difference> getComparator() {
    if (comparator == null) {
      return new DifferenceNameComparator();
    }
    return comparator;
  }

  @Override
  public Collection<Difference> getDifferences() {
    return differences;
  }

  /**
   * Set a new comparator and reorder already existing differences.
   * @param comparator to use from now on
   */
  public void setComparator(Comparator<Difference> comparator) {
    this.comparator = comparator;
    TreeSet<Difference> newDifferences = new TreeSet<Difference>(comparator);
    newDifferences.addAll(getDifferences());
    setDifferences(newDifferences);
  }

  private void setDifferences(SortedSet<Difference> differences) {
    this.differences = differences;
  }
}
