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
package io.wcm.qa.galenium.maven.freemarker.util;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;

import io.wcm.qa.galenium.maven.freemarker.pojo.SpecPojo;
import io.wcm.qa.galenium.selectors.base.NestedSelector;

public final class FormatUtil {

  private static final String FILE_ENDING_GSPEC = ".gspec";
  private static final String REGEX_NAME_CLEANING = "\\.";

  private FormatUtil() {
    // do not instantiate
  }

  public static String getClassName(File file) {
    return getClassName(FilenameUtils.getBaseName(file.getPath()));
  }

  public static String getClassName(NestedSelector selector) {
    String elementName = selector.elementName();
    String relativeElementName = getRelativeElementName(elementName);
    String cleanElementName = getCleanElementName(relativeElementName);
    return kebapToUpperCamel(cleanElementName);
  }

  public static String getClassName(SpecPojo specPojo) {
    return kebapToUpperCamel(specPojo.getBasename());
  }

  public static String getClassName(String string) {
    return kebapToUpperCamel(string);
  }

  public static String getConstantName(NestedSelector selector) {
    String elementName = selector.elementName();
    String relativeElementName = getRelativeElementName(elementName);
    String cleanElementName = getCleanElementName(relativeElementName);
    return kebapToConstant(cleanElementName);
  }

  public static String getSelectorsPackageName(String packageRoot, SpecPojo spec) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(packageRoot);
    String relativePackageName = getPackageName(spec);
    if (!StringUtils.startsWith(relativePackageName, ".")) {
      stringBuilder.append(".");
    }
    stringBuilder.append(relativePackageName);
    return stringBuilder.toString();
  }

  private static String getCleanElementName(String elementName) {
    return elementName.replaceAll(REGEX_NAME_CLEANING, "__");
  }

  private static String getPackageName(SpecPojo spec) {
    String relativePath = spec.getRelativeFilePath();
    String relativePathWithoutExtension = StringUtils.removeEnd(relativePath, FILE_ENDING_GSPEC);
    String lowerCasePath = relativePathWithoutExtension.toLowerCase().replaceAll("[^a-z0-9/]", "");
    return lowerCasePath.replaceAll("/", ".");
  }

  /**
   * Format as constant name.
   * @param input lower hyphen formatted string
   * @return upper case with underscores version of input string
   */
  private static String kebapToConstant(String input) {
    return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, input);
  }

  /**
   * Format as class name.
   * @param input lower hyphen formatted string
   * @return upper camel case version of input string
   */
  private static String kebapToUpperCamel(String input) {
    return CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, input);
  }

  protected static String getRelativeElementName(String elementName) {
    return elementName.replaceFirst("^.*\\.", "");
  }

}
