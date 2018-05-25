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
package io.wcm.qa.galenium.interaction;

import static io.wcm.qa.galenium.reporting.GaleniumReportUtil.getLogger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.wcm.qa.galenium.util.GaleniumContext;

public final class Wait {

  private static final int DEFAULT_POLLING_INTERVAL = 100;
  private static final int DEFAULT_TIMEOUT = 1;

  private Wait() {
    // do not instantiate
  }

  /**
   * Wait for domReady for a maximum of one second.
   */
  public static void forDomReady() {
    forDomReady(DEFAULT_TIMEOUT);
  }

  /**
   * Wait for "DOM ready" for a maximum of seconds specified by parameter.
   * @param timeOutInSeconds how long to wait for "DOM ready"
   */
  public static void forDomReady(int timeOutInSeconds) {
    WebDriverWait wait = getWait(timeOutInSeconds);
    wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
  }

  /**
   * Load URL and wait for it to be loaded.
   * @param url to load
   */
  public static void forUrl(String url) {
    Wait.forUrl(url, DEFAULT_TIMEOUT);
  }

  /**
   * Load URL and wait passed number of seconds for it to be loaded.
   * @param url to load
   * @param timeOutInSeconds how long to wait for URL to be current
   */
  public static void forUrl(String url, int timeOutInSeconds) {
    getLogger().trace("waiting for URL: '" + url + "'");
    WebDriverWait wait = getWait(timeOutInSeconds);
    wait.until(ExpectedConditions.urlToBe(url));
    getLogger().trace("found URL: '" + url + "'");
  }

  private static WebDriverWait getWait(int timeOutInSeconds) {
    return getWait(timeOutInSeconds, DEFAULT_POLLING_INTERVAL);
  }

  private static WebDriverWait getWait(int timeOutInSeconds, int pollingInterval) {
    return new WebDriverWait(GaleniumContext.getDriver(), timeOutInSeconds, pollingInterval);
  }

}
