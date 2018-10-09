/*
 * Copyright 2016 The Apache Software Foundation.
 *
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
 */
package org.apache.avro.logicalTypes;

import java.math.RoundingMode;
import java.util.Map;
import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypeFactory;
import org.apache.avro.Schema;

/**
 *
 * @author zfarkas
 */
public class DecimalFactory implements LogicalTypeFactory {

  @Override
  public String getLogicalTypeName() {
    return "decimal";
  }

  private static RoundingMode getRoundingMode(Map<String, Object> map, String fieldName) {
    Object n = map.get(fieldName);
    if (n == null) {
      return null;
    } else {
      if (n instanceof RoundingMode) {
        return (RoundingMode) n;
      } else {
        String str = n.toString();
        if ("none".equalsIgnoreCase(str)) {
          return null;
        } else {
          return RoundingMode.valueOf(str);
        }
      }
    }
  }

  @Override
  public LogicalType create(Schema.Type schemaType, Map<String, Object> attributes) {
    return new Decimal((Integer) attributes.get("precision"),
            (Integer) attributes.get("scale"), schemaType, getRoundingMode(attributes, "serRounding"),
            getRoundingMode(attributes, "deserRounding"));
  }

}