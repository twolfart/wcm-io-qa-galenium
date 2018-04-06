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
package io.wcm.qa.galenium.selectors;

import com.galenframework.specs.page.Locator;

import io.wcm.qa.galenium.exceptions.GaleniumException;

/**
 * Creates Selectors for use with Galenium.
 */
public final class SelectorFactory {

  private SelectorFactory() {
    // do not instantiate
  }

  /**
   * @param selectorString CSS selector
   * @return Galenium selector representing the CSS selector
   */
  public static Selector fromCss(String selectorString) {
    return new SelectorFromString(selectorString);
  }

  /**
   * @param elementName name to use for selector
   * @param selectorString CSS selector
   * @return Galenium selector representing the CSS selector
   */
  public static Selector fromCss(String elementName, String selectorString) {
    AbstractSelectorBase selector = new SelectorFromString(selectorString);
    selector.setName(elementName);
    return selector;
  }

  /**
   * @param locator to construct selector from
   * @return Galenium selector representing the locator
   */
  public static SelectorFromLocator fromLocator(Locator locator) {
    checkLocator(locator);
    return new SelectorFromLocator(locator);
  }

  private static void checkLocator(Locator locator) {

    switch (locator.getLocatorType()) {
      case "css":
        // standard case
        return;

      case "id":
        // can be transformed into standard case
        locator.setLocatorType("css");
        locator.setLocatorValue("#" + locator.getLocatorValue());
        return;

      default:
        throw new GaleniumException("unsupported locator type: '" + locator.getLocatorType() + "'");
    }
  }

  /**
   * @param elementName alternative name to use in Selector
   * @param locator to construct selector from
   * @return Galenium selector representing the locator
   */
  public static SelectorFromLocator fromLocator(String elementName, Locator locator) {
    checkLocator(locator);
    return new SelectorFromLocator(elementName, locator);
  }

  /**
   * Constructs selector relative to a parent selector.
   * @param parent parent selector to be used as base
   * @param relativeSelector relative to parent
   * @return new selector that is relative to the parent selector
   */
  public static Selector relativeToAbsolute(Selector parent, Selector relativeSelector) {
    return relativeToAbsolute(parent, relativeSelector.elementName(), relativeSelector.asString());
  }

  /**
   * Constructs selector relative to a parent selector.
   * @param parent parent selector to be used as base
   * @param relativeCssSelector relative to parent
   * @return new selector that is relative to the parent selector
   */
  public static Selector relativeToAbsolute(Selector parent, String relativeCssSelector) {
    return relativeToAbsolute(parent, "child", relativeCssSelector);
  }

  /**
   * Constructs selector relative to a parent selector.
   * @param parent parent selector to be used as base
   * @param childName to use in construction of name of relative selector
   * @param relativeCssSelector relative to parent
   * @return new selector that is relative to the parent selector
   */
  public static Selector relativeToAbsolute(Selector parent, String childName, String relativeCssSelector) {
    String selectorString = parent.asString() + " " + relativeCssSelector;
    String elementName = parent.elementName() + "|" + childName;
    return fromCss(elementName, selectorString);
  }
}
