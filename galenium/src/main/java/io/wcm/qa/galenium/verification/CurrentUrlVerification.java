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
package io.wcm.qa.galenium.verification;

import org.apache.commons.lang3.StringUtils;

import io.wcm.qa.galenium.util.GaleniumContext;
import io.wcm.qa.galenium.verification.base.VerificationBase;

public class CurrentUrlVerification extends VerificationBase {

  private static final String KEY_PART_URL = "url";

  public CurrentUrlVerification(String pageName) {
    super(pageName);
  }

  public CurrentUrlVerification(String pageName, String expectedUrl) {
    super(pageName, expectedUrl);
  }

  @Override
  protected String getAdditionalToStringInfo() {
    return getExpectedValue();
  }

  @Override
  protected String getExpectedKey() {
    if (StringUtils.isNotBlank(super.getExpectedKey())) {
      return super.getExpectedKey() + "." + KEY_PART_URL;
    }
    return KEY_PART_URL;
  }

  @Override
  protected String getFailureMessage() {
    return "Expected URL: '" + getExpectedValue() + "' but found '" + getActualValue() + "'";
  }

  @Override
  protected String getSuccessMessage() {
    return "Found URL: '" + getExpectedValue() + "'";
  }

  @Override
  protected String sampleValue() {
    return GaleniumContext.getDriver().getCurrentUrl();
  }

}
