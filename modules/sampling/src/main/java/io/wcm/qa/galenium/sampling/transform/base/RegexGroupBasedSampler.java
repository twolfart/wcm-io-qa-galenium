/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2018 wcm.io
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
package io.wcm.qa.galenium.sampling.transform.base;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.wcm.qa.galenium.sampling.Sampler;


public abstract class RegexGroupBasedSampler<S extends Sampler<String>> extends RegexBasedSampler<S, Collection<String>> {

  private boolean includeWholeMatchGroup = true;

  public RegexGroupBasedSampler(S inputSampler, Pattern pattern) {
    super(inputSampler, pattern);
  }

  public RegexGroupBasedSampler(S inputSampler, String regex) {
    super(inputSampler, regex);
  }

  public RegexGroupBasedSampler(S inputSampler, String regex, int flags) {
    super(inputSampler, regex, flags);
  }

  protected void addAllGroups(Collection<String> results, Matcher matcher) {
    if (isIncludeWholeMatchGroup()) {
      results.add(matcher.group());
    }
    int groupCount = matcher.groupCount();
    if (groupCount > 0) {
      for (int i = 1; i <= groupCount; i++) {
        results.add(matcher.group(i));
      }
    }
  }

  protected boolean isIncludeWholeMatchGroup() {
    return includeWholeMatchGroup;
  }

  protected void setIncludeWholeMatchGroup(boolean includeWholeMatchGroup) {
    this.includeWholeMatchGroup = includeWholeMatchGroup;
  }

}
