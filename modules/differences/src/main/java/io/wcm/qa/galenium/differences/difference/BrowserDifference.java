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
package io.wcm.qa.galenium.differences.difference;

import io.wcm.qa.galenium.device.BrowserType;
import io.wcm.qa.galenium.device.TestDevice;
import io.wcm.qa.galenium.differences.base.Difference;
import io.wcm.qa.galenium.differences.base.DifferenceBase;
import io.wcm.qa.galenium.util.GaleniumContext;

/**
 * {@link TestDevice} based {@link Difference} using {@link BrowserType}.
 */
public class BrowserDifference extends DifferenceBase {

  @Override
  protected String getRawTag() {
    return GaleniumContext.getTestDevice().getBrowserType().getBrowser();
  }

}
